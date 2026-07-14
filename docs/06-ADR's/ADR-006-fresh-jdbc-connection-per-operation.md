# ADR-006: Fresh JDBC Connection Per Operation

**Status:** Accepted\
**Date:** 2026-07-14

## Context

The framework requires database access only for validation during
integration tests. The expected workload is low, operations are
short-lived, and execution is primarily test-driven rather than
high-throughput.

Using a connection pool such as HikariCP would introduce additional
configuration and lifecycle management without providing significant
benefit for the current architecture.

## Decision

Each repository operation opens a new JDBC connection and closes it
immediately after use.

``` text
Repository
    │
    ▼
DatabaseConnectionFactory
    │
    ▼
DriverManager.getConnection()
    │
    ▼
Execute Query
    │
    ▼
Close Connection
```

The connection lifecycle is scoped to a single repository operation.

## Alternatives Considered

### Option 1 -- Shared Static Connection

**Rejected**

Pros: - Minimal connection overhead

Cons: - Not thread-safe - Difficult recovery after failures - Long-lived
resources - Risk of stale connections

### Option 2 -- Connection Pool (HikariCP)

**Deferred**

Pros: - Better performance under heavy load - Efficient connection reuse

Cons: - Extra dependency - Additional configuration - Little measurable
benefit for current validation workload

### Option 3 -- Fresh Connection Per Operation (Chosen)

Pros: - Stateless implementation - Simple lifecycle - Thread-safe by
design - Easy debugging - Minimal infrastructure

Cons: - Small connection creation overhead

## Consequences

### Positive

-   Repository classes remain simple.
-   No connection lifecycle management is required.
-   Parallel test execution is naturally supported.
-   Failures are isolated to individual operations.

### Negative

-   Repeated connection creation is slower than pooling.
-   Future high-volume validation may require revisiting this decision.

## Revisit Criteria

Introduce a connection pool if:

-   Database validation becomes performance-critical.
-   Large integration suites spend significant time opening connections.
-   Multiple repositories perform frequent concurrent operations.

## Related Principles

-   Thread Safety
-   Simplicity First
-   Read-Only Database Validation
-   No Speculative Abstractions

## References

-   Database Layer Reference
-   Design_Principles_v1.0.0.md
-   TECHNICAL_DEBT.md (future consideration for connection pooling)
