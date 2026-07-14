# Enterprise API Automation Framework

# Package Dependency Guide

**Version:** v1.0.0

## Purpose

This document defines the architectural dependency rules for every
package in the framework. It serves as the architecture contract to
prevent cyclic dependencies and maintain a clean layered design.

## High-Level Package Structure

``` text
com.rajat.framework
├── api
├── assertion
├── core
├── data
├── database
├── reporting
└── util
```

## Dependency Direction

``` text
Tests
   │
   ▼
API Layer
   │
   ▼
Core Services
   │
   ├── Authentication
   ├── Configuration
   ├── HTTP
   ├── Mapping
   └── Database
```

Dependencies always flow downward.

## Package Responsibilities

### api

Responsible for:

-   Business clients
-   Authentication
-   HTTP execution
-   Endpoint definitions
-   Request specifications
-   Response mapping

May depend on:

-   core
-   util

Must not depend on:

-   test classes

------------------------------------------------------------------------

### assertion

Responsible for:

-   Framework assertions
-   Response validation
-   Business verification

May depend on:

-   api.model
-   core

Must not depend on:

-   REST Assured directly

------------------------------------------------------------------------

### core

Responsible for:

-   Configuration
-   Environment
-   Constants
-   Exceptions
-   Logging
-   Listeners

Core should remain independent and reusable.

------------------------------------------------------------------------

### data

Responsible for:

-   Test data factories
-   Builders
-   Sample payloads

Should not contain HTTP or database logic.

------------------------------------------------------------------------

### database

Responsible for:

-   JDBC
-   Repository layer
-   Row mappers
-   Query execution

Should never call API clients.

------------------------------------------------------------------------

### reporting

Responsible for:

-   Allure integration
-   Reporting utilities
-   Attachments

Should not contain business logic.

------------------------------------------------------------------------

### util

Responsible for:

-   Stateless helper classes
-   Generic reusable utilities

Utilities must not become a dumping ground.

## Forbidden Dependencies

Examples:

❌ Database → API Client

❌ API Client → Test Classes

❌ Reporting → Database

❌ Utilities → Business Clients

## Allowed Dependencies

Examples:

✅ Client → RequestSpecificationFactory

✅ Client → ResponseMapper

✅ Repository → DatabaseQueryExecutor

✅ Assertions → ApiResponse

## Preventing Cyclic Dependencies

Rules:

-   Always depend downward.
-   Avoid bidirectional relationships.
-   Extract common behaviour into core or util where appropriate.

## Extension Rules

When introducing a new package:

1.  Define its responsibility.
2.  Define allowed dependencies.
3.  Document prohibited dependencies.
4.  Add architecture review before merging.

## Architecture Principles

-   High cohesion
-   Low coupling
-   SOLID
-   Separation of concerns
-   Layered architecture
-   Framework-owned abstractions

## Summary

Following these dependency rules preserves maintainability, prevents
architectural drift, and ensures the framework remains scalable as new
features are introduced.
