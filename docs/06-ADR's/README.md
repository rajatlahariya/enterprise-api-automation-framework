# Architecture Decision Records (ADR)

## Purpose

This directory contains the Architecture Decision Records (ADRs) for the
Enterprise API Automation Framework.

ADRs record **why** significant architectural decisions were made, the
alternatives considered, and their long-term consequences.

------------------------------------------------------------------------

# ADR Lifecycle

  -----------------------------------------------------------------------
  Status                            Meaning
  --------------------------------- -------------------------------------
  Proposed                          Under discussion and not yet approved

  Accepted                          Approved and implemented

  Superseded                        Replaced by a newer ADR

  Deprecated                        Retained for historical reference but
                                    no longer recommended
  -----------------------------------------------------------------------

------------------------------------------------------------------------

# ADR Numbering

Use sequential numbering.

Examples:

``` text
ADR-001-hide-rest-assured-from-tests.md
ADR-002-framework-owned-api-response.md
ADR-003-business-client-abstraction.md
```

Numbers are never reused.

------------------------------------------------------------------------

# ADR Template

Every ADR should contain:

1.  Title
2.  Status
3.  Context
4.  Decision
5.  Alternatives Considered
6.  Consequences
7.  References

------------------------------------------------------------------------

# ADR Index

  ADR       Title                                 Status
  --------- ------------------------------------- ----------
  ADR-001   Hide REST Assured from Test Layer     Accepted
  ADR-002   Framework-Owned ApiResponse           Accepted
  ADR-003   Business Client Abstraction           Accepted
  ADR-004   Layered Configuration Resolution      Accepted
  ADR-005   Read-Only Database Validation Layer   Accepted
  ADR-006   Fresh JDBC Connection Per Operation   Accepted
  ADR-007   Authentication Provider Abstraction   Accepted
  ADR-008   Parallel-Safe Test Data Strategy      Accepted
  ADR-009   Jenkins + Docker First CI Strategy    Accepted

------------------------------------------------------------------------

# When to Create an ADR

Create an ADR when a decision:

-   Changes framework architecture
-   Introduces a public abstraction
-   Changes dependency direction
-   Affects extensibility
-   Changes release strategy
-   Impacts framework users

Do **not** create ADRs for routine implementation details.

------------------------------------------------------------------------

# Review Policy

Accepted ADRs are historical records.

If a decision changes:

1.  Create a new ADR.
2.  Mark the previous ADR as **Superseded**.
3.  Link both documents.

Do not rewrite history by editing an accepted ADR.

------------------------------------------------------------------------

# Principles

-   Keep ADRs concise.
-   Focus on **why**, not implementation.
-   One architectural decision per ADR.
-   Reference related ADRs when appropriate.
