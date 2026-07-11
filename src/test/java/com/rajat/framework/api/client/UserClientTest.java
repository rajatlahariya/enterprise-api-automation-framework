package com.rajat.framework.api.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.containsString;
import com.rajat.framework.api.mapper.ErrorResponseMapper;
import com.rajat.framework.api.model.ErrorResponse;
import com.rajat.framework.api.model.common.PagedResult;
import com.rajat.framework.assertion.ResponseAssertions;

import com.rajat.framework.api.model.ApiResponse;
import com.rajat.framework.api.model.user.CreateUserRequest;
import com.rajat.framework.api.model.user.UpdateUserRequest;
import com.rajat.framework.api.model.user.User;
import com.rajat.framework.api.model.user.UserSearchCriteria;
import com.rajat.framework.assertion.JsonSchemaAssertions;
import com.rajat.framework.data.UserDataFactory;
import com.rajat.framework.testgroup.TestGroups;

public class UserClientTest {

	@Test
	public void shouldCreateUserSuccessfully() {

		String uniqueEmail = "automation.user." + System.currentTimeMillis() + "@enterprise.test";

		CreateUserRequest request = new CreateUserRequest("Automation", "User", uniqueEmail, 30, true);

		UserClient userClient = new UserClient();

		User createdUser = userClient.createUser(request);

		assertThat(createdUser, notNullValue());
		assertThat(createdUser.getId(), notNullValue());
		assertThat(createdUser.getFirstName(), equalTo(request.getFirstName()));
		assertThat(createdUser.getLastName(), equalTo(request.getLastName()));
		assertThat(createdUser.getEmail(), equalTo(request.getEmail()));
		assertThat(createdUser.getAge(), equalTo(request.getAge()));
		assertThat(createdUser.getIsActive(), equalTo(request.getIsActive()));
	}

	@Test
	public void shouldGetCreatedUserById() {

		String uniqueEmail = "read.user." + System.currentTimeMillis() + "@enterprise.test";

		CreateUserRequest request = new CreateUserRequest("Read", "User", uniqueEmail, 28, true);

		UserClient userClient = new UserClient();

		User createdUser = userClient.createUser(request);

		User fetchedUser = userClient.getUserById(createdUser.getId());

		assertThat(fetchedUser, notNullValue());
		assertThat(fetchedUser.getId(), equalTo(createdUser.getId()));
		assertThat(fetchedUser.getFirstName(), equalTo(request.getFirstName()));
		assertThat(fetchedUser.getLastName(), equalTo(request.getLastName()));
		assertThat(fetchedUser.getEmail(), equalTo(request.getEmail()));
		assertThat(fetchedUser.getAge(), equalTo(request.getAge()));
		assertThat(fetchedUser.getIsActive(), equalTo(request.getIsActive()));
	}

	@Test
	public void shouldCompleteUserCrudFlow() {

		UserClient userClient = new UserClient();

		CreateUserRequest createRequest = UserDataFactory.createUser("Crud", "User", 31, true);

		User createdUser = userClient.createUser(createRequest);

		assertThat(createdUser, notNullValue());
		assertThat(createdUser.getId(), notNullValue());
		assertThat(createdUser.getEmail(), equalTo(createRequest.getEmail()));

		User fetchedUser = userClient.getUserById(createdUser.getId());

		assertThat(fetchedUser, notNullValue());
		assertThat(fetchedUser.getId(), equalTo(createdUser.getId()));
		assertThat(fetchedUser.getEmail(), equalTo(createRequest.getEmail()));

		UpdateUserRequest updateRequest = UserDataFactory.createUpdatedUser(createdUser.getEmail());

		User updatedUser = userClient.updateUser(createdUser.getId(), updateRequest);

		assertThat(updatedUser, notNullValue());
		assertThat(updatedUser.getFirstName(), equalTo(updateRequest.getFirstName()));
		assertThat(updatedUser.getAge(), equalTo(updateRequest.getAge()));
		assertThat(updatedUser.getIsActive(), equalTo(updateRequest.getIsActive()));

		ApiResponse deleteResponse = userClient.deleteUser(createdUser.getId());

		assertThat(deleteResponse.getStatusCode(), equalTo(204));
	}

	@Test
	public void shouldMatchUserResponseSchema() {

		UserClient userClient = new UserClient();

		CreateUserRequest request = UserDataFactory.createDefaultUser();

		User createdUser = null;

		try {
			createdUser = userClient.createUser(request);

			ApiResponse response = userClient.getUserResponseById(createdUser.getId());

			JsonSchemaAssertions.assertMatchesSchema(response, "schemas/user-response-schema.json");

		} finally {
			if (createdUser != null) {
				userClient.deleteUser(createdUser.getId());
			}
		}
	}

	@Test(groups = { TestGroups.SMOKE, TestGroups.CRUD })
	public void shouldReturnNotFoundForSoftDeletedUser() {

		UserClient userClient = new UserClient();

		CreateUserRequest request = UserDataFactory.createDefaultUser();

		User createdUser = userClient.createUser(request);

		ApiResponse deleteResponse = userClient.deleteUser(createdUser.getId());

		ResponseAssertions.assertStatusCode(deleteResponse, 204);

		ApiResponse getResponse = userClient.getUserResponseById(createdUser.getId());

		ResponseAssertions.assertStatusCode(getResponse, 404);

		ErrorResponse errorResponse = ErrorResponseMapper.from(getResponse);

		assertThat(errorResponse.getSuccess(), equalTo(false));

		assertThat(errorResponse.getMessage(), containsString("User not found"));
	}

	@Test(groups = { TestGroups.SMOKE, TestGroups.CRUD })
	public void shouldReturnFirstPageOfUsers() {

		UserClient userClient = new UserClient();

		UserSearchCriteria criteria = UserSearchCriteria.builder().page(0).size(5).sort("id,asc").build();

		PagedResult<User> result = userClient.getUsers(criteria);

		assertThat(result, notNullValue());
		assertThat(result.getPagination(), notNullValue());

		assertThat(result.getPagination().getPage(), equalTo(0));

		assertThat(result.getPagination().getSize(), equalTo(5));

		assertThat(result.getNumberOfElements() <= 5, equalTo(true));
	}
}