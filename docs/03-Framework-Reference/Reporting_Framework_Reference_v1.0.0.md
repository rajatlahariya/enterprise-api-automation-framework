# Enterprise API Automation Framework

# Reporting Framework Reference

**Version:** v1.0.0

## Purpose

The Reporting Framework provides centralized test reporting for local
execution and CI pipelines. It produces consistent, readable reports
that help developers and QA engineers analyze failures and execution
trends.

## Objectives

-   Centralize reporting
-   Improve failure analysis
-   Provide CI-friendly artifacts
-   Capture execution evidence
-   Support Allure reporting

## Reporting Architecture

``` text
Test Execution
      │
      ▼
TestNG
      │
      ▼
Framework Listeners
      │
      ▼
Allure Lifecycle
      │
      ▼
allure-results
      │
      ▼
Allure Report
      │
      ▼
Jenkins
```

## Components

### TestNG Listeners

Responsibilities:

-   Observe test execution
-   Capture test lifecycle events
-   Publish execution metadata

### Allure Integration

Responsibilities:

-   Generate test reports
-   Record execution history
-   Display failures
-   Organize suites and test cases

### Attachment Manager

Attachments may include:

-   Request payload
-   Response payload
-   HTTP headers
-   JSON responses
-   Stack traces
-   Custom text attachments

Attachments should provide enough context to debug failures without
reproducing the issue.

### Environment Information

The framework publishes execution metadata such as:

-   Environment
-   Java version
-   Maven version
-   Operating system
-   Framework version
-   Execution time

## Report Generation

Local execution:

``` bash
mvn clean test
allure serve allure-results
```

Generate a static report:

``` bash
allure generate allure-results --clean
```

## Jenkins Integration

The Jenkins pipeline should:

1.  Execute the test suite
2.  Archive JUnit reports
3.  Archive Allure results
4.  Publish the Allure report

Typical artifacts:

-   Surefire XML
-   Allure HTML
-   Build logs

## Failure Analysis

Reports should help identify:

-   Assertion failures
-   Authentication failures
-   Configuration issues
-   Database validation failures
-   API contract failures
-   Timeout or retry failures

## Best Practices

-   Attach request and response information for API failures.
-   Keep reports deterministic.
-   Do not expose credentials or secrets.
-   Publish reports for every CI build.
-   Archive reports for historical analysis.

## Troubleshooting

### Empty Report

Verify:

-   Tests executed
-   `allure-results` exists
-   Allure plugin is installed

### Missing Attachments

Verify:

-   Listener registration
-   Attachment utility usage
-   Report generation step

### Jenkins Report Missing

Verify:

-   Allure plugin configuration
-   Artifact paths
-   Post-build actions

## Summary

The Reporting Framework provides a standardized mechanism for
collecting, enriching, and publishing execution results, enabling
efficient debugging and reliable visibility into framework health across
local and CI environments.
