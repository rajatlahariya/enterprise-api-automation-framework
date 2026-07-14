package com.rajat.framework.api.authentication;

import com.rajat.framework.core.exceptions.AuthenticationException;

import io.restassured.specification.RequestSpecification;

public final class BasicAuthenticationProvider implements AuthenticationProvider {

    private final String username;
    private final String password;

    public BasicAuthenticationProvider(String username, String password) {
        if (username == null || username.isBlank()) {
            throw new AuthenticationException(
                    "Basic-auth username cannot be null or blank."
            );
        }

        if (password == null || password.isBlank()) {
            throw new AuthenticationException(
                    "Basic-auth password cannot be null or blank."
            );
        }

        this.username = username.trim();
        this.password = password;
    }

    @Override
    public RequestSpecification apply(RequestSpecification specification) {
        if (specification == null) {
            throw new AuthenticationException(
                    "Request specification cannot be null."
            );
        }

        return specification
                .auth()
                .preemptive()
                .basic(username, password);
    }
}
