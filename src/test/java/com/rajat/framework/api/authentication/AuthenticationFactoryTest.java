package com.rajat.framework.api.authentication;

import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class AuthenticationFactoryTest {

    @Test
    public void shouldCreateNoAuthProvider() {
        AuthenticationProvider provider = AuthenticationFactory.noAuth();

        assertThat(provider, notNullValue());
    }

    @Test
    public void shouldCreateBasicAuthProvider() {
        AuthenticationProvider provider = AuthenticationFactory.basic();

        assertThat(provider, notNullValue());
    }

    @Test
    public void shouldCreateBearerTokenProvider() {
        AuthenticationProvider provider = AuthenticationFactory.bearerToken("dummy-token");

        assertThat(provider, notNullValue());
    }
}