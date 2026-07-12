package com.rajat.framework.api.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Collections;

import org.testng.annotations.Test;

import com.rajat.framework.api.model.ApiResponse;
import com.rajat.framework.api.model.ErrorResponse;
import com.rajat.framework.testgroup.TestGroups;

@Test(groups = {TestGroups.UNIT, TestGroups.REGRESSION, TestGroups.NEGATIVE})
public class ErrorResponseMapperTest {

	@Test
	public void shouldMapErrorResponseSuccessfully() {

		String body = "{" + "\"success\":false," + "\"message\":\"User not found with id: 999\"," + "\"data\":null,"
				+ "\"errors\":null" + "}";

		ApiResponse response = new ApiResponse(404, body, Collections.emptyMap(), 50L);

		ErrorResponse errorResponse = ErrorResponseMapper.from(response);

		assertThat(errorResponse.getSuccess(), equalTo(false));

		assertThat(errorResponse.getMessage(), equalTo("User not found with id: 999"));
	}
}