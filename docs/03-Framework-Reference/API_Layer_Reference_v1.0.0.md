# Enterprise API Automation Framework

# API Layer Reference

**Version:** v1.0.0

## Purpose

This document describes the API layer, which forms the primary interface
between test classes and the framework.

## Responsibilities

-   Hide REST Assured from tests
-   Expose business operations
-   Build requests using framework components
-   Return framework-owned `ApiResponse<T>` objects

## Package Structure

``` text
api/
├── authentication
├── client
├── endpoint
├── executor
├── http
├── mapper
├── model
├── serializer
└── specification
```

## Client Layer

Business clients expose domain operations instead of HTTP verbs.

Example methods:

-   createUser()
-   getUser()
-   updateUser()
-   deleteUser()
-   searchUsers()

Tests interact only with these methods.

## Request Flow

``` text
Test
 ↓
Business Client
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

## ApiResponse

The framework owns the response contract.

Contains:

-   Status code
-   Headers
-   Body
-   Response time

Advantages:

-   REST Assured independence
-   Stable public API
-   Easier future migration

## Response Mapping

The mapper converts raw REST responses into strongly typed framework
responses using generic models.

## Endpoint Management

Endpoints remain centralized instead of being duplicated across clients.

Benefits:

-   Easier maintenance
-   Reduced duplication
-   Better consistency

## Serialization

Jackson is responsible for request and response serialization.

POJOs remain simple Java objects with no framework logic.

## Authentication Integration

Clients never manage tokens directly.

Authentication is delegated to the authentication layer through the
request specification factory.

## Best Practices

-   Keep clients stateless.
-   Expose business methods only.
-   Never return REST Assured objects.
-   Avoid HTTP details in tests.
-   Centralize endpoint definitions.

## Extension Guide

To add a new API:

1.  Create request/response models.
2.  Add endpoint.
3.  Implement business client.
4.  Add assertions.
5.  Write integration tests.

## Summary

The API layer provides a clean abstraction over HTTP communication,
allowing test classes to remain business-focused while the framework
handles request construction, authentication, execution, and response
mapping.
