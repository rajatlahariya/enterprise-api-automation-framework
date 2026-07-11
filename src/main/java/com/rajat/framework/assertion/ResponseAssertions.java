package com.rajat.framework.assertion;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import com.rajat.framework.api.model.ApiResponse;

public final class ResponseAssertions {

	private ResponseAssertions() {
		// Prevent object creation
	}

	public static void assertStatusCode(ApiResponse response, int expectedStatusCode) {

		assertResponseNotNull(response);

		assertThat("Unexpected HTTP status. Response body: " + response.getBody(), response.getStatusCode(),
				is(expectedStatusCode));
	}

	public static void assertSuccessful(ApiResponse response) {

		assertResponseNotNull(response);

		assertThat("Expected a successful HTTP response. Response body: " + response.getBody(),
				response.getStatusCode(), allOf(greaterThanOrEqualTo(200), lessThan(300)));
	}

	public static void assertBodyNotBlank(ApiResponse response) {

		assertResponseNotNull(response);

		assertThat("Response body should not be null.", response.getBody(), notNullValue());

		assertThat("Response body should not be blank.", response.getBody().isBlank(), is(false));
	}

	public static void assertResponseTimeBelow(ApiResponse response, long maximumResponseTimeInMs) {

		assertResponseNotNull(response);

		if (maximumResponseTimeInMs <= 0) {
			throw new IllegalArgumentException("Maximum response time must be greater than zero.");
		}

		assertThat("Response time exceeded " + maximumResponseTimeInMs + " ms.", response.getResponseTimeInMs(),
				lessThan(maximumResponseTimeInMs));
	}

	private static void assertResponseNotNull(ApiResponse response) {
		assertThat("ApiResponse cannot be null.", response, notNullValue());
	}
}