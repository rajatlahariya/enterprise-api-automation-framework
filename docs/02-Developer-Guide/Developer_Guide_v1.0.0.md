# Enterprise API Automation Framework

# Developer Guide

**Version:** v1.0.0

## Purpose

This guide explains how to set up, configure, execute and extend the
Enterprise API Automation Framework.

## Prerequisites

-   Java 21
-   Maven 3.9+
-   Git
-   Docker
-   PostgreSQL
-   Jenkins (optional)

## Clone

``` bash
git clone <repository-url>
cd enterprise-api-automation-framework
```

## Build

``` bash
mvn clean compile
```

## Execute

``` bash
mvn clean test
```

Run one class:

``` bash
mvn -Dtest=UserClientTest test
```

Run a suite:

``` bash
mvn -Dsurefire.suiteXmlFiles=integration.xml test
```

## Configuration Priority

1.  JVM properties
2.  Environment variables
3.  framework.properties

## Local Setup

Configure API URL, DB URL and credentials before execution.

## Docker

Ensure enterprise-user-api and enterprise-user-api-db containers are
running.

## Jenkins

Configure Maven, Allure and credentials before execution.

## First Test

``` java
UserClient client = new UserClient();
ApiResponse<UserResponse> response = client.createUser(request);
```

Tests should never use REST Assured directly.

## Adding a Client

-   Create under api.client
-   Expose business methods
-   Return ApiResponse`<T>`{=html}

## Database Validation

Use repositories under database/repository.

## Best Practices

-   Business-focused tests
-   Reusable assertions
-   Data factories
-   External configuration
-   Cleanup test data

## Troubleshooting

Authentication: - Verify credentials - Verify API

Database: - Verify JDBC URL - Verify PostgreSQL

Jenkins: - Verify credentials - Verify Docker network - Verify Maven

## Summary

Follow this guide to onboard developers and extend the framework
consistently.
