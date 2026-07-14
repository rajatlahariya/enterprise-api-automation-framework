package com.rajat.framework.api.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ApiResponse {

    private final int statusCode;
    private final String body;
    private final Map<String, String> headers;
    private final long responseTimeInMs;

    public ApiResponse(int statusCode,
                       String body,
                       Map<String, String> headers,
                       long responseTimeInMs) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers == null
                ? Collections.emptyMap()
                : Collections.unmodifiableMap(new LinkedHashMap<>(headers));
        this.responseTimeInMs = responseTimeInMs;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getHeader(String name) {
        if (name == null) {
            return null;
        }

        return headers.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equalsIgnoreCase(name))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    public long getResponseTimeInMs() {
        return responseTimeInMs;
    }
}