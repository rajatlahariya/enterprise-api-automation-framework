# ADR-003: Business Client Abstraction

**Status:** Accepted\
**Date:** 2026-07-14

## Context

API tests can either build HTTP requests directly or interact through
business-oriented clients. Direct HTTP calls duplicate endpoint
knowledge, headers, authentication, and request construction across test
classes.

## Decision

The framework exposes business clients (for example `UserClient` and
`AuthClient`) as the primary entry point for tests.

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
HTTP Transport
```

Business clients expose operations such as:

``` java
userClient.createUser(request);
userClient.getUser(id);
userClient.updateUser(id, request);
userClient.deleteUser(id);
```

Tests do not construct HTTP requests directly.

## Alternatives Considered

### Option 1 -- HTTP Requests in Tests

**Rejected**

Pros: - Fewer framework classes - Quick to prototype

Cons: - Repeated endpoint logic - Authentication duplicated - Harder
maintenance - Business intent hidden by transport details

### Option 2 -- Business Client Layer (Chosen)

Pros: - Readable tests - Centralized endpoint knowledge - Reusable
business operations - Easier API evolution

Cons: - Requires client implementation for new endpoints

## Consequences

### Positive

-   Tests describe business scenarios instead of HTTP mechanics.
-   Endpoint changes are localized to client implementations.
-   Common request behavior is reused consistently.

### Negative

-   New APIs require corresponding client methods before use.

## Related Principles

-   Business-Oriented Tests
-   Separation of Concerns
-   Single Responsibility
-   Encapsulation

## References

-   Design_Principles_v1.0.0.md
-   Architecture_Guide_v1.0.0.md
-   API Layer Reference
-   Client Layer Deep Dive
