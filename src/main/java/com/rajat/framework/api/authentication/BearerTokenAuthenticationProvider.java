package com.rajat.framework.api.authentication;

import com.rajat.framework.core.exceptions.AuthenticationException;

import io.restassured.specification.RequestSpecification;

public class BearerTokenAuthenticationProvider implements AuthenticationProvider {

    private static final String BEARER_PREFIX = "Bearer ";

    private final String accessToken;

    public BearerTokenAuthenticationProvider(String token) {

        if (token == null || token.isBlank()) {
            throw new AuthenticationException(
                    "Bearer token cannot be null or blank."
            );
        }

        String normalizedToken = token.trim();

        if (normalizedToken.regionMatches(
                true,
                0,
                BEARER_PREFIX,
                0,
                BEARER_PREFIX.length())) {

            normalizedToken = normalizedToken
                    .substring(BEARER_PREFIX.length())
                    .trim();
        }

        if (normalizedToken.isBlank()) {
            throw new AuthenticationException(
                    "Bearer token value cannot be blank."
            );
        }

        this.accessToken = normalizedToken;
    }

    @Override
    public RequestSpecification apply(
            RequestSpecification specification) {

        if (specification == null) {
            throw new AuthenticationException(
                    "Request specification cannot be null."
            );
        }

        return specification.auth().oauth2(accessToken);
    }
}