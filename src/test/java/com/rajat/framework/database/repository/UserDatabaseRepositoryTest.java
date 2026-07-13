package com.rajat.framework.database.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.Optional;

import org.testng.annotations.Test;

import com.rajat.framework.database.model.UserDatabaseRecord;
import com.rajat.framework.testgroup.TestGroups;

public class UserDatabaseRepositoryTest {

	private final UserDatabaseRepository repository = new UserDatabaseRepository();

	@Test(groups = { TestGroups.INTEGRATION, TestGroups.REGRESSION })
	public void shouldFindExistingUserById() {

		List<UserDatabaseRecord> activeUsers = repository.findActiveUsers();

		assertThat(activeUsers.size(), greaterThanOrEqualTo(1));

		Long existingUserId = activeUsers.get(0).id();

		Optional<UserDatabaseRecord> result = repository.findById(existingUserId);

		assertThat(result.isPresent(), equalTo(true));
		assertThat(result.get().id(), equalTo(existingUserId));
		assertThat(result.get().email(), notNullValue());
	}

	@Test(groups = { TestGroups.INTEGRATION, TestGroups.REGRESSION })
	public void shouldFindExistingUserByEmail() {

		List<UserDatabaseRecord> activeUsers = repository.findActiveUsers();

		assertThat(activeUsers.size(), greaterThanOrEqualTo(1));

		String existingEmail = activeUsers.get(0).email();

		Optional<UserDatabaseRecord> result = repository.findByEmail(existingEmail);

		assertThat(result.isPresent(), equalTo(true));
		assertThat(result.get().email(), equalTo(existingEmail));
	}

	@Test(groups = { TestGroups.INTEGRATION, TestGroups.REGRESSION })
	public void shouldConfirmExistingEmail() {

		String existingEmail = repository.findActiveUsers().get(0).email();

		assertThat(repository.existsByEmail(existingEmail), equalTo(true));
	}

	@Test(groups = { TestGroups.INTEGRATION, TestGroups.REGRESSION })
	public void shouldReturnUserCount() {

		assertThat(repository.countUsers(), greaterThanOrEqualTo(1L));
	}

	@Test
	public void shouldRejectInvalidUserId() {

		try {
			repository.findById(0L);
		} catch (IllegalArgumentException exception) {
			assertThat(exception.getMessage(), equalTo("User ID must be a positive number."));
			return;
		}

		throw new AssertionError("Expected IllegalArgumentException.");
	}
}