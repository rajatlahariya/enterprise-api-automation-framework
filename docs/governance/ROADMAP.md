# Roadmap

**Version:** v1.0.0

## Purpose

This roadmap communicates the long-term direction of the Enterprise API
Automation Framework.

Unlike a task backlog, this document describes strategic capabilities
planned for future releases. Active implementation work should be
tracked through GitHub Issues and Projects.

------------------------------------------------------------------------

# Roadmap Principles

-   Deliver incremental value.
-   Preserve backward compatibility within major versions.
-   Favor stable architecture over rapid feature growth.
-   Validate new capabilities through automated tests and documentation.

------------------------------------------------------------------------

# Current Release

## v1.0.0 --- Foundation

**Status:** Released

### Highlights

-   Layered architecture
-   Business client abstraction
-   Framework-owned `ApiResponse<T>`
-   REST Assured integration
-   Database validation layer
-   PostgreSQL support
-   Docker execution
-   Jenkins CI/CD pipeline
-   Allure reporting
-   Comprehensive documentation

------------------------------------------------------------------------

# Planned Releases

## v1.1 --- Security & Quality

### Objectives

-   OAuth2 Client Credentials authentication
-   Dependency vulnerability scanning
-   Improved authentication extensibility
-   Enhanced reporting

------------------------------------------------------------------------

## v1.2 --- Architecture Maturity

### Objectives

-   Architecture rule validation (ArchUnit)
-   JDBC connection pooling (if justified by performance)
-   Expanded assertion library
-   Improved diagnostics and logging

------------------------------------------------------------------------

## v2.0 --- Platform Expansion

### Objectives

-   Multiple database support
-   Contract testing integration
-   Additional authentication mechanisms
-   Multi-module framework support
-   Extended CI platform examples

------------------------------------------------------------------------

# Long-Term Vision

The framework aims to become a reusable enterprise-quality API
automation platform rather than a collection of project-specific tests.

Key characteristics include:

-   Clear architectural boundaries
-   Stable public contracts
-   Extensible component model
-   Automated quality gates
-   Comprehensive documentation
-   Technology-agnostic design where practical

------------------------------------------------------------------------

# Out of Scope

The following are intentionally outside the scope of the current
roadmap:

-   UI automation
-   Performance testing implementation
-   Mobile automation
-   Production monitoring
-   Test management platform integration

These may be addressed by separate projects or future initiatives.

------------------------------------------------------------------------

# Change Management

The roadmap should be reviewed:

-   Before each release
-   During architectural planning
-   After significant ADRs are accepted

Major roadmap changes should reference the relevant ADRs and technical
debt items where applicable.

------------------------------------------------------------------------

# Relationship to Other Documents

-   ADRs explain architectural decisions.
-   TECHNICAL_DEBT.md records consciously accepted compromises.
-   CHANGELOG.md records completed work.
-   GitHub Issues track active implementation.

------------------------------------------------------------------------

# Summary

This roadmap provides strategic direction while allowing day-to-day
implementation planning to remain in GitHub Issues and Projects. It
should evolve as the framework matures and new architectural priorities
emerge.
