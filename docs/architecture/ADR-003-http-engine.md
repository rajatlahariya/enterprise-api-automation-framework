# ADR-003: HTTP Engine

## Status

Accepted

## Context

The framework needs a centralized way to execute HTTP requests against the API under test.

Direct Rest Assured usage inside test classes would cause duplication and tight coupling. Each test would need to know how to configure base URI, headers, authentication, content type, request execution, response extraction, and logging.

The framework should allow tests to express intent while the HTTP engine handles request execution.

## Decision

We will build a centralized HTTP engine.

The request flow will be:

```text
Test
  ↓
API Client
  ↓
Request Executor
  ↓
Rest Assured
  ↓
Response Mapper
  ↓
ApiResponse