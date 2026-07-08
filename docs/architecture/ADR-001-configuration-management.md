# ADR-001: Configuration Management

## Status

Accepted

## Context

The framework needs configuration values such as base URL, environment, admin credentials, and client credentials.

These values are required by multiple parts of the framework, including authentication, API clients, request specifications, reporting, and future database validation.

Directly reading property files from multiple classes would create duplication, magic strings, and tight coupling to the configuration storage format.

## Decision

We will centralize configuration access through `ConfigManager`.

The framework will use:

- `framework.properties` as the initial configuration source
- `Environment` enum to represent supported environments
- `EnvironmentManager` to resolve the active environment
- `ConfigurationException` for configuration-related failures

Tests and framework components should not directly read `framework.properties`.

They should access configuration through meaningful methods such as:

```java
ConfigManager.getBaseUrl();
ConfigManager.getAdminUsername();
ConfigManager.getClientId();