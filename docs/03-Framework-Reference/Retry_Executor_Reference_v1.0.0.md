# Enterprise API Automation Framework

# Retry Executor Reference

**Version:** v1.0.0

## Purpose

The Retry Executor provides a reusable mechanism for retrying transient
operations without embedding retry logic throughout the framework. It
improves reliability while keeping business clients and tests simple.

## Why Retry?

Retries are useful for transient failures such as:

-   Temporary network interruptions
-   Short-lived service unavailability
-   Eventual consistency
-   Brief infrastructure instability

Retries should **not** be used to hide functional defects.

## Architecture

``` text
Caller
  │
  ▼
RetryExecutor
  │
  ├── Validate Configuration
  ├── Execute Operation
  ├── Retry if Eligible
  └── Return / Throw
```

## Responsibilities

### RetryExecutor

-   Execute arbitrary operations
-   Limit retry attempts
-   Apply retry delay
-   Preserve root cause
-   Fail fast for invalid configuration

## Execution Lifecycle

``` text
Start
 │
 ▼
Execute Operation
 │
 ├── Success ─────────► Return Result
 │
 └── Failure
       │
       ▼
Retry Limit Reached?
       │
   No ─┴─ Yes
   │        │
Wait Delay  Throw Exception
   │
   ▼
Retry
```

## Configuration

Typical parameters:

-   Maximum attempts
-   Delay between attempts
-   Operation to execute

Negative delays or invalid retry counts should be rejected immediately.

## Exception Handling

-   Preserve original exception
-   Do not swallow failures
-   Throw framework exceptions where appropriate

## Idempotency

Retries should be applied only to operations that are safe to repeat.

Examples:

Suitable: - GET - Health checks - Polling

Use caution: - POST - Financial transactions - Non-idempotent writes

## Best Practices

-   Keep retry policies explicit.
-   Avoid infinite retries.
-   Log retry attempts when appropriate.
-   Retry only transient failures.
-   Keep retry logic outside business clients unless it is part of the
    client contract.

## Anti-Patterns

Do not use retries to:

-   Hide application bugs
-   Ignore authentication failures
-   Mask configuration errors
-   Replace proper synchronization

## Extending RetryExecutor

Future enhancements may include:

-   Exponential backoff
-   Jitter
-   Retry predicates
-   Circuit breaker integration

## Summary

The Retry Executor centralizes retry behaviour, improves reliability for
transient failures, and keeps retry policies consistent across the
framework without complicating business logic.
