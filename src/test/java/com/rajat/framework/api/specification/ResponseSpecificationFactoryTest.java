package com.rajat.framework.api.specification;

import org.testng.annotations.Test;

import com.rajat.framework.testgroup.TestGroups;

import io.restassured.builder.ResponseBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Test(groups = {TestGroups.UNIT, TestGroups.REGRESSION})
public class ResponseSpecificationFactoryTest {

	@Test
	public void shouldValidateOkResponse() {

		Response response = new ResponseBuilder().setStatusCode(200).setContentType(ContentType.JSON)
				.setBody("{\"success\":true}").build();

		response.then().spec(ResponseSpecificationFactory.ok());
	}

	@Test
	public void shouldValidateCreatedResponse() {

		Response response = new ResponseBuilder().setStatusCode(201).setContentType(ContentType.JSON)
				.setBody("{\"success\":true}").build();

		response.then().spec(ResponseSpecificationFactory.created());
	}

	@Test
	public void shouldValidateNoContentResponse() {

		Response response = new ResponseBuilder().setStatusCode(204).build();

		response.then().spec(ResponseSpecificationFactory.noContent());
	}

	@Test
	public void shouldValidateNotFoundResponse() {

		Response response = new ResponseBuilder().setStatusCode(404).setContentType(ContentType.JSON)
				.setBody("{\"success\":false}").build();

		response.then().spec(ResponseSpecificationFactory.notFound());
	}

	@Test(expectedExceptions = AssertionError.class, groups = { TestGroups.NEGATIVE })
	public void shouldFailForUnexpectedStatusCode() {

		Response response = new ResponseBuilder().setStatusCode(500).setContentType(ContentType.JSON)
				.setBody("{\"success\":false}").build();

		response.then().spec(ResponseSpecificationFactory.ok());
	}
}