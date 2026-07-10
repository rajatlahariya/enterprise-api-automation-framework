package com.rajat.framework.api.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajat.framework.api.model.ApiResponse;
import com.rajat.framework.api.model.AuthenticationToken;
import com.rajat.framework.core.exceptions.AuthenticationException;

public final class AuthenticationTokenMapper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private AuthenticationTokenMapper() {
        // Prevent object creation
    }

    public static AuthenticationToken from(ApiResponse response) {
        if (response == null) {
            throw new AuthenticationException("Authentication response cannot be null.");
        }

        if (response.getStatusCode() < 200 || response.getStatusCode() >= 300) {
            throw new AuthenticationException(
                    "Authentication failed with HTTP status: " + response.getStatusCode()
            );
        }

        try {
            JsonNode rootNode = OBJECT_MAPPER.readTree(response.getBody());

            // Supports both:
            // { "accessToken": "..." }
            // and { "data": { "accessToken": "..." } }
            JsonNode tokenNode = rootNode.hasNonNull("data")
                    ? rootNode.get("data")
                    : rootNode;

            String accessToken = requiredText(tokenNode, "accessToken");
            String refreshToken = optionalText(tokenNode, "refreshToken");
            String tokenType = optionalText(tokenNode, "tokenType");

            if (tokenType == null || tokenType.isBlank()) {
                tokenType = "Bearer";
            }

            long expiresIn = optionalLong(tokenNode, "expiresIn");

            return new AuthenticationToken(
                    accessToken,
                    refreshToken,
                    tokenType,
                    expiresIn
            );

        } catch (JsonProcessingException exception) {
            throw new AuthenticationException(
                    "Unable to parse authentication response.",
                    exception
            );
        }
    }

    private static String requiredText(JsonNode node, String fieldName) {
        JsonNode field = node.get(fieldName);

        if (field == null || field.isNull() || field.asText().isBlank()) {
            throw new AuthenticationException(
                    "Missing or blank authentication field: " + fieldName
            );
        }

        return field.asText().trim();
    }

    private static String optionalText(JsonNode node, String fieldName) {
        JsonNode field = node.get(fieldName);

        if (field == null || field.isNull()) {
            return null;
        }

        return field.asText().trim();
    }

    private static long optionalLong(JsonNode node, String fieldName) {
        JsonNode field = node.get(fieldName);

        if (field == null || field.isNull()) {
            return 0L;
        }

        return field.asLong();
    }
}