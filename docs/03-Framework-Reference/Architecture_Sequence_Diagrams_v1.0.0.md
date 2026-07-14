# Enterprise API Automation Framework

# Architecture & Sequence Diagrams

**Version:** v1.0.0

## Purpose

This document contains Mermaid diagrams describing the architecture,
execution flow and component interactions within the framework.

------------------------------------------------------------------------

# 1. Overall Framework Architecture

``` mermaid
flowchart TD
    T[Test]
    C[Business Client]
    RSF[Request Specification Factory]
    AUTH[Authentication Provider]
    HTTP[REST Assured]
    MAP[Response Mapper]
    API[ApiResponse<T>]
    DB[Database Repository]
    ASSERT[Assertions]

    T --> C
    C --> RSF
    RSF --> AUTH
    RSF --> HTTP
    HTTP --> MAP
    MAP --> API
    API --> ASSERT
    ASSERT --> DB
```

------------------------------------------------------------------------

# 2. Package Dependency

``` mermaid
flowchart TB
Tests --> API
API --> Core
API --> Util
API --> Database
Assertion --> API
Database --> Core
Reporting --> Core
Data --> API
```

------------------------------------------------------------------------

# 3. API Request Lifecycle

``` mermaid
sequenceDiagram
participant Test
participant Client
participant Factory
participant Auth
participant RA as REST Assured
participant Mapper

Test->>Client: createUser()
Client->>Factory: build specification
Factory->>Auth: authenticate
Auth-->>Factory: bearer token
Factory-->>Client: RequestSpecification
Client->>RA: execute request
RA-->>Mapper: Response
Mapper-->>Client: ApiResponse<User>
Client-->>Test: ApiResponse<User>
```

------------------------------------------------------------------------

# 4. Authentication Flow

``` mermaid
sequenceDiagram
participant Client
participant Provider
participant Token
participant AuthAPI

Client->>Provider: authenticate()
Provider->>Token: getToken()
Token->>AuthAPI: POST /login
AuthAPI-->>Token: access token
Token-->>Provider: cached token
Provider-->>Client: Authorization header
```

------------------------------------------------------------------------

# 5. Database Validation Flow

``` mermaid
sequenceDiagram
participant Test
participant API
participant Repo
participant JDBC
participant DB

Test->>API: createUser()
API-->>Test: id
Test->>Repo: findById(id)
Repo->>JDBC: SQL
JDBC->>DB: SELECT
DB-->>JDBC: row
JDBC-->>Repo: User
Repo-->>Test: User
```

------------------------------------------------------------------------

# 6. Retry Flow

``` mermaid
flowchart TD
Start --> Execute
Execute -->|Success| End
Execute -->|Failure| Retry
Retry --> Attempts{Attempts Left?}
Attempts -->|Yes| Execute
Attempts -->|No| Fail
```

------------------------------------------------------------------------

# 7. Configuration Resolution

``` mermaid
flowchart LR
JVM --> Config
ENV --> Config
Properties --> Config
Config --> Framework
```

------------------------------------------------------------------------

# 8. Jenkins Pipeline

``` mermaid
flowchart LR
Git --> Jenkins
Jenkins --> Checkout
Checkout --> Build
Build --> Test
Test --> Allure
Allure --> Archive
Archive --> Report
```

------------------------------------------------------------------------

# 9. Docker Topology

``` mermaid
flowchart LR
Jenkins --> API
API --> PostgreSQL
Developer --> Jenkins
Developer --> API
```

------------------------------------------------------------------------

# Summary

These diagrams serve as the visual architecture reference for the
framework and complement the written reference guides.
