package com.rajat.framework.api.client;

import static io.restassured.RestAssured.given;

import com.rajat.framework.api.endpoint.Endpoint;
import com.rajat.framework.api.mapper.ResponseMapper;
import com.rajat.framework.api.mapper.UserMapper;
import com.rajat.framework.api.model.ApiResponse;
import com.rajat.framework.api.model.user.CreateUserRequest;
import com.rajat.framework.api.model.user.UpdateUserRequest;
import com.rajat.framework.api.model.user.User;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserClient extends BaseClient {

	public User createUser(CreateUserRequest request) {

		RequestSpecification specification = authenticatedJsonSpecification();

		Response response = given().spec(specification).body(request).when().post(Endpoint.USERS.getResolvedPath());

		ApiResponse apiResponse = ResponseMapper.toApiResponse(response);

		return UserMapper.from(apiResponse);
	}

	public User getUserById(Integer userId) {

		if (userId == null || userId <= 0) {
			throw new IllegalArgumentException("User ID must be a positive number.");
		}

		RequestSpecification specification = authenticatedJsonSpecification();

		Response response = given().spec(specification).pathParam("id", userId).when()
				.get(Endpoint.USER_BY_ID.getResolvedPath());

		ApiResponse apiResponse = ResponseMapper.toApiResponse(response);

		return UserMapper.from(apiResponse);
	}
	
	public User updateUser(Integer userId, UpdateUserRequest request) {

	    if (userId == null || userId <= 0) {
	        throw new IllegalArgumentException(
	                "User ID must be a positive number."
	        );
	    }

	    RequestSpecification specification =
	            authenticatedJsonSpecification();

	    Response response = given()
	            .spec(specification)
	            .pathParam("id", userId)
	            .body(request)
	        .when()
	            .put(Endpoint.USER_BY_ID.getResolvedPath());

	    ApiResponse apiResponse =
	            ResponseMapper.toApiResponse(response);

	    return UserMapper.from(apiResponse);
	}
	
	public ApiResponse deleteUser(Integer userId) {

	    if (userId == null || userId <= 0) {
	        throw new IllegalArgumentException(
	                "User ID must be a positive number."
	        );
	    }

	    RequestSpecification specification =
	            authenticatedJsonSpecification();

	    Response response = given()
	            .spec(specification)
	            .pathParam("id", userId)
	        .when()
	            .delete(Endpoint.USER_BY_ID.getResolvedPath());

	    return ResponseMapper.toApiResponse(response);
	}
}