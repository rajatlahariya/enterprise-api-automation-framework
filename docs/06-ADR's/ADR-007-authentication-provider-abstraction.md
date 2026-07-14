# ADR-007: Authentication Provider Abstraction

**Status:** Accepted\
**Date:** 2026-07-14

## Context

The framework must support different authentication mechanisms without
changing business clients or test code. Embedding authentication logic
directly into clients would duplicate code and make future
authentication changes difficult.

## Decision

Authentication is delegated to an `AuthenticationProvider` abstraction.
Business clients request authenticated specifications without knowing
how credentials or tokens are obtained.

``` text
Test
 │
 ▼
Business Client
 │
 ▼
RequestSpecificationFactory
 │
 ▼
AuthenticationProvider
 ├── Basic Authentication
 ├── Bearer Token
 └── OAuth2 (Future)
 │
 ▼
REST Assured
```

The authentication layer owns:

-   Credential retrieval
-   Token acquisition
-   Token refresh (future)
-   Authorization header construction

Business clients never construct authorization headers.

## Alternatives Considered

### Option 1 -- Authentication inside every Client

**Rejected**

Pros: - Simple initial implementation - Fewer classes

Cons: - Duplicated logic - Difficult maintenance - Hard to add OAuth2 -
Clients become responsible for infrastructure concerns

### Option 2 -- Authentication Provider (Chosen)

Pros: - Single authentication implementation - Easy to add new
mechanisms - Cleaner clients - Better separation of concerns - Improved
testability

Cons: - One additional abstraction

## Consequences

### Positive

-   Business clients remain focused on business operations.
-   New authentication mechanisms can be introduced with minimal impact.
-   Credentials remain centralized.

### Negative

-   All authentication strategies must conform to the provider contract.

## Future Evolution

The abstraction is designed to support:

-   Basic Authentication
-   Bearer Token Authentication
-   OAuth2 Client Credentials
-   OAuth2 Authorization Code
-   API Key Authentication
-   mTLS (if required)

These additions should not require changes to existing business clients.

## Related Principles

-   Separation of Concerns
-   Single Responsibility
-   Encapsulation
-   Configuration is External

## References

-   Authentication Layer Reference
-   Request Specification Factory Reference
-   Design_Principles_v1.0.0.md
