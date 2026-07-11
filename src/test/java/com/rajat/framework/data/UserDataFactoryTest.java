package com.rajat.framework.data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.rajat.framework.api.model.user.CreateUserRequest;
import com.rajat.framework.api.model.user.UpdateUserRequest;
import com.rajat.framework.testgroup.TestGroups;

public class UserDataFactoryTest {

	@Test(groups = { TestGroups.SMOKE, TestGroups.CRUD })
	public void shouldCreateValidDefaultUserData() {

		CreateUserRequest request = UserDataFactory.createDefaultUser();

		assertThat(request, notNullValue());
		assertThat(request.getFirstName(), equalTo("Automation"));
		assertThat(request.getLastName(), equalTo("User"));
		assertThat(request.getEmail(), containsString("@enterprise.test"));
		assertThat(request.getAge(), equalTo(30));
		assertThat(request.getIsActive(), equalTo(true));
	}

	@Test(groups = { TestGroups.SMOKE, TestGroups.CRUD })
	public void shouldCreateUpdatedUserDataWithExistingEmail() {

		String existingEmail = "existing.user@enterprise.test";

		UpdateUserRequest request = UserDataFactory.createUpdatedUser(existingEmail);

		assertThat(request.getEmail(), equalTo(existingEmail));
		assertThat(request.getFirstName(), equalTo("Updated"));
		assertThat(request.getAge(), equalTo(32));
	}
}