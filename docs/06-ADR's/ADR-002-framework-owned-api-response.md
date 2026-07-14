# ADR-002: Framework-Owned ApiResponse

**Status:** Accepted\
**Date:** 2026-07-14

## Context

REST Assured returns its own `Response` type. Exposing this type outside
the transport layer would couple the framework API to a third-party
library and make future migration difficult.

## Decision

The framework exposes a generic `ApiResponse<T>` as its public response
contract.

``` text
REST Assured Response
        │
        ▼
 ResponseMapper
        │
        ▼
  ApiResponse<T>
        │
        ▼
   Test / Client
```

All business clients return `ApiResponse<T>` instead of
`io.restassured.response.Response`.

## Alternatives Considered

### Option 1 -- Return REST Assured Response

**Rejected**

Pros: - No mapping layer - Minimal implementation

Cons: - Third-party dependency leaks - Inconsistent response handling -
Difficult to replace HTTP library - Tests become transport-aware

### Option 2 -- Framework-Owned ApiResponse (Chosen)

Pros: - Stable public contract - Generic type safety - Consistent
metadata access - Easier future migration

Cons: - Requires response mapping - Additional maintenance for the
response model

## Consequences

### Positive

-   Framework users depend only on framework types.
-   HTTP implementation can evolve independently.
-   Assertions can be written against a consistent response model.

### Negative

-   Every HTTP response must be mapped before being returned.

## Related Principles

-   Framework Owns Public Contracts
-   Encapsulation
-   Separation of Concerns

## References

-   Design_Principles_v1.0.0.md
-   Response Mapping & ApiResponse Reference
-   Architecture_Guide_v1.0.0.md
