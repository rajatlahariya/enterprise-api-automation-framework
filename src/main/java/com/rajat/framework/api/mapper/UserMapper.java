package com.rajat.framework.api.mapper;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajat.framework.api.model.ApiResponse;
import com.rajat.framework.api.model.user.User;
import com.rajat.framework.core.exceptions.FrameworkException;
import com.rajat.framework.util.JsonNodeUtils;

public final class UserMapper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private UserMapper() {
        // Prevent object creation
    }

    public static User from(ApiResponse response) {

        validate(response);

        try {

            JsonNode root = OBJECT_MAPPER.readTree(response.getBody());
            JsonNode data = root.get("data");

            if (data == null || data.isNull()) {
                throw new FrameworkException("User data is missing from response.");
            }

            return new User(
                    JsonNodeUtils.getInteger(data, "id"),
                    JsonNodeUtils.getText(data, "firstName"),
                    JsonNodeUtils.getText(data, "lastName"),
                    JsonNodeUtils.getText(data, "email"),
                    JsonNodeUtils.getInteger(data, "age"),
                    JsonNodeUtils.getBoolean(data, "isActive"),
                    JsonNodeUtils.getLocalDateTime(data, "createdAt")
            );

        } catch (JsonProcessingException exception) {
            throw new FrameworkException(
                    "Unable to parse user response.",
                    exception);
        }
    }

    private static void validate(ApiResponse response) {

        if (response == null) {
            throw new FrameworkException("ApiResponse cannot be null.");
        }

        if (response.getStatusCode() < 200 ||
                response.getStatusCode() >= 300) {

            throw new FrameworkException(
                    "User API failed. Status Code: "
                            + response.getStatusCode()
                            + ", Body: "
                            + response.getBody());
        }
    }
}