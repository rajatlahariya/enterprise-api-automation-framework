# Enterprise API Automation Framework

# Request Specification Factory Reference

**Version:** v1.0.0

## Purpose

The Request Specification Factory centralizes construction of REST
Assured request specifications. It ensures every request follows the
same conventions for authentication, headers, content type, logging, and
configuration.

## Why It Exists

Without a centralized factory, every client would duplicate:

-   Base URI
-   Authentication
-   Headers
-   Content-Type
-   Accept headers
-   Logging configuration

Centralization guarantees consistency.

## Architecture

``` text
Business Client
      │
      ▼
RequestSpecificationFactory
      │
      ├── Base URI
      ├── Authentication
      ├── Headers
      ├── Content Type
      └── Logging
      │
      ▼
REST Assured RequestSpecification
```

## Responsibilities

### Base Configuration

-   Resolve base URL from ConfigManager
-   Apply default content type
-   Apply accept header
-   Configure request logging

### Authentication

The factory delegates authentication to the authentication layer rather
than generating tokens itself.

``` text
Factory
   │
   ▼
AuthenticationProvider
   │
   ▼
Bearer Token
```

## Design Principles

-   Single responsibility
-   Stateless
-   Reusable
-   Thread-safe
-   Framework-owned configuration

## Request Types

Typical factory methods include:

-   JSON authenticated request
-   JSON unauthenticated request
-   Custom header request
-   Multipart request (future)
-   Form request (future)

## Benefits

-   One place to change request defaults
-   Consistent behavior
-   Easier maintenance
-   Simpler business clients

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
RequestSpecification
 ↓
REST Assured
```

## Error Handling

Configuration failures:

-   ConfigurationException

Authentication failures:

-   AuthenticationException

Invalid request setup:

-   FrameworkException

## Extension Guide

To add support for a new request style:

1.  Add a dedicated factory method.
2.  Reuse existing configuration.
3.  Avoid duplicating authentication logic.
4.  Keep clients unchanged.

## Best Practices

-   Never create RequestSpecification inside test classes.
-   Keep all defaults in one place.
-   Delegate authentication.
-   Avoid mutable shared state.

## Summary

The Request Specification Factory is the gateway through which every
HTTP request enters the framework. By centralizing request creation it
improves consistency, reduces duplication, and keeps business clients
focused on business operations rather than transport configuration.
