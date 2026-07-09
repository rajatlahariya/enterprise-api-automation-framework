package com.rajat.framework.api.endpoint;

public enum Endpoint {

    HEALTH("/actuator/health"),
    USERS("/users"),
    USER_BY_ID("/users/{id}"),
    LOGIN("/auth/login"),
    CLIENT_TOKEN("/auth/client-token");

    private final String path;

    Endpoint(String path) {
        this.path = path;
    }

    public String getpath() {
        return path;
    }
    
    public String getResolvedPath() {
        return ApiPathResolver.resolve(this);
    }
}
