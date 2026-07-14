# Security Policy

## Supported Versions

  Version    Supported
  --------- -----------
  1.x           ✅
  \<1.0         ❌

## Reporting a Vulnerability

If you discover a security issue in this framework:

1.  **Do not** create a public GitHub issue.
2.  Prepare a private report including:
    -   Framework version
    -   Steps to reproduce
    -   Impact assessment
    -   Suggested remediation (if known)
3.  Allow time for the issue to be investigated before public
    disclosure.

## Scope

This policy applies to:

-   Configuration management
-   Authentication components
-   Request processing
-   Database access layer
-   CI/CD configuration
-   Secret handling

## Security Principles

-   Secrets are never committed to source control.
-   Credentials are injected through environment variables or CI secret
    stores.
-   Third-party libraries should be kept up to date.
-   Dependencies should be reviewed for known vulnerabilities.
-   Framework abstractions should not expose sensitive implementation
    details.

## Responsible Disclosure

Please report issues privately and avoid publishing exploit details
until a fix is available.

## Security Best Practices for Users

-   Use dedicated test credentials.
-   Rotate credentials regularly.
-   Do not reuse production credentials in test environments.
-   Restrict database permissions to the minimum required.
-   Store secrets in your CI/CD platform's credential manager.
-   Review logs to ensure sensitive values are masked.

## Dependency Updates

Review project dependencies periodically and upgrade supported versions
to receive security fixes.

## Acknowledgements

We appreciate responsible security disclosures that help improve the
framework.
