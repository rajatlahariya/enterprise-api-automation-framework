package com.rajat.framework.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Optional;

import org.testng.annotations.Test;

import com.rajat.framework.api.client.UserClient;
import com.rajat.framework.api.model.ApiResponse;
import com.rajat.framework.api.model.user.CreateUserRequest;
import com.rajat.framework.api.model.user.UpdateUserRequest;
import com.rajat.framework.api.model.user.User;
import com.rajat.framework.data.UserDataFactory;
import com.rajat.framework.database.model.UserDatabaseRecord;
import com.rajat.framework.database.repository.UserDatabaseRepository;
import com.rajat.framework.testgroup.TestGroups;

public class UserApiDatabaseValidationTest {

	private final UserClient userClient = new UserClient();

	private final UserDatabaseRepository repository = new UserDatabaseRepository();

	@Test(groups = { TestGroups.INTEGRATION, TestGroups.REGRESSION })
	public void shouldValidateCompleteUserLifecycleAgainstDatabase() {

		CreateUserRequest createRequest = UserDataFactory.createUser("Database", "Validation", 30, true);

		User createdUser = null;
		boolean deleted = false;

		try {
			// Create through API
			createdUser = userClient.createUser(createRequest);

			assertThat(createdUser, notNullValue());
			assertThat(createdUser.getId(), notNullValue());

			Long userId = createdUser.getId().longValue();

			// Validate created database row
			UserDatabaseRecord createdRecord = repository.findById(userId)
					.orElseThrow(() -> new AssertionError("Created user was not found in database. ID: " + userId));

			assertUserMatchesCreateRequest(createdRecord, createRequest);

			// Update through API
			UpdateUserRequest updateRequest = UserDataFactory.createUpdatedUser(createdUser.getEmail());

			User updatedUser = userClient.updateUser(createdUser.getId(), updateRequest);

			assertThat(updatedUser, notNullValue());

			// Validate updated database row
			UserDatabaseRecord updatedRecord = repository.findById(userId)
					.orElseThrow(() -> new AssertionError("Updated user was not found in database. ID: " + userId));

			assertUserMatchesUpdateRequest(updatedRecord, updateRequest);

			// Soft delete through API
			ApiResponse deleteResponse = userClient.deleteUser(createdUser.getId());

			assertThat(deleteResponse.getStatusCode(), equalTo(204));

			deleted = true;

			// Soft-deleted row must still exist
			UserDatabaseRecord deletedRecord = repository.findById(userId).orElseThrow(
					() -> new AssertionError("Soft-deleted user row should remain in database. ID: " + userId));

			assertThat(deletedRecord.active(), equalTo(false));

			// Deleted user should no longer be exposed by API
			ApiResponse getResponse = userClient.getUserResponseById(createdUser.getId());

			assertThat(getResponse.getStatusCode(), equalTo(404));

		} finally {
			/*
			 * Cleanup only when the test failed before reaching the intended soft-delete
			 * step.
			 */
			if (createdUser != null && !deleted) {

				Optional<UserDatabaseRecord> record = repository.findById(createdUser.getId().longValue());

				if (record.isPresent() && Boolean.TRUE.equals(record.get().active())) {

					userClient.deleteUser(createdUser.getId());
				}
			}
		}
	}

	private void assertUserMatchesCreateRequest(UserDatabaseRecord record, CreateUserRequest request) {

		assertThat(record.firstName(), equalTo(request.getFirstName()));

		assertThat(record.lastName(), equalTo(request.getLastName()));

		assertThat(record.email(), equalTo(request.getEmail()));

		assertThat(record.age(), equalTo(request.getAge()));

		assertThat(record.active(), equalTo(request.getIsActive()));

		assertThat(record.createdAt(), notNullValue());
	}

	private void assertUserMatchesUpdateRequest(UserDatabaseRecord record, UpdateUserRequest request) {

		assertThat(record.firstName(), equalTo(request.getFirstName()));

		assertThat(record.lastName(), equalTo(request.getLastName()));

		assertThat(record.email(), equalTo(request.getEmail()));

		assertThat(record.age(), equalTo(request.getAge()));

		assertThat(record.active(), equalTo(request.getIsActive()));
	}
}