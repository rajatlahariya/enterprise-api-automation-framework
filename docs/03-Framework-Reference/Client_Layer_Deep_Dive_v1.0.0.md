# Enterprise API Automation Framework

# Client Layer Deep Dive

**Version:** v1.0.0

## Purpose

The Client Layer is the public API of the framework. Test classes
interact only with business clients and never communicate directly with
REST Assured.

## Design Philosophy

The framework exposes business operations rather than HTTP operations.

Preferred:

``` java
userClient.createUser(request);
userClient.getUser(id);
userClient.updateUser(id, request);
userClient.deleteUser(id);
```

Avoid exposing:

``` java
get();
post();
put();
delete();
```

## Layer Position

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
Authentication
 │
 ▼
REST Assured
```

## Responsibilities

### BaseClient

-   Shared request creation
-   Authentication integration
-   Common request specifications
-   Common response handling

### Business Clients

Examples:

-   UserClient
-   AuthClient

Responsibilities:

-   Business operations
-   Endpoint orchestration
-   Request execution
-   Response mapping

## Request Lifecycle

``` text
Test
 ↓
UserClient
 ↓
RequestSpecificationFactory
 ↓
Authentication
 ↓
REST Assured
 ↓
ResponseMapper
 ↓
ApiResponse<T>
```

## Design Rules

-   Stateless clients
-   No shared mutable state
-   No business assertions
-   No JDBC access
-   No configuration logic

## Error Handling

Clients throw framework exceptions only.

Never expose REST Assured exceptions to test classes.

## Retry Integration

Retry logic should remain external to business methods unless a retry
policy is explicitly part of the client contract.

## Extending the Layer

To introduce a new resource:

1.  Create endpoint constants.
2.  Create request/response models.
3.  Create a business client.
4.  Add reusable assertions.
5.  Add integration tests.

## Best Practices

-   One responsibility per client.
-   Keep methods business-oriented.
-   Return `ApiResponse<T>`.
-   Keep REST Assured hidden.
-   Reuse request specifications.

## Summary

The Client Layer isolates tests from transport details and provides a
stable, business-focused interface that keeps the framework maintainable
and easy to extend.
