package com.rajat.framework.core.configuration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.rajat.framework.core.exceptions.ConfigurationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class ConfigManagerTest {

    @AfterMethod
    public void cleanUp() {
        System.clearProperty("env");
    }

    @Test
    public void shouldReturnLocalBaseUrlWhenEnvironmentIsNotProvided() {

        String baseUrl = ConfigManager.getBaseUrl();

        assertThat(baseUrl, equalTo("http://localhost:8081"));
    }

    @Test(expectedExceptions = ConfigurationException.class)
    public void shouldThrowExceptionWhenEnvironmentIsInvalid() {

        System.setProperty("env", "production");

        ConfigManager.getBaseUrl();
    }

    @Test(expectedExceptions = ConfigurationException.class)
    public void shouldThrowExceptionWhenEnvironmentUrlIsBlank() {

        System.setProperty("env", "qa");

        ConfigManager.getBaseUrl();
    }

    @Test
    public void shouldReturnAdminUsernameFromConfiguration() {

        String username = ConfigManager.getAdminUsername();

        assertThat(username, equalTo("rajat"));
    }

    @Test
    public void shouldReturnClientIdFromConfiguration() {

        String clientId = ConfigManager.getClientId();

        assertThat(clientId, startsWith("rest-assured"));
    }
}