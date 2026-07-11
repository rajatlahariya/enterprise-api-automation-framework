package com.rajat.framework.api.client;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public final class ResponseSpecificationFactory {

	private ResponseSpecificationFactory() {
		// Prevent object creation
	}

	public static ResponseSpecification success() {
		return new ResponseSpecBuilder().expectStatusCode(allOfSuccessRange()).build();
	}

	public static ResponseSpecification ok() {
		return new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}

	public static ResponseSpecification created() {
		return new ResponseSpecBuilder().expectStatusCode(201).expectContentType(ContentType.JSON).build();
	}

	public static ResponseSpecification noContent() {
		return new ResponseSpecBuilder().expectStatusCode(204).build();
	}

	public static ResponseSpecification badRequest() {
		return new ResponseSpecBuilder().expectStatusCode(400).expectContentType(ContentType.JSON).build();
	}

	public static ResponseSpecification unauthorized() {
		return new ResponseSpecBuilder().expectStatusCode(401).build();
	}

	public static ResponseSpecification forbidden() {
		return new ResponseSpecBuilder().expectStatusCode(403).build();
	}

	public static ResponseSpecification notFound() {
		return new ResponseSpecBuilder().expectStatusCode(404).expectContentType(ContentType.JSON).build();
	}

	public static ResponseSpecification statusCode(int expectedStatusCode) {
		return new ResponseSpecBuilder().expectStatusCode(expectedStatusCode).build();
	}

	public static ResponseSpecification jsonStatusCode(int expectedStatusCode) {

		return new ResponseSpecBuilder().expectStatusCode(expectedStatusCode).expectContentType(ContentType.JSON)
				.build();
	}

	private static org.hamcrest.Matcher<Integer> allOfSuccessRange() {
		return org.hamcrest.Matchers.allOf(greaterThanOrEqualTo(200), lessThan(300));
	}
}