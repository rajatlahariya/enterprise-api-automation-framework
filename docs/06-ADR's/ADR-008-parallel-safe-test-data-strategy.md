# ADR-008: Parallel-Safe Test Data Strategy

**Status:** Accepted\
**Date:** 2026-07-14

## Context

The framework is designed to support parallel execution using TestNG.
When multiple tests execute simultaneously, shared test data such as
fixed usernames or email addresses can lead to unique constraint
violations, flaky tests, and inconsistent results.

## Decision

The framework generates unique test data for every test execution.

Typical identifiers include:

-   Email addresses
-   Usernames
-   External IDs
-   Correlation IDs

Generation is delegated to centralized test data factories rather than
individual test classes.

``` text
Test
 │
 ▼
Data Factory
 │
 ├── UUID
 ├── Timestamp
 ├── Random Values
 └── Business Defaults
 │
 ▼
Business Client
 │
 ▼
Application
```

## Alternatives Considered

### Option 1 -- Static Test Data

**Rejected**

Pros: - Easy to write - Predictable values

Cons: - Unique constraint violations - Parallel execution failures -
Data cleanup becomes difficult - Flaky integration tests

### Option 2 -- Centralized Unique Test Data (Chosen)

Pros: - Reliable parallel execution - Minimal data collisions - Reusable
generation logic - Easier maintenance

Cons: - Generated values are less human-readable - Test data factories
require maintenance

## Consequences

### Positive

-   Tests can execute concurrently with minimal interference.
-   Database uniqueness constraints are respected.
-   Test classes remain concise.

### Negative

-   Debugging may require tracing generated identifiers.
-   Assertions should avoid relying on hardcoded values.

## Implementation Guidelines

-   Generate identifiers centrally.
-   Avoid random generation inside tests.
-   Keep generation deterministic where possible.
-   Use UUIDs only where uniqueness is required.

## Related Principles

-   Thread Safety
-   Business-Oriented Tests
-   No Shared Mutable State

## References

-   Data Factory & Test Data Management Reference
-   TestNG Integration Guide
-   Design_Principles_v1.0.0.md
