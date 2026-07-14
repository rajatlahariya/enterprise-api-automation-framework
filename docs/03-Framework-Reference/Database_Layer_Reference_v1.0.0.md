# Enterprise API Automation Framework

# Database Layer Reference

**Version:** v1.0.0

## Purpose

The Database Layer provides independent database verification for API
tests. It allows validating persisted data directly from PostgreSQL
without coupling tests to JDBC implementation details.

## Architecture

``` text
Test
 ↓
UserDatabaseRepository
 ↓
DatabaseQueryExecutor
 ↓
DatabaseConnectionFactory
 ↓
JDBC Driver
 ↓
PostgreSQL
```

## Responsibilities

### DatabaseConnectionFactory

-   Creates JDBC connections
-   Reads database configuration from ConfigManager
-   Centralizes connection creation
-   Wraps connection failures in framework exceptions

### DatabaseQueryExecutor

-   Executes parameterized SQL queries
-   Returns mapped domain objects
-   Closes JDBC resources
-   Supports single-row and multi-row queries

### RowMapper Pattern

Maps a `ResultSet` into framework models.

Benefits:

-   Reusable mapping logic
-   Clean separation between SQL and domain objects
-   Easy extension for new entities

### Repository Layer

Repositories expose business-oriented database operations.

Examples:

-   findById()
-   findByEmail()
-   existsByEmail()
-   countUsers()

Tests never execute SQL directly.

## API ↔ Database Validation

Typical lifecycle:

``` text
Create User API
      ↓
Read database row
      ↓
Compare API response and database values
      ↓
Update User API
      ↓
Revalidate database
      ↓
Delete User API
      ↓
Verify expected database state
```

## Configuration

Database settings are externalized.

Typical properties:

``` text
local.db.url
dev.db.url
db.username
db.password
```

Credentials should come from environment variables or CI credentials.

## Connection Lifecycle

Each query follows:

1.  Open connection
2.  Prepare statement
3.  Bind parameters
4.  Execute
5.  Map result
6.  Close ResultSet
7.  Close Statement
8.  Close Connection

No connection objects are exposed to tests.

## Exception Handling

Database failures are wrapped in framework-specific exceptions instead
of exposing raw JDBC exceptions.

## Best Practices

-   Use parameterized SQL
-   Keep SQL inside repositories
-   Reuse RowMappers
-   Validate business data, not implementation details
-   Always clean up created test data

## Extending the Layer

To add a new repository:

1.  Create domain RowMapper
2.  Create repository class
3.  Add business methods
4.  Write repository tests
5.  Use repository from integration tests

## Summary

The Database Layer enables reliable end-to-end validation by
independently verifying database state while keeping JDBC details
isolated from tests and higher framework layers.
