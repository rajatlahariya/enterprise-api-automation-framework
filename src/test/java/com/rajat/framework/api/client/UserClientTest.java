package com.rajat.framework.api.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.anyOf;

import org.testng.annotations.Test;

import com.rajat.framework.api.model.ApiResponse;
import com.rajat.framework.api.model.user.CreateUserRequest;
import com.rajat.framework.api.model.user.UpdateUserRequest;
import com.rajat.framework.api.model.user.User;
import com.rajat.framework.data.UserDataFactory;

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
}