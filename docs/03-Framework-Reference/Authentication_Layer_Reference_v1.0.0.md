# Enterprise API Automation Framework

# Authentication Layer Reference

**Version:** v1.0.0

## Purpose

The Authentication Layer centralizes all authentication logic so that
test classes and business clients never manage credentials or tokens
directly.

## Objectives

-   Isolate authentication from tests
-   Reuse authentication across all clients
-   Avoid duplicated login requests
-   Support environment-driven credentials
-   Keep authentication implementation replaceable

## Architecture

``` text
Test
 ↓
Business Client
 ↓
RequestSpecificationFactory
 ↓
AuthenticationProvider
 ↓
TokenManager
 ↓
AuthClient
 ↓
Authentication API
```

## Components

### AuthenticationProvider

Responsibilities:

-   Apply authentication to outgoing requests
-   Hide token implementation
-   Keep request creation consistent

### TokenManager

Responsibilities:

-   Obtain access token
-   Cache token for reuse
-   Refresh token when required
-   Prevent unnecessary authentication requests

### AuthClient

Responsibilities:

-   Execute login/authentication request
-   Parse authentication response
-   Return authentication model

### AuthenticationTokenMapper

Responsibilities:

-   Map authentication response into framework model
-   Validate successful authentication
-   Throw framework exceptions on authentication failure

## Authentication Flow

``` text
Business Client
      │
      ▼
RequestSpecificationFactory
      │
      ▼
AuthenticationProvider
      │
      ▼
TokenManager
      │
      ▼
AuthClient
      │
      ▼
POST /auth/login
      │
      ▼
Access Token
      │
      ▼
Authorization: Bearer <token>
```

## Configuration

Authentication credentials should be supplied through external
configuration.

Typical properties:

``` text
auth.admin.username
auth.admin.password
auth.client.id
auth.client.secret
```

Never hardcode credentials in tests or framework classes.

## Error Handling

Authentication failures are reported using:

-   AuthenticationException

Examples:

-   Invalid credentials
-   Missing token
-   Authentication endpoint unavailable
-   Unexpected authentication response

## Security Guidelines

-   Do not log credentials.
-   Do not commit secrets.
-   Externalize usernames and passwords.
-   Prefer environment variables in CI.
-   Mask credentials in Jenkins.

## Extending Authentication

Future versions may introduce additional providers:

-   OAuth2 Client Credentials
-   Basic Authentication
-   API Key Authentication
-   Mutual TLS

The public test API should remain unchanged.

## Best Practices

-   Keep authentication centralized.
-   Reuse cached tokens where appropriate.
-   Avoid authentication logic inside test classes.
-   Fail fast on authentication errors.
-   Return framework-owned models only.

## Summary

The Authentication Layer provides a single, secure, and reusable
mechanism for obtaining and applying authentication across the framework
while keeping test code clean and independent of the underlying
authentication implementation.
