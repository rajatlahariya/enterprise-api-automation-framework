package com.rajat.framework.api.authentication;

import io.restassured.specification.RequestSpecification;

public class NoAuthenticationProvider implements AuthenticationProvider {

    @Override
    public RequestSpecification apply(RequestSpecification specification) {
        return specification;
    }
}