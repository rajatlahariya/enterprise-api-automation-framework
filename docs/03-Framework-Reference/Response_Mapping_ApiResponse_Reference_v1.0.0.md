# Enterprise API Automation Framework

# Response Mapping & ApiResponse Reference

**Version:** v1.0.0

## Purpose

The Response Mapping layer converts raw REST Assured responses into
framework-owned response objects. Tests interact only with
`ApiResponse<T>` and remain independent of the underlying HTTP library.

## Why ApiResponse Exists

The framework intentionally hides REST Assured to:

-   Prevent framework leakage into tests
-   Provide a stable public API
-   Enable future HTTP library replacement
-   Standardize response handling

## Architecture

``` text
REST Assured Response
        │
        ▼
ResponseMapper
        │
        ▼
ApiResponse<T>
        │
        ├── Status Code
        ├── Headers
        ├── Body
        └── Response Time
```

## Components

### ResponseMapper

Responsibilities:

-   Convert REST Assured responses
-   Deserialize response body
-   Extract headers
-   Record response time
-   Create immutable ApiResponse objects

### ApiResponse`<T>`{=html}

Responsibilities:

-   Hold strongly typed response body
-   Expose HTTP metadata
-   Prevent accidental mutation
-   Provide a framework-owned contract

## Generic Response Model

``` java
ApiResponse<UserResponse> response = userClient.getUser(id);
```

The generic model provides compile-time type safety and removes casting
from tests.

## Response Lifecycle

``` text
HTTP Response
      │
      ▼
ResponseMapper
      │
      ▼
Deserialize JSON
      │
      ▼
ApiResponse<T>
      │
      ▼
Assertions
```

## Immutability

ApiResponse is designed to be immutable.

Recommendations:

-   Defensive copy mutable collections
-   Do not expose mutable internals
-   Prefer constructor initialization

## Header Handling

Headers should be:

-   Case-insensitive
-   Exposed through framework methods
-   Detached from REST Assured types

## Response Time

The mapper records execution time once and stores it in ApiResponse for
reporting and assertions.

## Error Handling

Mapping failures should result in framework exceptions with preserved
root causes.

## Best Practices

-   Never return REST Assured Response.
-   Keep mapping logic centralized.
-   Return strongly typed models.
-   Avoid duplicate deserialization.

## Extension Guide

To support a new response type:

1.  Create a POJO.
2.  Update mapping logic if required.
3.  Return `ApiResponse<NewType>`.
4.  Add mapper tests.

## Summary

The Response Mapping layer separates transport concerns from business
tests, providing a stable, type-safe, and immutable response model that
simplifies assertions and future framework evolution.
