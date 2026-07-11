# Authentication Interview Notes

## Why use AuthenticationProvider?

To decouple authentication implementation from request creation.

---

## Why Strategy Pattern?

Authentication mechanisms differ in implementation but share the same contract.

---

## Why AuthenticationFactory?

Centralizes provider creation.

Avoids exposing constructors to clients.

---

## Why TokenManager?

Authentication and token lifecycle are separate concerns.

TokenManager caches and refreshes tokens.

---

## Why not login before every request?

It increases execution time and causes unnecessary authentication requests.

Caching improves performance and scalability.

---

## Why AuthenticationToken instead of String?

Authentication includes metadata:

- access token
- refresh token
- expiry
- token type

A model better represents the domain.

---

## Why does AuthClient not cache tokens?

Caching is TokenManager's responsibility.

Each class has one responsibility.