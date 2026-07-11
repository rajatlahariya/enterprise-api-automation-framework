# Interview Notes (PR-6)

## Why SearchCriteria?
Avoids overloaded client methods and scales as query parameters grow.

## Why PagedResult<T>?
Reusable generic pagination container for future entities.

## Why RetryExecutor?
Explicit retries for idempotent operations only.

## Why ResponseAssertions?
Centralizes assertions and improves readability.

## Why ResponseSpecificationFactory?
Reusable HTTP expectations across tests.
