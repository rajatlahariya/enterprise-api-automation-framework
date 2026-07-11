package com.rajat.framework.api.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajat.framework.api.model.ApiResponse;
import com.rajat.framework.api.model.ErrorResponse;
import com.rajat.framework.core.exceptions.FrameworkException;
import com.rajat.framework.util.JsonNodeUtils;

public final class ErrorResponseMapper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private ErrorResponseMapper() {
        // Prevent object creation
    }

    public static ErrorResponse from(ApiResponse response) {

        if (response == null) {
            throw new FrameworkException(
                    "Error API response cannot be null."
            );
        }

        try {
            JsonNode root =
                    OBJECT_MAPPER.readTree(response.getBody());

            return new ErrorResponse(
                    JsonNodeUtils.getBoolean(root, "success"),
                    JsonNodeUtils.getRequiredText(root, "message")
            );

        } catch (JsonProcessingException exception) {
            throw new FrameworkException(
                    "Unable to parse error response.",
                    exception
            );
        }
    }
}