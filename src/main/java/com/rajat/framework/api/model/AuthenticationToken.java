package com.rajat.framework.api.model;

import java.time.Instant;

public class AuthenticationToken {

    private final String accessToken;
    private final String refreshToken;
    private final String tokenType;
    private final long expiresIn;
    private final Instant createdAt;

    public AuthenticationToken(String accessToken,
                               String refreshToken,
                               String tokenType,
                               long expiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.createdAt = Instant.now();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public boolean isExpired() {
        if (expiresIn <= 0) {
            return false;
        }

        return Instant.now().isAfter(createdAt.plusMillis(expiresIn));
    }
}