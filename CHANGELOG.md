# Changelog

All notable changes to this project will be documented in this file.

The format follows **Keep a Changelog** and the project follows
**Semantic Versioning**.

------------------------------------------------------------------------

# \[1.0.0\] - Initial Release

## Added

### Framework Core

-   Layered architecture
-   Configuration management
-   Environment resolution
-   Framework exception hierarchy

### API Layer

-   Business clients
-   Request Specification Factory
-   Authentication abstraction
-   Response Mapper
-   Framework-owned `ApiResponse<T>`

### Database Layer

-   JDBC connection factory
-   Query executor
-   Repository pattern
-   RowMapper support
-   API ↔ Database validation

### Assertions

-   Reusable assertion framework
-   Response validation
-   Status code validation
-   Response time validation

### Test Infrastructure

-   TestNG integration
-   Data factories
-   Retry executor
-   Parallel execution support

### Tooling

-   Maven build
-   Jenkins pipeline
-   Allure reporting
-   Docker support

### Documentation

-   Architecture Guide
-   Developer Guide
-   Framework Reference
-   README
-   CONTRIBUTING

------------------------------------------------------------------------

## Changed

-   Standardized package structure.
-   Centralized configuration.
-   Hidden REST Assured from test classes.
-   Introduced framework-owned response contract.

------------------------------------------------------------------------

## Fixed

-   Database connection configuration.
-   Docker/Jenkins networking.
-   Credential injection through CI.
-   Soft-delete integration validation.
-   PostgreSQL authentication configuration.

------------------------------------------------------------------------

## Breaking Changes

None.

------------------------------------------------------------------------

## Migration Notes

Initial release. No migration required.

------------------------------------------------------------------------

# Upcoming (1.1.0)

## Planned

-   OAuth2 Client Credentials
-   API Key authentication
-   GitHub Actions workflow
-   Performance testing integration
-   Contract testing support
-   Enhanced reporting
-   Additional sample APIs

------------------------------------------------------------------------

## Versioning Policy

-   MAJOR: Breaking changes
-   MINOR: Backward-compatible features
-   PATCH: Backward-compatible fixes
