package com.rajat.framework.core.environment;

public final class EnvironmentManager {

    private static final String ENV_PROPERTY = "env";

    private EnvironmentManager() {
        // Prevent object creation
    }

    public static Environment getActiveEnvironment() {
        String environmentValue = System.getProperty(ENV_PROPERTY);
        return Environment.from(environmentValue);
    }
}