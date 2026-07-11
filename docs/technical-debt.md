# Technical Debt Register

## TD-001
AuthenticationTokenMapper still duplicates JsonNode extraction.

Priority: Medium

Reason Deferred:
Not enough benefit before delivery.

Planned:
Refactor AuthenticationTokenMapper to use JsonNodeUtils.

----------------------------------------

## TD-002
ApiResponse headers are case-sensitive.

Priority: Medium

Reason Deferred:
Current APIs don't require case-insensitive lookup.

----------------------------------------

## TD-003
HttpMethod enum currently has no consumers.

Priority: Low

Reason Deferred:
Reserved for future RequestBuilder improvements.

----------------------------------------

## TD-004
AuthenticationType supports Client Credentials but implementation is incomplete.

Priority: Low

Reason Deferred:
JWT Bearer authentication is currently the only supported flow.

----------------------------------------

## TD-005
Logging framework is configured but minimally used.

Priority: Medium

Reason Deferred:
Will be expanded with request/response filters.