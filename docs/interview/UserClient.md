# User Client Interview Notes

## Why does UserClient return User instead of ApiResponse?

Business tests care about user data rather than HTTP plumbing.

The client hides transport-level details and returns a domain object.

## Why separate CreateUserRequest and User?

CreateUserRequest represents an instruction to create a user.

User represents the resource returned by the API.

Server-generated fields such as ID and createdAt do not belong in the create request.

## Why not extend User in CreateUserRequest?

A request is not a user.

Inheritance would incorrectly expose server-generated fields and create tight coupling.

## Why use UserMapper?

UserClient should perform API operations, not parse JSON.

Mapping is a separate responsibility.

## Why use UserDataFactory?

It centralizes valid test-data creation, avoids duplicated constructors, and generates unique values.

## Why should CRUD tests create their own data?

Tests should not depend on seeded or pre-existing database records.

Creating data during the test makes execution deterministic and independent.