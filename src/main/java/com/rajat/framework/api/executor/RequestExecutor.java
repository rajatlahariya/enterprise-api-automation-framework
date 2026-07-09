package com.rajat.framework.api.executor;

import com.rajat.framework.api.mapper.ResponseMapper;
import com.rajat.framework.api.response.ApiResponse;
import com.rajat.framework.core.configuration.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public final class RequestExecutor {

    private RequestExecutor() {
        // Prevent object creation
    }

    public static ApiResponse get(String endpoint) {

        Response response = RestAssured
                .given()
                    .baseUri(ConfigManager.getBaseUrl())
                .when()
                    .get(endpoint);

        return ResponseMapper.toApiResponse(response);
    }
}