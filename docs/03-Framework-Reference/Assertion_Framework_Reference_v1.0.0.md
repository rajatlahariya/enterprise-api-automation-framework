# Enterprise API Automation Framework

# Assertion Framework Reference

**Version:** v1.0.0

## Purpose

The Assertion Framework provides reusable, framework-owned validation
utilities so that test classes remain concise, readable, and independent
of low-level assertion logic.

## Objectives

-   Centralize assertion logic
-   Improve readability
-   Eliminate duplicate assertions
-   Standardize failure messages
-   Keep business tests expressive

## Architecture

``` text
Test
 │
 ▼
Framework Assertion
 │
 ├── Status Code
 ├── Headers
 ├── Response Body
 ├── Response Time
 ├── JSON Schema
 └── Custom Business Assertions
```

## Assertion Categories

### Status Code Assertions

Validate HTTP response codes.

Examples:

-   200 OK
-   201 Created
-   204 No Content
-   400 Bad Request
-   401 Unauthorized
-   404 Not Found

### Header Assertions

Validate:

-   Content-Type
-   Authorization
-   Cache-Control
-   Location
-   Custom headers

### Response Body Assertions

Validate:

-   Field values
-   Null checks
-   Collections
-   Nested objects
-   Optional fields

### Response Time Assertions

Validate maximum acceptable response time.

Example:

-   Less than 2 seconds
-   Less than 5 seconds

### JSON Schema Validation

Validate response structure against JSON schema.

Benefits:

-   Detect contract changes
-   Ensure required fields exist
-   Verify response format

## Assertion Flow

``` text
API Response
      │
      ▼
ApiResponse<T>
      │
      ▼
Assertion Utility
      │
      ▼
Pass / Fail
```

## Design Principles

-   Reusable
-   Stateless
-   Descriptive failure messages
-   Framework-owned API
-   No duplicated assertions

## Best Practices

-   Keep assertions focused.
-   Prefer reusable methods.
-   Validate behaviour, not implementation.
-   Separate API assertions from database assertions.
-   Do not place assertion logic inside test methods.

## Extending the Framework

To add a new assertion:

1.  Create a reusable assertion method.
2.  Keep it generic.
3.  Add unit tests.
4.  Document expected behaviour.
5.  Reuse across test suites.

## Common Failures

-   Unexpected status code
-   Missing header
-   Schema mismatch
-   Slow response
-   Incorrect business value

## Summary

The Assertion Framework provides consistent, reusable validation
utilities that improve readability, reduce duplication, and encourage a
clean separation between test scenarios and verification logic.
