# ADR-004: HTTP Foundation

## Status

Accepted

## Context

The framework requires a scalable and maintainable HTTP foundation that hides low-level Rest Assured implementation details from test classes.

Tests should express business intent rather than HTTP implementation.

The framework should provide a single place for common request configuration, endpoint management, and response abstraction.

---

## Decision

The HTTP layer will be built around the following components:

- HttpMethod
- RequestSpecificationFactory
- Endpoint
- ApiPathResolver
- ResponseMapper
- ApiResponse

The request lifecycle is:

Test
↓
API Client
↓
RequestSpecificationFactory
↓
Authentication Provider
↓
Rest Assured
↓
ResponseMapper
↓
ApiResponse
↓
Assertions

Tests must never interact with Rest Assured directly.

---

## Component Responsibilities

### HttpMethod

Represents supported HTTP methods as a strongly typed enum.

---

### RequestSpecificationFactory

Creates a new RequestSpecification for every request.

Responsible for:

- Base URI
- Content-Type
- Accept Header

Authentication will be injected in the next phase.

---

### Endpoint

Represents API endpoints as domain objects instead of string constants.

Each endpoint owns only its relative path.

Example:

USERS("/users")

The endpoint also exposes:

getResolvedPath()

which delegates to ApiPathResolver.

---

### ApiPathResolver

Responsible for constructing the final endpoint path.

Current behaviour:

api.version + endpoint.path

Future responsibilities may include:

- API versioning
- Tenant prefixes
- Localization
- Gateway routing

---

### ApiResponse

Framework-owned immutable response model.

The framework deliberately avoids exposing Rest Assured Response outside the HTTP layer.

---

### ResponseMapper

Maps Rest Assured Response into framework-owned ApiResponse.

This isolates third-party library implementation from framework consumers.

---

## Alternatives Considered

### Hardcoded endpoint strings

Rejected because endpoint duplication becomes difficult to maintain.

---

### Constants class

Rejected because endpoints represent domain concepts and may later own metadata.

---

### RequestExecutor

Initially proposed.

Rejected because it did not provide sufficient architectural value.

RequestSpecificationFactory became the reusable abstraction instead.

---

### ApiResponse wrapping Rest Assured Response

Rejected because it leaked third-party implementation into the framework.

---

## Consequences

### Positive

- Centralized request configuration
- Single source of truth for endpoints
- Framework-owned response model
- Cleaner test code
- Reduced duplication
- Future-ready API versioning

### Negative

- Slightly more framework code
- Response mapping layer required

---

## Design Principles Applied

- Single Responsibility Principle
- Information Hiding
- Dependency Containment
- YAGNI
- Separation of Concerns
- Single Source of Truth

---

## Future Work

- Authentication
- Request Filters
- Retry Strategy
- Request Logging
- Response Logging
- Metrics