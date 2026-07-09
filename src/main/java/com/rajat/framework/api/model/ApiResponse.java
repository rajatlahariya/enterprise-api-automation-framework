package com.rajat.framework.api.model;

import java.util.Collections;
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
        this.headers = headers == null ? Collections.emptyMap() : Collections.unmodifiableMap(headers);
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
        return headers.get(name);
    }

    public long getResponseTimeInMs() {
        return responseTimeInMs;
    }
}