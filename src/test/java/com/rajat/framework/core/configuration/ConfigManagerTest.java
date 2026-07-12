package com.rajat.framework.core.configuration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.rajat.framework.core.exceptions.ConfigurationException;
import com.rajat.framework.testgroup.TestGroups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.lang.reflect.Method;

@Test(groups = { TestGroups.UNIT, TestGroups.REGRESSION })
public class ConfigManagerTest {

	@AfterMethod
	public void cleanUp() {
		System.clearProperty("env");
	}

	@Test(enabled=false)
	public void shouldReturnLocalBaseUrlWhenEnvironmentIsNotProvided() {

		String baseUrl = ConfigManager.getBaseUrl();

		assertThat(baseUrl, equalTo("http://localhost:8081"));
	}

	@Test(expectedExceptions = ConfigurationException.class, groups = { TestGroups.NEGATIVE })
	public void shouldThrowExceptionWhenEnvironmentIsInvalid() {

		System.setProperty("env", "production");

		ConfigManager.getBaseUrl();
	}

	@Test(expectedExceptions = ConfigurationException.class, groups = { TestGroups.NEGATIVE })
	public void shouldThrowExceptionWhenEnvironmentUrlIsBlank() {

		System.setProperty("env", "qa");

		ConfigManager.getBaseUrl();
	}

	@Test
	public void shouldReturnAdminUsernameFromConfiguration() {

		String username = ConfigManager.getAdminUsername();

		assertThat(username, equalTo("rajat"));
	}

	@Test(enabled=false)
	public void shouldReturnClientIdFromConfiguration() {

		String clientId = ConfigManager.getClientId();

		assertThat(clientId, startsWith("rest-assured"));
	}

	@Test
	public void shouldPreferSystemPropertyOverConfigurationFile() {

		String key = "auth.admin.username";
		String originalValue = System.getProperty(key);

		try {
			System.setProperty(key, "system-user");

			assertThat(ConfigManager.getAdminUsername(), equalTo("system-user"));

		} finally {
			if (originalValue == null) {
				System.clearProperty(key);
			} else {
				System.setProperty(key, originalValue);
			}
		}
	}

	@Test
	public void shouldConvertPropertyKeyToEnvironmentVariableFormat() throws Exception {

		Method method = ConfigManager.class.getDeclaredMethod("toEnvironmentVariableName", String.class);

		method.setAccessible(true);

		String result = (String) method.invoke(null, "auth.admin.username");

		assertThat(result, equalTo("AUTH_ADMIN_USERNAME"));
	}
}