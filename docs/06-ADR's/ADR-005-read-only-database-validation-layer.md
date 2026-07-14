# ADR-005: Read-Only Database Validation Layer

**Status:** Accepted\
**Date:** 2026-07-14

## Context

Integration tests often manipulate database records directly to create
test data or verify results. While direct writes are convenient, they
bypass business rules, validation, auditing, and application workflows,
reducing confidence in end-to-end behavior.

## Decision

The framework uses the database **only for validation**. Test setup and
business operations are performed through the public API whenever
practical.

``` text
Test
 │
 ▼
Business Client
 │
 ▼
Application API
 │
 ▼
Database

Validation Path
Test
 │
 ▼
Repository
 │
 ▼
JDBC
 │
 ▼
Database (Read Only)
```

Repository classes execute read queries to confirm that API operations
produced the expected persisted state.

## Alternatives Considered

### Option 1 -- Read and Write Through SQL

**Rejected**

Pros: - Fast test setup - Simple data manipulation

Cons: - Bypasses business rules - Can create impossible application
states - Reduces confidence in API behavior

### Option 2 -- Read-Only Validation Layer (Chosen)

Pros: - Validates real application behavior - Keeps business logic
inside the application - Produces trustworthy integration tests

Cons: - Test data creation may take slightly longer through the API

## Consequences

### Positive

-   Integration tests verify the complete request lifecycle.
-   Database repositories remain focused on validation.
-   Business logic is exercised consistently.

### Negative

-   Complex test setup may require additional API calls.

## Exceptions

Direct database writes may be acceptable for: - Reference data
initialization - Environment bootstrap - One-time migration scripts

They should not be used as the normal mechanism for business test
execution.

## Related Principles

-   Read-Only Database Validation
-   Business-Oriented Tests
-   Separation of Concerns

## References

-   Database Layer Reference
-   Integration Test Guide
-   Design_Principles_v1.0.0.md
