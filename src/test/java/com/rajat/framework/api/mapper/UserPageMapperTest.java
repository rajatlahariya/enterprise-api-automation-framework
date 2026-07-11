package com.rajat.framework.api.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Collections;

import org.testng.annotations.Test;

import com.rajat.framework.api.model.ApiResponse;
import com.rajat.framework.api.model.common.PagedResult;
import com.rajat.framework.api.model.user.User;
import com.rajat.framework.testgroup.TestGroups;

public class UserPageMapperTest {

	@Test(groups = { TestGroups.SMOKE, TestGroups.CRUD })
	public void shouldMapPagedUserResponseSuccessfully() {

		String body = "{" + "\"success\":true," + "\"message\":\"Users fetched successfully\"," + "\"data\":[" + "{"
				+ "\"id\":4," + "\"firstName\":\"Aarav\"," + "\"lastName\":\"Sharma\","
				+ "\"email\":\"seed.aarav@enterprise.test\"," + "\"age\":29," + "\"isActive\":true,"
				+ "\"createdAt\":\"2026-07-07T07:36:31.236269\"" + "}," + "{" + "\"id\":5,"
				+ "\"firstName\":\"Vihaan\"," + "\"lastName\":\"Verma\"," + "\"email\":\"seed.vihaan@enterprise.test\","
				+ "\"age\":32," + "\"isActive\":true," + "\"createdAt\":\"2026-07-07T07:40:31.236269\"" + "}" + "],"
				+ "\"pagination\":{" + "\"page\":0," + "\"size\":2," + "\"totalElements\":20," + "\"totalPages\":10,"
				+ "\"first\":true," + "\"last\":false" + "}" + "}";

		ApiResponse response = new ApiResponse(200, body, Collections.emptyMap(), 120L);

		PagedResult<User> result = UserPageMapper.from(response);

		assertThat(result.getNumberOfElements(), equalTo(2));

		assertThat(result.isEmpty(), is(false));

		assertThat(result.getContent().get(0).getFirstName(), equalTo("Aarav"));

		assertThat(result.getContent().get(1).getAge(), equalTo(32));

		assertThat(result.getPagination().getPage(), equalTo(0));

		assertThat(result.getPagination().getSize(), equalTo(2));

		assertThat(result.getPagination().getTotalElements(), equalTo(20L));

		assertThat(result.getPagination().getTotalPages(), equalTo(10));

		assertThat(result.getPagination().getFirst(), equalTo(true));

		assertThat(result.getPagination().getLast(), equalTo(false));
	}
}