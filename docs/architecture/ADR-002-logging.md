# ADR-002: Logging

## Status

Accepted

## Context

The framework needs consistent logs for debugging failures locally and in CI/CD pipelines.

Logs should help engineers understand:

- Which environment was used
- Which test or framework layer was running
- Which API request was executed
- What response was received
- Where a failure occurred

The initial Maven test execution produced an SLF4J warning because no logging provider was configured.

## Decision

We will use Log4j2 as the logging implementation.

The framework will provide:

- Console logging
- File logging
- Centralized log format through `log4j2.xml`

We will not introduce a custom `FrameworkLogger` abstraction at this stage.

Framework classes may use Log4j2 directly:

```java
private static final Logger LOGGER = LogManager.getLogger(ClassName.class);