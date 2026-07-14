# Enterprise API Automation Framework

# Configuration Examples Cookbook

**Version:** v1.0.0

## Purpose

This cookbook contains practical configuration examples for running the
framework across different environments.

------------------------------------------------------------------------

## Local Execution

### framework.properties

``` properties
env=local
base.url=http://localhost:8081

local.db.url=jdbc:postgresql://localhost:5433/automationdb
db.username=postgres
db.password=postgres

auth.admin.username=admin
auth.admin.password=admin123
```

Run:

``` bash
mvn clean test -Denv=local
```

------------------------------------------------------------------------

## Docker Execution

Use Docker service names instead of localhost.

``` properties
env=docker
base.url=http://enterprise-user-api:8081

docker.db.url=jdbc:postgresql://enterprise-user-api-db:5432/automationdb
db.username=postgres
db.password=postgres
```

------------------------------------------------------------------------

## Jenkins Pipeline

Example environment variables:

``` groovy
environment {
    ENVIRONMENT = "docker"
    DB_USERNAME = credentials("db-user")
    DB_PASSWORD = credentials("db-password")
}
```

Execution:

``` bash
mvn clean test -Denv=docker
```

------------------------------------------------------------------------

## Environment Variables

Linux/macOS:

``` bash
export DB_USERNAME=postgres
export DB_PASSWORD=postgres
export ENV=local
```

Windows:

``` cmd
set DB_USERNAME=postgres
set DB_PASSWORD=postgres
```

------------------------------------------------------------------------

## Maven Examples

Run one test:

``` bash
mvn -Dtest=UserClientTest test
```

Run suite:

``` bash
mvn -Dsurefire.suiteXmlFiles=testng/full.xml test
```

------------------------------------------------------------------------

## Docker Compose Naming

Recommended service names:

-   enterprise-user-api
-   enterprise-user-api-db
-   jenkins

------------------------------------------------------------------------

## GitHub Actions Example

``` yaml
- name: Execute Tests
  run: mvn clean test -Denv=ci
```

------------------------------------------------------------------------

## Security Guidelines

-   Never commit credentials.
-   Use CI secret stores.
-   Prefer environment variables over hardcoded values.
-   Validate mandatory configuration at startup.

------------------------------------------------------------------------

## Troubleshooting

### ConfigurationException

Check:

-   Active environment
-   Missing properties
-   Property names

### Authentication Failure

Check:

-   Username/password
-   Authentication endpoint
-   Token generation

### Database Failure

Check:

-   JDBC URL
-   Docker networking
-   PostgreSQL credentials

------------------------------------------------------------------------

## Summary

These examples provide recommended configuration patterns for local
development, Docker containers, Jenkins pipelines, and future CI
platforms while keeping sensitive information external to the source
code.
