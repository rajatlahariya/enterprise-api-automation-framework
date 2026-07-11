package com.rajat.framework.api.model;

public class ErrorResponse {

    private final Boolean success;
    private final String message;

    public ErrorResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}