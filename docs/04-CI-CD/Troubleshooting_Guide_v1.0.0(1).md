# Enterprise API Automation Framework

# Troubleshooting Guide

**Version:** v1.0.0

## Purpose

This guide documents the most common problems encountered while
developing and running the framework and provides proven resolutions.

------------------------------------------------------------------------

# Troubleshooting Workflow

``` text
Failure
   │
   ▼
Read Error
   │
   ▼
Identify Layer
   │
   ├── Configuration
   ├── API
   ├── Database
   ├── Docker
   ├── Jenkins
   └── Framework
   │
   ▼
Apply Fix
   │
   ▼
Re-run
```

------------------------------------------------------------------------

## Configuration Issues

### Missing ConfigurationException

**Symptom**

    Missing or blank configuration property

**Cause**

-   Missing JVM property
-   Missing environment variable
-   Missing property in `framework.properties`

**Resolution**

-   Verify active environment (`-Denv`)
-   Check property names
-   Ensure required properties exist

------------------------------------------------------------------------

## Database Issues

### PostgreSQL Authentication Failed

**Symptom**

    FATAL: password authentication failed

**Cause**

-   Incorrect username/password
-   Wrong database user
-   Incorrect Docker environment variables

**Resolution**

-   Verify `db.username`
-   Verify `db.password`
-   Check container configuration
-   Confirm credentials in Jenkins

------------------------------------------------------------------------

### Duplicate Key Violation

**Symptom**

    duplicate key value violates unique constraint

**Cause**

-   Hardcoded test data

**Resolution**

-   Generate unique data using UUIDs
-   Clean up created records

------------------------------------------------------------------------

## API Issues

### Authentication Failure

**Symptom**

401 Unauthorized

**Resolution**

-   Verify API credentials
-   Verify authentication endpoint
-   Check token generation

------------------------------------------------------------------------

### Soft Delete Validation Failure

**Cause**

API behavior and test expectation differ.

**Resolution**

-   Verify API contract
-   Confirm soft delete implementation
-   Align integration tests with business behavior

------------------------------------------------------------------------

## Docker Issues

### Service Not Reachable

**Cause**

Containers are not on the same Docker network.

**Resolution**

``` bash
docker network connect enterprise-network jenkins
```

Use service names instead of `localhost`.

------------------------------------------------------------------------

### Hostname Not Resolved

Inside Docker:

    enterprise-user-api

Outside Docker:

    localhost

Never mix the two.

------------------------------------------------------------------------

## Jenkins Issues

### Maven Not Found

Configure Maven under:

    Manage Jenkins
    → Tools

------------------------------------------------------------------------

### Credential ID Not Found

Verify:

-   Credential exists
-   Correct ID
-   Pipeline reference

------------------------------------------------------------------------

### Allure Results Missing

Verify:

-   Tests executed
-   `allure-results` directory created
-   Post-build step configured

------------------------------------------------------------------------

## Framework Issues

### REST Assured Leakage

Tests should never import or use REST Assured directly.

Use business clients only.

------------------------------------------------------------------------

### Database Validation Failures

Verify:

-   Repository SQL
-   RowMapper
-   Test environment
-   Cleanup strategy

------------------------------------------------------------------------

## Debug Checklist

-   Environment selected correctly
-   API reachable
-   Database reachable
-   Credentials valid
-   Docker network healthy
-   Jenkins tools installed
-   Tests isolated
-   Reports generated

------------------------------------------------------------------------

## Preventive Practices

-   Externalize configuration
-   Use UUID-based data
-   Keep Docker services healthy
-   Keep CI credentials updated
-   Publish reports for every build

------------------------------------------------------------------------

## Summary

Most framework issues originate from configuration, networking,
credentials, or environment mismatches. Following the diagnostic
workflow above helps isolate problems quickly and ensures consistent
execution across local development, Docker, and Jenkins.
