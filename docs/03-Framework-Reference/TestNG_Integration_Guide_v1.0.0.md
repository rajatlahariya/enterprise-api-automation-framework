# Enterprise API Automation Framework

# TestNG Integration Guide

**Version:** v1.0.0

## Purpose

This document explains how TestNG is integrated into the framework for
organizing, executing, and reporting API tests.

## Why TestNG?

The framework uses TestNG because it provides:

-   Test grouping
-   XML suite execution
-   Parallel execution
-   DataProviders
-   Listeners
-   Retry support
-   Flexible configuration

## Architecture

``` text
TestNG Suite
     │
     ▼
Test Classes
     │
     ▼
Framework Clients
     │
     ▼
Assertions
     │
     ▼
Reporting (Allure/JUnit)
```

## Test Organization

Recommended package layout:

``` text
src/test/java
├── client
├── integration
├── regression
└── smoke
```

## Test Groups

Recommended groups:

-   smoke
-   regression
-   integration
-   database
-   authentication
-   api

Groups allow selective execution from Maven or Jenkins.

## Suite Files

Typical suites:

-   smoke.xml
-   regression.xml
-   integration.xml
-   full.xml

Example:

``` bash
mvn -Dsurefire.suiteXmlFiles=testng/full.xml test
```

## Parallel Execution

Parallel execution should:

-   Use thread-safe framework components
-   Avoid shared mutable state
-   Generate unique test data
-   Clean up created resources

## Data Providers

Use DataProviders for:

-   Parameterized API validation
-   Boundary value testing
-   Multiple user roles
-   Negative scenarios

Keep providers deterministic and readable.

## Listeners

Framework listeners can be used for:

-   Execution logging
-   Allure integration
-   Environment capture
-   Custom reporting

## Retry Integration

Retry logic belongs in RetryExecutor or dedicated retry mechanisms, not
inside individual test methods.

## Best Practices

-   One business scenario per test.
-   Use descriptive test names.
-   Keep assertions reusable.
-   Separate smoke from regression.
-   Tag integration tests consistently.

## Common Issues

-   Missing groups
-   Incorrect suite configuration
-   Parallel data collisions
-   Listener registration problems

## Summary

TestNG provides the execution backbone of the framework, enabling
scalable, maintainable, and CI-friendly API test execution through
suites, groups, listeners, and parallel support.
