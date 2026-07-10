package com.rajat.framework.core.exceptions;

public class AuthenticationException extends FrameworkException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}