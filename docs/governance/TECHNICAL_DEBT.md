# Technical Debt Register

**Version:** v1.0.0

## Purpose

This register records technical debt that has been **consciously
accepted** by the project team. Each entry includes the reason for
accepting the debt, the associated risks, and the conditions under which
it should be addressed.

Technical debt is intentionally documented so that it is visible,
traceable, and reviewed during future releases.

------------------------------------------------------------------------

# Debt Classification

  Priority   Description
  ---------- ---------------------------------------------------------
  High       Should be addressed in the next major/minor release
  Medium     Valuable improvement with manageable risk
  Low        Improvement opportunity with minimal operational impact

------------------------------------------------------------------------

# Technical Debt Register

  -------------------------------------------------------------------------------
  ID       Title           Priority        Target Release            Status
  -------- --------------- --------------- ------------------------- ------------
  TD-001   Introduce JDBC  Medium          v1.2                      Open
           connection                                                
           pooling                                                   

  TD-002   Add OAuth2      High            v1.1                      Open
           Client                                                    
           Credentials                                               
           provider                                                  

  TD-003   Support         Medium          v2.0                      Open
           multiple                                                  
           database                                                  
           engines                                                   

  TD-004   Add             Medium          v1.2                      Open
           architecture                                              
           rule                                                      
           enforcement                                               
           (ArchUnit)                                                

  TD-005   Dependency      High            v1.1                      Open
           vulnerability                                             
           scanning                                                  

  TD-006   Contract        Medium          v2.0                      Open
           testing support                                           
  -------------------------------------------------------------------------------

------------------------------------------------------------------------

# Debt Details

## TD-001 -- JDBC Connection Pooling

### Current State

The framework opens a fresh JDBC connection for each repository
operation.

### Why Accepted

Current database validation volume is low, making the implementation
simpler and easier to maintain.

### Risk

Connection creation overhead may become noticeable as integration test
volume grows.

### Exit Criteria

Introduce a connection pool (for example, HikariCP) when performance
measurements demonstrate a measurable benefit.

------------------------------------------------------------------------

## TD-002 -- OAuth2 Client Credentials

### Current State

Authentication abstraction exists, but only currently implemented
authentication mechanisms are supported.

### Why Accepted

The abstraction allows future authentication providers without changing
business clients.

### Risk

Limited interoperability with APIs requiring OAuth2 Client Credentials.

### Exit Criteria

Implement an OAuth2 provider conforming to the existing
`AuthenticationProvider` contract.

------------------------------------------------------------------------

## TD-003 -- Multiple Database Engines

### Current State

Framework validation targets PostgreSQL.

### Why Accepted

Single-database support reduced initial complexity.

### Risk

Limited portability across database platforms.

### Exit Criteria

Introduce database-specific adapters or dialect-aware repositories.

------------------------------------------------------------------------

## TD-004 -- Architecture Rule Enforcement

### Current State

Architecture rules are documented but not automatically verified.

### Why Accepted

Documentation and code review currently enforce architecture.

### Risk

Future changes could accidentally violate dependency direction.

### Exit Criteria

Add automated architecture verification using ArchUnit or an equivalent
tool.

------------------------------------------------------------------------

## TD-005 -- Dependency Vulnerability Scanning

### Current State

Dependencies are reviewed manually.

### Why Accepted

Acceptable for the initial release.

### Risk

Security vulnerabilities may remain undetected between manual reviews.

### Exit Criteria

Integrate automated dependency scanning into CI.

------------------------------------------------------------------------

## TD-006 -- Contract Testing

### Current State

Integration testing validates runtime behavior but does not verify API
contracts.

### Why Accepted

Functional validation was prioritized for v1.

### Risk

Contract regressions may be detected later than desired.

### Exit Criteria

Introduce contract testing using an appropriate framework while
preserving the current layered architecture.

------------------------------------------------------------------------

# Review Process

The technical debt register should be reviewed:

-   Before every minor release
-   During release planning
-   When new architectural decisions are made

Resolved debt should be marked **Closed** rather than removed to
preserve project history.

------------------------------------------------------------------------

# Principles

-   Document only real, accepted debt.
-   Do not record completed work.
-   Every debt item must have an owner, rationale, and exit criteria.
-   Technical debt is a planning tool---not a backlog.
