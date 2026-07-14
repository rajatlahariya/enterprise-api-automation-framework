# Enterprise API Automation Framework

# Docker Setup & Execution Guide

**Version:** v1.0.0

## Purpose

This guide documents the Docker-based execution environment used by the
Enterprise API Automation Framework. It covers local development,
container networking, service discovery, Jenkins integration, and
operational best practices.

## Container Topology

``` text
+--------------------+
| Jenkins            |
| (Docker)           |
+---------+----------+
          |
          |
+---------v----------+
| enterprise-user-api|
+---------+----------+
          |
          |
+---------v----------+
| PostgreSQL         |
+--------------------+
```

## Components

-   Jenkins
-   Enterprise User API
-   PostgreSQL
-   Docker Network

## Recommended Network

``` bash
docker network create enterprise-network
```

Attach services:

``` bash
docker network connect enterprise-network jenkins
docker network connect enterprise-network enterprise-user-api
docker network connect enterprise-network enterprise-user-api-db
```

## Service Discovery

Inside Docker, always use service names instead of localhost.

  Service    Host
  ---------- ------------------------
  API        enterprise-user-api
  Database   enterprise-user-api-db

Example:

``` text
http://enterprise-user-api:8081
jdbc:postgresql://enterprise-user-api-db:5432/automationdb
```

## Container Lifecycle

Start:

``` bash
docker start jenkins
docker start enterprise-user-api
docker start enterprise-user-api-db
```

Status:

``` bash
docker ps
```

Logs:

``` bash
docker logs enterprise-user-api
docker logs jenkins
```

Stop:

``` bash
docker stop enterprise-user-api
```

## Volumes

Persist:

-   Jenkins Home
-   PostgreSQL Data
-   Maven Repository (optional)

## Health Verification

API:

``` bash
curl http://localhost:8081/actuator/health
```

Database:

``` bash
docker exec -it enterprise-user-api-db psql -U postgres
```

## Jenkins Integration

The Jenkins container should execute tests using Docker service names,
not localhost.

## Common Problems

### Service Not Reachable

-   Verify containers are running.
-   Verify shared Docker network.
-   Use service names.

### Authentication Failure

-   Verify environment variables.
-   Verify credential values.

### Database Connection Failure

-   Verify JDBC URL.
-   Verify PostgreSQL credentials.
-   Verify exposed ports only when connecting from host.

## Best Practices

-   Pin image versions.
-   Keep secrets outside images.
-   Use one Docker network.
-   Publish only required ports.
-   Keep containers stateless where practical.

## Summary

Docker provides a consistent execution environment across local
development and CI, ensuring the framework behaves the same on developer
machines and Jenkins builds.
