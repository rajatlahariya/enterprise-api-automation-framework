package com.rajat.framework.core.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.rajat.framework.core.environment.Environment;
import com.rajat.framework.core.environment.EnvironmentManager;
import com.rajat.framework.core.exceptions.ConfigurationException;

public final class ConfigManager {

    private static final String CONFIG_FILE = "framework.properties";

    private ConfigManager() {
        // Prevent object creation
    }

    public static String getBaseUrl() {
        Environment environment = EnvironmentManager.getActiveEnvironment();
        String key = environment.name().toLowerCase() + ".base.url";
        return getRequiredProperty(key);
    }

    public static String getAdminUsername() {
        return getRequiredProperty("auth.admin.username");
    }

    public static String getAdminPassword() {
        return getRequiredProperty("auth.admin.password");
    }

    public static String getUsername() {
        return getAdminUsername();
    }

    public static String getPassword() {
        return getAdminPassword();
    }

    public static String getClientId() {
        return getRequiredProperty("auth.client.id");
    }

    public static String getClientSecret() {
        return getRequiredProperty("auth.client.secret");
    }

    public static String getApiVersion() {
        String value = getProperties().getProperty("api.version");

        if (value == null || value.isBlank()) {
            return "";
        }

        return value.trim();
    }

    private static String getRequiredProperty(String key) {
        String value = getProperties().getProperty(key);

        if (value == null || value.isBlank()) {
            throw new ConfigurationException(
                    "Missing or blank configuration property: " + key
            );
        }

        return value.trim();
    }

    private static Properties getProperties() {
        return PropertiesHolder.PROPERTIES;
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream inputStream = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {

            if (inputStream == null) {
                throw new ConfigurationException(
                        "Configuration file not found: " + CONFIG_FILE
                );
            }

            properties.load(inputStream);
            return properties;

        } catch (IOException exception) {
            throw new ConfigurationException(
                    "Failed to load configuration file: " + CONFIG_FILE,
                    exception
            );
        }
    }

    private static final class PropertiesHolder {

        private static final Properties PROPERTIES = loadProperties();

        private PropertiesHolder() {
            // Prevent object creation
        }
    }
}
