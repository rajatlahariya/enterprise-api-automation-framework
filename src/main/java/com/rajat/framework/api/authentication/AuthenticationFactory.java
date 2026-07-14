package com.rajat.framework.api.authentication;

import com.rajat.framework.core.configuration.ConfigManager;

public final class AuthenticationFactory {

    private AuthenticationFactory() {
        // Prevent object creation
    }

    public static AuthenticationProvider noAuth() {
        return new NoAuthenticationProvider();
    }

    public static AuthenticationProvider basic() {
        return new BasicAuthenticationProvider(
                ConfigManager.getAdminUsername(),
                ConfigManager.getAdminPassword()
        );
    }

    public static AuthenticationProvider bearerToken(String token) {
        return new BearerTokenAuthenticationProvider(token);
    }
}