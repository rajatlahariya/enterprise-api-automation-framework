package com.rajat.framework.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.rajat.framework.core.exceptions.FrameworkException;

public final class JsonNodeUtils {

    private JsonNodeUtils() {
        // Prevent object creation
    }

    public static String getText(JsonNode node, String fieldName) {
        JsonNode field = getField(node, fieldName);

        if (field == null || field.isNull()) {
            return null;
        }

        return field.asText();
    }

    public static String getRequiredText(JsonNode node, String fieldName) {
        String value = getText(node, fieldName);

        if (value == null || value.isBlank()) {
            throw new FrameworkException(
                    "Missing or blank required JSON field: " + fieldName
            );
        }

        return value.trim();
    }

    public static Integer getInteger(JsonNode node, String fieldName) {
        JsonNode field = getField(node, fieldName);

        if (field == null || field.isNull()) {
            return null;
        }

        return field.asInt();
    }

    public static Long getLong(JsonNode node, String fieldName) {
        JsonNode field = getField(node, fieldName);

        if (field == null || field.isNull()) {
            return null;
        }

        return field.asLong();
    }

    public static Boolean getBoolean(JsonNode node, String fieldName) {
        JsonNode field = getField(node, fieldName);

        if (field == null || field.isNull()) {
            return null;
        }

        return field.asBoolean();
    }

    public static LocalDateTime getLocalDateTime(
            JsonNode node,
            String fieldName) {

        String value = getText(node, fieldName);

        if (value == null || value.isBlank()) {
            return null;
        }

        try {
            return LocalDateTime.parse(value);
        } catch (DateTimeParseException exception) {
            throw new FrameworkException(
                    "Invalid date-time value for JSON field '"
                            + fieldName + "': " + value,
                    exception
            );
        }
    }

    private static JsonNode getField(JsonNode node, String fieldName) {
        if (node == null) {
            throw new FrameworkException("JSON node cannot be null.");
        }

        if (fieldName == null || fieldName.isBlank()) {
            throw new FrameworkException(
                    "JSON field name cannot be null or blank."
            );
        }

        return node.get(fieldName);
    }
}
