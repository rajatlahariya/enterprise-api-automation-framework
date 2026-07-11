package com.rajat.framework.api.authentication;

import org.testng.annotations.Test;

import com.rajat.framework.testgroup.TestGroups;

import io.restassured.specification.RequestSpecification;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class AuthenticationFactoryTest {

	@Test(groups = { TestGroups.SMOKE, TestGroups.CRUD })
	public void shouldCreateNoAuthProvider() {
		AuthenticationProvider provider = AuthenticationFactory.noAuth();

		assertThat(provider, notNullValue());
	}

	@Test(groups = { TestGroups.SMOKE, TestGroups.CRUD })
	public void shouldCreateBasicAuthProvider() {
		AuthenticationProvider provider = AuthenticationFactory.basic();

		assertThat(provider, notNullValue());
	}

	@Test(groups = { TestGroups.SMOKE, TestGroups.CRUD })
	public void shouldCreateBearerTokenProvider() {
		AuthenticationProvider provider = AuthenticationFactory.bearerToken("dummy-token");

		assertThat(provider, notNullValue());
	}
}