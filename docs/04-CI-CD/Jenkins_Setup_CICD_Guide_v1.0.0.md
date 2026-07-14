# Enterprise API Automation Framework

# Jenkins Setup & CI/CD Guide

**Version:** v1.0.0

## Purpose

This guide documents the complete Jenkins setup used by the Enterprise
API Automation Framework, from installation to pipeline execution. It is
based on the project's actual CI/CD implementation.

## Architecture

``` text
Developer
    │
    ▼
GitHub Repository
    │
    ▼
Jenkins (Docker)
    │
    ├── Checkout Source
    ├── Verify Tools
    ├── Verify Services
    ├── Execute Tests
    ├── Publish JUnit
    ├── Generate Allure
    └── Archive Artifacts
```

## Prerequisites

-   Docker Desktop
-   Java 21
-   Maven 3.9
-   Git
-   Allure CLI
-   GitHub repository
-   Running API container
-   Running PostgreSQL container

## Jenkins Installation

Recommended approach:

``` bash
docker run -d \
  --name jenkins \
  -p 8080:8080 \
  -p 50000:50000 \
  rajat/jenkins-with-docker:latest
```

The custom image should include:

-   Docker CLI
-   Git
-   Bash
-   Java 21

## Global Tool Configuration

Configure:

### JDK

-   Name: `Java21`
-   Version: Temurin/OpenJDK 21

### Maven

-   Name: `Maven 3.9`

### Allure

-   Install Allure CLI
-   Configure under Global Tool Configuration

## Required Plugins

-   Pipeline
-   Git
-   Maven Integration
-   JUnit
-   Allure
-   Credentials
-   Docker Pipeline (optional)

## Credentials

Store credentials in Jenkins Credentials Store.

Examples:

-   Git credentials (if private repository)
-   API username/password
-   Database username/password

Never hardcode credentials in the Jenkinsfile.

## Docker Networking

Containers should share the same Docker network.

Example services:

-   enterprise-user-api
-   enterprise-user-api-db
-   jenkins

Use service names instead of localhost inside containers.

## Pipeline Stages

1.  Checkout
2.  Verify Tools
3.  Verify Services
4.  Execute Tests
5.  Publish JUnit
6.  Generate Allure
7.  Archive Artifacts

## Report Publishing

Artifacts:

-   Surefire XML
-   Allure HTML
-   Build Logs

## Common Issues

### Maven Not Found

Cause: - Maven tool not configured.

Resolution: - Configure Maven under Global Tool Configuration.

### Credentials Missing

Cause: - Incorrect credential ID.

Resolution: - Verify Jenkins credential identifiers.

### Docker Service Unreachable

Cause: - Containers not on same network.

Resolution: - Connect containers to the same Docker network and use
service names.

### Allure Report Missing

Cause: - No `allure-results` directory.

Resolution: - Verify test execution and Allure configuration.

## Best Practices

-   Use declarative pipelines.
-   Externalize credentials.
-   Keep pipelines version-controlled.
-   Publish reports for every build.
-   Fail fast on infrastructure issues.

## Summary

The Jenkins pipeline provides repeatable, automated execution of the
framework, integrating source control, test execution, reporting, and
artifact publishing into a consistent CI workflow.
