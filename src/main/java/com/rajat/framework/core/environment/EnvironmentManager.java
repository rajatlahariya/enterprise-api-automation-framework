package com.rajat.framework.core.environment;

public final class EnvironmentManager {

	private static final String ENV_PROPERTY = "env";
	private static final String ENV_VARIABLE = "ENV";
	private static final String DEFAULT_ENVIRONMENT = "local";

	private EnvironmentManager() {
		// Prevent object creation
	}

	public static Environment getActiveEnvironment() {

		String systemProperty = System.getProperty(ENV_PROPERTY);

		if (systemProperty != null && !systemProperty.isBlank()) {

			return Environment.from(systemProperty.trim());
		}

		String environmentVariable = System.getenv(ENV_VARIABLE);

		if (environmentVariable != null && !environmentVariable.isBlank()) {

			return Environment.from(environmentVariable.trim());
		}

		return Environment.from(DEFAULT_ENVIRONMENT);
	}
}