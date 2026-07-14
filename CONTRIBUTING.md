# CONTRIBUTING

# Contributing Guide

**Version:** v1.0.0

Thank you for contributing to the Enterprise API Automation Framework.

## Development Workflow

1.  Create a feature branch from the active release branch.
2.  Implement the change.
3.  Add or update tests.
4.  Update documentation.
5.  Open a Pull Request.

## Branch Strategy

``` text
main
├── release/*
│   ├── feature/*
│   └── bugfix/*
```

-   `main` contains stable releases.
-   `release/*` is the integration branch.
-   `feature/*` is for new functionality.
-   `bugfix/*` is for fixes.

## Commit Message Convention

Examples:

``` text
feat: add OAuth2 authentication provider
fix: correct database repository mapping
refactor: simplify request specification factory
docs: update developer guide
test: add integration validation
```

## Pull Request Checklist

-   Architecture respected
-   Tests pass
-   No forbidden package dependencies
-   Documentation updated
-   No hardcoded credentials
-   No REST Assured usage in tests
-   CI pipeline passes

## Coding Standards

-   Java 21
-   SOLID principles
-   Small focused classes
-   Constructor injection where applicable
-   Descriptive method names
-   Meaningful exception messages

## Testing Requirements

Every functional change should include:

-   Unit tests
-   Integration tests (where applicable)
-   Regression verification

## Documentation Requirements

Update documentation whenever:

-   Public APIs change
-   Configuration changes
-   Architecture changes
-   New modules are added

## Code Review Expectations

Reviewers should verify:

-   Correctness
-   Readability
-   Performance
-   Thread safety
-   Architecture compliance
-   Test coverage

## Security

Never commit:

-   Passwords
-   API keys
-   Tokens
-   Certificates
-   Environment-specific secrets

Use environment variables or CI credential stores.

## Reporting Issues

Include:

-   Framework version
-   Java version
-   Maven version
-   Environment
-   Steps to reproduce
-   Expected result
-   Actual result
-   Relevant logs

## Summary

Following this guide helps keep the framework maintainable, consistent,
and production-ready for all contributors.
