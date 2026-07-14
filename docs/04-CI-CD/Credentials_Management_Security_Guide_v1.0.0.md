# Enterprise API Automation Framework

# Credentials Management & Security Guide

**Version:** v1.0.0

## Purpose

This guide explains how credentials and sensitive configuration should
be managed across local development, Docker, and Jenkins. The goal is to
keep secrets out of source control while providing a consistent
configuration model.

## Security Principles

-   Never commit secrets to Git.
-   Externalize all credentials.
-   Grant the minimum required privileges.
-   Mask sensitive values in logs.
-   Rotate credentials periodically.

## Credential Sources

The framework resolves configuration in this order:

``` text
1. JVM System Properties
2. Environment Variables
3. framework.properties
```

## Local Development

Store secrets outside the repository.

Example:

``` bash
export DB_USERNAME=postgres
export DB_PASSWORD=postgres
export AUTH_ADMIN_USERNAME=admin
export AUTH_ADMIN_PASSWORD=admin123
```

## Docker

Pass credentials through environment variables.

``` yaml
environment:
  DB_USERNAME: postgres
  DB_PASSWORD: postgres
```

Avoid baking credentials into images.

## Jenkins

Use the Jenkins Credentials Store.

Typical entries:

-   API Username/Password
-   Database Username/Password
-   Git Credentials (private repositories)

Reference them using the `credentials()` helper in the pipeline.

## Database Credentials

Example properties:

``` properties
db.username=${DB_USERNAME}
db.password=${DB_PASSWORD}
```

Use different credentials for local, QA, and production environments.

## API Credentials

Authentication values should be supplied externally and never hardcoded
inside business clients or tests.

## Logging

Never log:

-   Passwords
-   Tokens
-   API keys
-   Connection strings containing secrets

Mask sensitive values before writing logs or reports.

## Rotation Strategy

-   Change credentials regularly.
-   Update Jenkins credentials first.
-   Verify pipeline execution.
-   Remove obsolete secrets.

## Common Mistakes

-   Committing secrets to Git
-   Using localhost inside containers
-   Sharing production credentials
-   Reusing administrator accounts for tests

## Security Checklist

-   Secrets externalized
-   Jenkins credentials configured
-   Environment variables documented
-   No hardcoded passwords
-   CI logs reviewed for secret exposure

## Summary

The framework separates credentials from source code and supports secure
configuration across developer machines, Docker, and Jenkins while
following common enterprise security practices.
