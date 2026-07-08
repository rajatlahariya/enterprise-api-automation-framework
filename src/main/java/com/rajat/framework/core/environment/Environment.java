package com.rajat.framework.core.environment;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.rajat.framework.core.exceptions.ConfigurationException;

public enum Environment {

    LOCAL,
    DEV,
    QA,
    STAGE,
    PROD;

    public static Environment from(String value) {
        if (value == null || value.isBlank()) {
            return LOCAL;
        }

        try {
            return Environment.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new ConfigurationException(
                    "Invalid environment: " + value + ". Supported environments are: " + supportedValues(),
                    exception
            );
        }
    }

    private static String supportedValues() {
        return Arrays.stream(Environment.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}