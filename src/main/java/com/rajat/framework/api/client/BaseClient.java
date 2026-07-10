package com.rajat.framework.api.client;

import com.rajat.framework.api.specification.RequestSpecificationFactory;

import io.restassured.specification.RequestSpecification;

public abstract class BaseClient {

    protected RequestSpecification publicJsonSpecification() {
        return RequestSpecificationFactory.buildJsonSpecification();
    }

    protected RequestSpecification authenticatedJsonSpecification() {
        return RequestSpecificationFactory.buildAuthenticatedJsonSpecification();
    }

    protected RequestSpecification basicAuthJsonSpecification() {
        return RequestSpecificationFactory.buildBasicAuthJsonSpecification();
    }
}