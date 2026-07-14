# Enterprise API Automation Framework

# Configuration & Environment Reference

**Version:** v1.0.0

## Purpose

This document explains how configuration is managed across Local, Docker
and Jenkins environments.

## Configuration Components

### ConfigManager

Responsibilities:

-   Central access point for framework configuration
-   Reads required and optional properties
-   Throws `ConfigurationException` for missing required values
-   Prevents configuration logic from leaking into business code

### EnvironmentManager

Responsibilities:

-   Determines the active environment
-   Reads the `env` system property
-   Resolves the corresponding environment configuration

### Environment

Supported values:

-   local
-   dev
-   qa
-   stage
-   prod (reserved for future use)

## Property Resolution Order

The framework resolves configuration in the following order:

``` text
1. JVM System Properties
        ↓
2. Environment Variables
        ↓
3. framework.properties
```

This allows the same build to run unchanged across developer machines
and CI environments.

## Common Properties

``` properties
# Environment
env=local

# API
base.url=http://localhost:8081

# Authentication
auth.admin.username=
auth.admin.password=

# Database
local.db.url=
db.username=
db.password=
```

## Running Locally

``` bash
mvn clean test \
-Denv=local \
-Dauth.admin.username=admin \
-Dauth.admin.password=password
```

## Docker Execution

Use container hostnames instead of localhost.

Example:

``` text
enterprise-user-api
enterprise-user-api-db
```

## Jenkins Execution

Supply sensitive values using Jenkins Credentials.

Typical parameters:

-   ENVIRONMENT
-   TEST_SUITE

Credentials:

-   API username/password
-   Database username/password

Never commit secrets to Git.

## Security Guidelines

-   Keep credentials external.
-   Mask credentials in CI.
-   Avoid printing secrets to logs.
-   Validate required properties at startup.

## Common Issues

### Missing Property

Cause:

Missing JVM property or framework configuration.

Resolution:

Verify property name and active environment.

### Authentication Failure

Verify:

-   Username
-   Password
-   API availability

### Database Connection Failure

Verify:

-   JDBC URL
-   Username/password
-   PostgreSQL availability
-   Docker networking (if applicable)

## Best Practices

-   Use `ConfigManager` everywhere.
-   Avoid hardcoded URLs.
-   Keep environment-specific values outside source code.
-   Use descriptive property names.
-   Fail fast for mandatory configuration.

## Summary

The configuration subsystem provides a single, secure, and
environment-aware mechanism for managing framework settings across local
development, Docker, and Jenkins while keeping sensitive information
external to the codebase.
