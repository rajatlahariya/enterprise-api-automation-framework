package com.rajat.framework.api.authentication;

import io.restassured.specification.RequestSpecification;

public class BasicAuthenticationProvider implements AuthenticationProvider {

    private final String username;
    private final String password;

    public BasicAuthenticationProvider(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public RequestSpecification apply(RequestSpecification specification) {
        return specification
                .auth()
                .preemptive()
                .basic(username, password);
    }
}