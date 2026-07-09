package com.rajat.framework.api.endpoint;

import com.rajat.framework.core.configuration.ConfigManager;

public final class ApiPathResolver {

    private ApiPathResolver() {
        // Prevent object creation
    }

    public static String resolve(Endpoint endpoint) {

        String apiVersion = ConfigManager.getApiVersion();
        String endpointPath = endpoint.getpath();

        if (apiVersion.isBlank()) {
            return endpointPath;
        }

        return normalize(apiVersion) + normalize(endpointPath);
    }

    private static String normalize(String value) {
        if (value.startsWith("/")) {
            return value;
        }

        return "/" + value;
    }
}