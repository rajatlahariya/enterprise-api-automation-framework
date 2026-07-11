package com.rajat.framework.api.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.rajat.framework.api.model.ApiResponse;
import com.rajat.framework.api.model.AuthenticationToken;
import com.rajat.framework.api.model.LoginRequest;
import com.rajat.framework.core.configuration.ConfigManager;
import com.rajat.framework.testgroup.TestGroups;

public class AuthClientTest {

	@Test(groups = { TestGroups.NEGATIVE, TestGroups.REGRESSION })
	public void shouldLoginSuccessfullyWithValidCredentials() {

		AuthClient authClient = new AuthClient();

		LoginRequest loginRequest = new LoginRequest(ConfigManager.getAdminUsername(),
				ConfigManager.getAdminPassword());

		ApiResponse response = authClient.login(loginRequest);

		assertThat(response.getStatusCode(), equalTo(200));
		assertThat(response.getBody(), notNullValue());
		assertThat(response.getBody(), containsString("accessToken"));
	}

	@Test(groups = { TestGroups.NEGATIVE, TestGroups.REGRESSION })
	public void shouldReturnAuthenticationTokenAfterSuccessfulLogin() {

		AuthClient authClient = new AuthClient();

		LoginRequest loginRequest = new LoginRequest(ConfigManager.getAdminUsername(),
				ConfigManager.getAdminPassword());

		AuthenticationToken token = authClient.loginAndGetToken(loginRequest);

		assertThat(token, notNullValue());
		assertThat(token.getAccessToken(), notNullValue());
		assertThat(token.getAccessToken().isBlank(), equalTo(false));
		assertThat(token.getTokenType(), equalTo("Bearer"));
	}

}