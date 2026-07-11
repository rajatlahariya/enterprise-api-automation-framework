package com.rajat.framework.assertion;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

import com.rajat.framework.api.model.ApiResponse;

public final class JsonSchemaAssertions {

    private JsonSchemaAssertions() {
        // Prevent object creation
    }

    public static void assertMatchesSchema(
            ApiResponse response,
            String schemaPath) {

        if (response == null) {
            throw new IllegalArgumentException(
                    "ApiResponse cannot be null."
            );
        }

        if (schemaPath == null || schemaPath.isBlank()) {
            throw new IllegalArgumentException(
                    "Schema path cannot be null or blank."
            );
        }

        assertThat(
                "Response body does not match schema: " + schemaPath,
                response.getBody(),
                matchesJsonSchemaInClasspath(schemaPath)
        );
    }
}