package com.rajat.framework.assertion;

import java.util.Collections;

import org.testng.annotations.Test;

import com.rajat.framework.api.model.ApiResponse;
import com.rajat.framework.testgroup.TestGroups;

@Test(groups = {TestGroups.UNIT, TestGroups.REGRESSION})
public class ResponseAssertionsTest {

	@Test
	public void shouldValidateSuccessfulResponse() {

		ApiResponse response = new ApiResponse(200, "{\"success\":true}", Collections.emptyMap(), 150L);

		ResponseAssertions.assertStatusCode(response, 200);
		ResponseAssertions.assertSuccessful(response);
		ResponseAssertions.assertBodyNotBlank(response);
		ResponseAssertions.assertResponseTimeBelow(response, 1000L);
	}

	@Test(expectedExceptions = AssertionError.class, groups = { TestGroups.NEGATIVE })
	public void shouldFailWhenStatusCodeIsUnexpected() {

		ApiResponse response = new ApiResponse(404, "{\"message\":\"Not found\"}", Collections.emptyMap(), 100L);

		ResponseAssertions.assertStatusCode(response, 200);
	}

	@Test(expectedExceptions = AssertionError.class, groups = { TestGroups.NEGATIVE })
	public void shouldFailWhenResponseTimeExceedsLimit() {

		ApiResponse response = new ApiResponse(200, "{\"success\":true}", Collections.emptyMap(), 2500L);

		ResponseAssertions.assertResponseTimeBelow(response, 1000L);
	}
}