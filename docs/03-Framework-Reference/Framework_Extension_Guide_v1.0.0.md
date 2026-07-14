# Enterprise API Automation Framework

# Framework Extension Guide

**Version:** v1.0.0

## Purpose

This guide defines the standard process for extending the framework
while preserving the architecture, coding standards, and dependency
rules. All new capabilities should follow the patterns described here to
maintain consistency and long-term maintainability.

## Design Principles

Every extension should:

-   Follow the layered architecture
-   Preserve separation of concerns
-   Avoid leaking third-party libraries
-   Reuse existing framework abstractions
-   Remain thread-safe
-   Be fully testable

## Extension Workflow

``` text
Requirement
    │
    ▼
Design Review
    │
    ▼
Create Models
    │
    ▼
Implement Framework Component
    │
    ▼
Unit Tests
    │
    ▼
Integration Tests
    │
    ▼
Documentation
    │
    ▼
Pull Request
```

## Adding a New API Resource

Example: Product API

Steps:

1.  Create request POJOs.
2.  Create response POJOs.
3.  Add endpoint definitions.
4.  Create ProductClient.
5.  Implement business methods.
6.  Add assertions.
7.  Add integration tests.
8.  Add documentation.

## Adding Authentication

Supported patterns may include:

-   Bearer Token
-   Basic Authentication
-   OAuth2 Client Credentials
-   API Keys

Implementation guidelines:

-   Authentication logic belongs only in the authentication layer.
-   Business clients must remain unchanged.

## Adding a Repository

1.  Create RowMapper.
2.  Create Repository.
3.  Reuse DatabaseQueryExecutor.
4.  Write repository tests.
5.  Use repository only from integration tests.

## Adding Assertions

Reusable assertions should:

-   Accept ApiResponse`<T>`{=html}
-   Produce descriptive failure messages
-   Avoid business-specific assumptions

## Adding Configuration

New configuration values should:

-   Be documented
-   Have clear names
-   Be externalized
-   Validate required properties

## Thread Safety

New components must:

-   Avoid shared mutable state
-   Prefer immutable models
-   Avoid static caches unless synchronized

## Coding Standards

-   Small focused classes
-   Single responsibility
-   Constructor injection where applicable
-   Clear exception messages
-   Meaningful package placement

## Review Checklist

Before merging:

-   Architecture respected
-   Tests passing
-   Documentation updated
-   No forbidden dependencies
-   No duplicated logic
-   Code reviewed

## Common Anti-Patterns

Avoid:

-   Direct REST Assured usage in tests
-   JDBC in test classes
-   Hardcoded credentials
-   Configuration inside business clients
-   Utility classes containing business logic

## Summary

Following this guide ensures that every new feature integrates cleanly
with the framework while preserving its architectural integrity,
readability, and maintainability.
