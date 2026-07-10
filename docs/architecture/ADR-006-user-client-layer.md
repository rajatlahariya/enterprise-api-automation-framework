# ADR-006: User Business Client Layer

## Status

Accepted

## Context

Tests need to perform user operations without directly using Rest Assured, endpoint strings, authentication logic, or JSON parsing.

The framework requires business-facing models and clients that expose readable user operations.

## Decision

The user domain will use:

- `User`
- `CreateUserRequest`
- `UpdateUserRequest`
- `UserMapper`
- `UserClient`
- `UserDataFactory`

`UserClient` exposes business operations:

- `createUser`
- `getUserById`
- `updateUser`
- `deleteUser`

Tests do not use Rest Assured directly.

## Design Decisions

- Models represent the API contract, not the database entity.
- Models are immutable.
- Clients return domain objects where possible.
- DELETE returns `ApiResponse` because the successful backend response has no body.
- JSON parsing is delegated to `UserMapper`.
- Test data creation is centralized in `UserDataFactory`.
- CRUD tests create and manage their own data.

## Consequences

### Positive

- Business-readable tests
- No hardcoded user IDs
- Centralized test data
- Reduced duplication
- Strong typing
- Clean separation between HTTP and domain layers

### Negative

- Explicit mapper code is required
- More model classes than map-based payloads

## Principles Applied

- Single Responsibility Principle
- Test Data Ownership
- Information Hiding
- Strong Typing
- Separation of Concerns