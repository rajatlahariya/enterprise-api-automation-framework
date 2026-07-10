package com.rajat.framework.api.authentication;

import io.restassured.specification.RequestSpecification;

public interface AuthenticationProvider {

    RequestSpecification apply(RequestSpecification specification);
}
