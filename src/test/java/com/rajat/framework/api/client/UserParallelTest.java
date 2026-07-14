package com.rajat.framework.api.client;

import java.util.UUID;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import com.rajat.framework.api.authentication.TokenManager;
import com.rajat.framework.api.model.common.PagedResult;
import com.rajat.framework.api.model.user.CreateUserRequest;
import com.rajat.framework.api.model.user.User;
import com.rajat.framework.api.model.user.UserSearchCriteria;
import com.rajat.framework.testgroup.TestGroups;

public class UserParallelTest {

	@Test(
		groups = { TestGroups.INTEGRATION, TestGroups.REGRESSION, TestGroups.CRUD },
		invocationCount = 10,
		threadPoolSize = 5
	)
	public void shouldCreateUsersInParallel() {

		UserClient client = new UserClient();

		String email = "parallel." + UUID.randomUUID() + "@enterprise.test";

		CreateUserRequest request = new CreateUserRequest("Parallel", "User", email, 30, true);

		User created = null;

		try {
			created = client.createUser(request);

			assertThat(created.getEmail(), equalTo(email));
			assertThat(TokenManager.getToken(), notNullValue());
		} finally {
			if (created != null) {
				client.deleteUser(created.getId());
			}
		}
	}

	@Test(
		groups = { TestGroups.INTEGRATION, TestGroups.REGRESSION, TestGroups.SEARCH },
		invocationCount = 20,
		threadPoolSize = 10
	)
	public void shouldSearchUsersInParallel() {

		UserClient client = new UserClient();

		UserSearchCriteria criteria = UserSearchCriteria.builder().page(0).size(5).build();

		PagedResult<User> result = client.getUsers(criteria);

		assertThat(result, notNullValue());
		assertThat(TokenManager.getToken(), notNullValue());
	}

}
