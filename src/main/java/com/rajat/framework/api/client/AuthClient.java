package com.rajat.framework.api.client;

import static io.restassured.RestAssured.given;

import com.rajat.framework.api.endpoint.Endpoint;
import com.rajat.framework.api.mapper.AuthenticationTokenMapper;
import com.rajat.framework.api.mapper.ResponseMapper;
import com.rajat.framework.api.model.ApiResponse;
import com.rajat.framework.api.model.AuthenticationToken;
import com.rajat.framework.api.model.LoginRequest;
import com.rajat.framework.api.specification.RequestSpecificationFactory;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public final class AuthClient {

    public ApiResponse login(LoginRequest loginRequest) {

        RequestSpecification specification =
                RequestSpecificationFactory.buildJsonSpecification();

        Response response = given()
                .spec(specification)
                .body(loginRequest)
            .when()
                .post(Endpoint.LOGIN.getResolvedPath());

        return ResponseMapper.toApiResponse(response);
    }

    public AuthenticationToken loginAndGetToken(LoginRequest loginRequest) {
        ApiResponse response = login(loginRequest);
        return AuthenticationTokenMapper.from(response);
    }
}
