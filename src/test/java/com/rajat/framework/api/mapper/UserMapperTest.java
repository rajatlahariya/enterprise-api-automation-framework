package com.rajat.framework.api.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.rajat.framework.api.model.ApiResponse;
import com.rajat.framework.api.model.user.User;
import com.rajat.framework.testgroup.TestGroups;

public class UserMapperTest {

	@Test(groups = { TestGroups.SEARCH, TestGroups.REGRESSION })
	public void shouldMapUserSuccessfully() {

		String body = "{" + "\"success\":true," + "\"message\":\"User fetched successfully\"," + "\"data\":{"
				+ "\"id\":4," + "\"firstName\":\"Aarav\"," + "\"lastName\":\"Sharma\","
				+ "\"email\":\"demo.user@example.test\"," + "\"age\":22," + "\"isActive\":true,"
				+ "\"createdAt\":\"2026-07-07T07:36:31.236269\"" + "}" + "}";

		ApiResponse response = new ApiResponse(200, body, null, 120L);

		User user = UserMapper.from(response);

		assertThat(user.getId(), equalTo(4));
		assertThat(user.getFirstName(), equalTo("Aarav"));
		assertThat(user.getLastName(), equalTo("Sharma"));
		assertThat(user.getEmail(), equalTo("demo.user@example.test"));
		assertThat(user.getAge(), equalTo(22));
		assertThat(user.getIsActive(), equalTo(true));
	}
}