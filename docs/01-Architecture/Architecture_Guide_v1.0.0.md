# Enterprise API Automation Framework

# Architecture Guide

**Version:** v1.0.0

## Purpose

This document explains the architecture of the Enterprise API Automation
Framework, the responsibility of each layer, the request lifecycle,
dependency direction, and the key design decisions.

## Design Goals

-   Layered architecture
-   Separation of concerns
-   High cohesion
-   Low coupling
-   Thread safety
-   Reusability
-   Maintainability
-   CI/CD readiness

## High-Level Architecture

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
Authentication
 │
 ▼
REST Assured
 │
 ▼
Response Mapper
 │
 ▼
ApiResponse
 ├── Assertions
 └── Database Validation
```

## Layer Responsibilities

### Test Layer

-   Contains business scenarios.
-   Never uses REST Assured directly.
-   Calls business clients only.

### Business Client Layer

-   Encapsulates API operations.
-   Exposes business methods (createUser, getUser, updateUser,
    deleteUser).

### Request Specification Factory

-   Creates reusable request specifications.
-   Applies headers, authentication and content type.

### Authentication Layer

-   Performs login.
-   Manages bearer tokens.
-   Keeps authentication isolated from tests.

### HTTP Layer

-   Executes requests using REST Assured.
-   Hidden from tests.

### Response Mapper

-   Converts REST Assured responses into framework-owned ApiResponse
    objects.

### Assertion Layer

-   Reusable assertions for status codes, headers, response time and
    schema.

### Database Layer

-   Independent JDBC validation.
-   Repository and RowMapper pattern.
-   API-to-database verification.

## Package Structure

``` text
com.rajat.framework
├── api
│   ├── authentication
│   ├── client
│   ├── endpoint
│   ├── executor
│   ├── http
│   ├── mapper
│   ├── model
│   ├── serializer
│   └── specification
├── assertion
├── core
├── data
├── database
├── reporting
└── util
```

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
ApiResponse
 ├── Assertions
 └── Database Validation
```

## Dependency Direction

``` text
Tests
 ↓
Clients
 ↓
Specification
 ↓
Authentication
 ↓
HTTP
 ↓
Mapper
 ↓
ApiResponse
```

Dependencies flow downward only. Lower layers never depend on upper
layers.

## Thread Safety

-   Stateless business clients
-   Immutable response objects
-   Centralized configuration
-   No shared mutable framework state

## Exception Strategy

Framework exceptions communicate failures explicitly:

-   FrameworkException
-   ConfigurationException
-   AuthenticationException
-   AssertionException
-   DatabaseException

## Design Principles

-   SOLID
-   Composition over inheritance
-   Fail fast
-   Framework-owned contracts
-   REST Assured hidden from tests

## Summary

The architecture provides a clean, scalable, and maintainable foundation
for enterprise API automation while keeping tests simple and independent
of implementation details.
