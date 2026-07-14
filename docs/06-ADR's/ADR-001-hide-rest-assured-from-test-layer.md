# ADR-001: Hide REST Assured from Test Layer

**Status:** Accepted\
**Date:** 2026-07-14

## Context

The framework uses REST Assured for HTTP communication. Exposing REST
Assured directly to test classes would tightly couple business tests to
a third-party library, making future migrations expensive and
encouraging low-level HTTP logic in tests.

## Decision

REST Assured is an internal implementation detail.

Tests interact only with framework business clients (for example,
`UserClient`) and receive framework-owned `ApiResponse<T>` objects.

``` text
Test
  │
  ▼
Business Client
  │
  ▼
Request Specification Factory
  │
  ▼
REST Assured (internal)
  │
  ▼
ResponseMapper
  │
  ▼
ApiResponse<T>
```

## Alternatives Considered

### Option 1 -- Use REST Assured directly in tests

**Rejected**

Pros: - Less framework code - Familiar syntax

Cons: - HTTP details leak into tests - Difficult to replace REST
Assured - Repeated request construction - Inconsistent assertions

### Option 2 -- Wrap REST Assured (Chosen)

Pros: - Stable public API - Business-oriented tests - Easier
maintenance - Vendor independence at the framework boundary

Cons: - Additional abstraction layer - Slight increase in implementation
code

## Consequences

Positive: - Tests remain readable and focused on business behavior. -
HTTP implementation can evolve without changing tests. - Common request
configuration is centralized.

Negative: - New API operations require client methods before they are
available to tests.

## Related Principles

-   Business-Oriented Tests
-   Framework-Owned Public Contracts
-   Separation of Concerns
-   Encapsulation

## References

-   Design_Principles_v1.0.0.md
-   Architecture_Guide_v1.0.0.md
-   API Layer Reference
