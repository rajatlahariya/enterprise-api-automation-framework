package com.rajat.framework.api.specification;

import com.rajat.framework.api.authentication.AuthenticationFactory;
import com.rajat.framework.api.authentication.AuthenticationProvider;
import com.rajat.framework.core.configuration.ConfigManager;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecificationFactory {

    private RequestSpecificationFactory() {
        // Prevent object creation
    }

    public static RequestSpecification buildJsonSpecification() {
        return buildJsonSpecification(AuthenticationFactory.noAuth());
    }

    public static RequestSpecification buildJsonSpecification(
            AuthenticationProvider authenticationProvider) {

        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();

        return authenticationProvider.apply(specification);
    }

    public static RequestSpecification buildBasicAuthJsonSpecification() {
        return buildJsonSpecification(AuthenticationFactory.basic());
    }

    public static RequestSpecification buildBearerTokenJsonSpecification(
            String token) {
        return buildJsonSpecification(AuthenticationFactory.bearerToken(token));
    }
}
