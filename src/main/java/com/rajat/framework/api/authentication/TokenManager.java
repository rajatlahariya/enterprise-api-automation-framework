package com.rajat.framework.api.authentication;

import com.rajat.framework.api.client.AuthClient;
import com.rajat.framework.api.model.AuthenticationToken;
import com.rajat.framework.api.model.LoginRequest;
import com.rajat.framework.core.configuration.ConfigManager;

public final class TokenManager {

	private static volatile AuthenticationToken cachedToken;
	
    private TokenManager() {
        // Prevent object creation
    }

    public static AuthenticationToken getToken() {

        if (cachedToken == null || cachedToken.isExpired()) {
            synchronized (TokenManager.class) {
                if (cachedToken == null || cachedToken.isExpired()) {
                    cachedToken = generateToken();
                }
            }
        }

        return cachedToken;
    }

    private static AuthenticationToken generateToken() {

        LoginRequest loginRequest = new LoginRequest(
                ConfigManager.getAdminUsername(),
                ConfigManager.getAdminPassword()
        );
        
        return new AuthClient().loginAndGetToken(loginRequest);
    }

    static void clearToken() {
        cachedToken = null;
    }
}