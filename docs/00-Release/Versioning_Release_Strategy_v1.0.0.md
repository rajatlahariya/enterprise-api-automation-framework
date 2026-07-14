# Enterprise API Automation Framework

# Versioning and Release Strategy

**Version:** v1.0.0

## Purpose

This document defines how versions are assigned, how releases are
prepared, and how changes move from development branches into stable
releases.

## Semantic Versioning

The project follows Semantic Versioning:

``` text
MAJOR.MINOR.PATCH
```

Example:

``` text
1.2.3
```

### MAJOR

Increment when a release introduces incompatible changes.

Examples:

-   Removing a public framework API
-   Changing package contracts
-   Replacing a supported configuration format
-   Introducing breaking client behavior

### MINOR

Increment when adding backward-compatible functionality.

Examples:

-   Adding a new authentication provider
-   Adding a new database repository
-   Adding a new assertion utility
-   Adding a new CI capability

### PATCH

Increment for backward-compatible fixes.

Examples:

-   Correcting mapping logic
-   Fixing configuration resolution
-   Improving exception messages
-   Resolving thread-safety or resource-management defects

## Release Branch Strategy

``` text
main
 ├── feature/*
 ├── bugfix/*
 └── release/*
```

### main

Contains the latest stable release.

### feature/\*

Used for isolated feature development.

Example:

``` text
feature/oauth2-client-credentials
```

### bugfix/\*

Used for targeted fixes.

Example:

``` text
bugfix/database-timeout-handling
```

### release/\*

Used for release validation and stabilization.

Example:

``` text
release/1.1.0
```

## Release Workflow

``` text
Development
    │
    ▼
Feature/Bugfix Branch
    │
    ▼
Pull Request
    │
    ▼
Release Branch
    │
    ▼
Local Validation
    │
    ▼
Jenkins Validation
    │
    ▼
Merge to main
    │
    ▼
Create Git Tag
```

## Release Checklist

Before creating a release:

-   All tests pass locally
-   Jenkins full suite passes
-   Integration suite passes
-   Allure report is generated
-   No secrets are committed
-   Documentation is updated
-   CHANGELOG is updated
-   Version is updated in `pom.xml`
-   Working tree is clean

## Tagging

Create an annotated tag:

``` bash
git tag -a v1.0.0 \
  -m "Release v1.0.0 - Enterprise API Automation Framework"
```

Push the tag:

``` bash
git push origin v1.0.0
```

## Snapshot Versions

Development builds should use `-SNAPSHOT`.

Example:

``` text
1.1.0-SNAPSHOT
```

Stable releases should not include `-SNAPSHOT`.

Example:

``` text
1.1.0
```

## Hotfix Process

For a production-impacting defect:

1.  Create a branch from `main`.
2.  Apply the smallest safe fix.
3.  Add regression tests.
4.  Run local and Jenkins validation.
5.  Merge into `main`.
6.  Increment PATCH version.
7.  Create a new tag.

## Deprecation Policy

Public APIs should be deprecated before removal whenever practical.

Recommended process:

1.  Mark API as deprecated.
2.  Document the replacement.
3.  Keep it for at least one MINOR release.
4.  Remove only in the next MAJOR release.

## Documentation Versioning

Documentation should match the released framework version.

Examples:

``` text
Architecture_Guide_v1.0.0.md
Developer_Guide_v1.0.0.md
```

General repository files remain unversioned:

``` text
README.md
CONTRIBUTING.md
CHANGELOG.md
LICENSE
SECURITY.md
```

## Release History

  Version   Type    Summary
  --------- ------- ---------------------------------------------
  v0.8.0    Minor   Database validation and Jenkins integration
  v1.0.0    Major   First stable enterprise release

## Summary

This strategy keeps releases predictable, traceable, and safe by
combining semantic versioning, controlled branches, automated
validation, and explicit release gates.
