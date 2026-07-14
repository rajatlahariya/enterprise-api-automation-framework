package com.rajat.framework.api.client;

import com.rajat.framework.api.authentication.AuthenticationFactory;
import com.rajat.framework.api.authentication.TokenManager;
import com.rajat.framework.api.specification.RequestSpecificationFactory;

import io.restassured.specification.RequestSpecification;

public abstract class BaseClient {

    protected RequestSpecification authenticatedJsonSpecification() {
        String accessToken = TokenManager.getToken().getAccessToken();

        return RequestSpecificationFactory.buildJsonSpecification(
                AuthenticationFactory.bearerToken(accessToken)
        );
    }
}
