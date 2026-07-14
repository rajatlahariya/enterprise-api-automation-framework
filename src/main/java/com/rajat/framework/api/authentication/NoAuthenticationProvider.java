package com.rajat.framework.api.authentication;

import com.rajat.framework.core.exceptions.AuthenticationException;

import io.restassured.specification.RequestSpecification;

public final class NoAuthenticationProvider implements AuthenticationProvider {

    @Override
    public RequestSpecification apply(RequestSpecification specification) {
        if (specification == null) {
            throw new AuthenticationException(
                    "Request specification cannot be null."
            );
        }

        return specification;
    }
}
