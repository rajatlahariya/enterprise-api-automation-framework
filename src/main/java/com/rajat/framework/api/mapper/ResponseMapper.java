package com.rajat.framework.api.mapper;

import java.util.LinkedHashMap;
import java.util.Map;

import com.rajat.framework.api.response.ApiResponse;

import io.restassured.response.Response;

public final class ResponseMapper {

    private ResponseMapper() {
        // Prevent object creation
    }

    public static ApiResponse toApiResponse(Response response) {

        Map<String, String> headers = new LinkedHashMap<>();

        response.getHeaders().forEach(header ->
                headers.put(header.getName(), header.getValue())
        );

        return new ApiResponse(
                response.getStatusCode(),
                response.getBody().asString(),
                headers,
                response.getTime()
        );
    }
}