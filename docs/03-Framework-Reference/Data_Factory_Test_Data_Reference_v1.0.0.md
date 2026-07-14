# Enterprise API Automation Framework

# Data Factory & Test Data Management Reference

**Version:** v1.0.0

## Purpose

The Data Factory layer centralizes creation of reusable, deterministic,
and isolated test data. Tests should request valid business objects from
factories instead of manually constructing payloads.

## Objectives

-   Eliminate duplicated test data
-   Produce valid default objects
-   Generate unique values
-   Support parallel execution
-   Improve readability

## Architecture

``` text
Test
 │
 ▼
Data Factory
 │
 ├── Default Values
 ├── UUID Generator
 ├── Business Rules
 └── Request Model
```

## Responsibilities

### Test Data Factory

-   Build valid request models
-   Provide sensible defaults
-   Allow selective overrides
-   Hide randomization details

### Unique Data Generation

Unique identifiers should be generated using UUIDs for fields that
require uniqueness (such as usernames or email addresses). This avoids
collisions during repeated or parallel execution.

### Deterministic Data

Factories should return predictable defaults unless uniqueness is
explicitly required.

## Data Lifecycle

``` text
Create Request
      │
      ▼
Business Client
      │
      ▼
API
      │
      ▼
Assertions
      │
      ▼
Cleanup
```

## Parallel Execution

Factories must be thread-safe:

-   No shared mutable state
-   Generate unique values per invocation
-   Avoid static counters
-   Prefer UUID-based identifiers

## Cleanup Strategy

When tests create persistent entities:

1.  Create
2.  Validate
3.  Clean up (API or database)

This keeps environments reusable and prevents flaky tests.

## Best Practices

-   Never hardcode unique emails.
-   Keep business defaults in one place.
-   Reuse factories across suites.
-   Separate valid and invalid test data.
-   Keep factory methods small and focused.

## Extension Guide

To add a new request model:

1.  Create the request POJO.
2.  Add a factory method.
3.  Supply valid defaults.
4.  Add override parameters where useful.
5.  Add factory tests.

## Common Issues

### Duplicate Data

Cause: - Hardcoded unique fields.

Resolution: - Use UUID-based generation.

### Flaky Parallel Tests

Cause: - Shared mutable data.

Resolution: - Generate fresh objects per test.

## Summary

The Data Factory layer standardizes test data creation, improves
maintainability, supports parallel execution, and reduces duplication by
providing reusable, business-oriented request builders.
