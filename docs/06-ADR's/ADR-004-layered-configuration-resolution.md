# ADR-004: Layered Configuration Resolution

**Status:** Accepted\
**Date:** 2026-07-14

## Context

The framework must run consistently in local development, Docker, and
Jenkins. Hardcoded values or a single configuration source would make
deployments inflexible and environment-specific.

## Decision

Configuration is resolved using a defined precedence order:

``` text
1. JVM System Properties
2. Environment Variables
3. framework.properties
```

The first available value is used.

``` text
Application
     │
     ▼
ConfigManager
     │
     ├── JVM Property
     ├── Environment Variable
     └── framework.properties
```

This allows the same codebase to execute unchanged across environments.

## Alternatives Considered

### Option 1 -- Property File Only

**Rejected**

Pros: - Simple implementation - Easy to understand

Cons: - Difficult to override in CI - Encourages environment-specific
files - Poor container support

### Option 2 -- Layered Resolution (Chosen)

Pros: - Flexible deployments - CI/CD friendly - Docker friendly - Local
development remains simple

Cons: - Slightly more configuration logic - Requires clear precedence
documentation

## Consequences

### Positive

-   No code changes between environments.
-   Secrets can be injected externally.
-   Environment-specific overrides are straightforward.

### Negative

-   Incorrect precedence can lead to unexpected values if documentation
    is ignored.

## Related Principles

-   Configuration is External
-   Fail Fast
-   Separation of Concerns
-   Secrets Outside Source Control

## References

-   Design_Principles_v1.0.0.md
-   Configuration & Environment Reference
-   Configuration Examples Cookbook
-   Versioning & Release Strategy
