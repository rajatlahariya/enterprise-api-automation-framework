# HTTP Foundation Interview Notes

## Q1. Why did you create RequestSpecificationFactory?

Every API request requires common configuration such as Base URI, Content-Type and Accept headers.

Instead of duplicating this setup in every client, RequestSpecificationFactory centralizes request construction.

This improves maintainability and provides a single place for future authentication, filters and logging.

---

## Q2. Why return RequestSpecification instead of RequestSpecBuilder?

A factory should return the finished product.

Returning RequestSpecBuilder exposes construction details and defeats the purpose of the Factory Pattern.

Clients should receive a ready-to-use RequestSpecification.

---

## Q3. Why create a new RequestSpecification for every request?

RequestSpecification is mutable.

It contains headers, authentication, cookies, query parameters and filters.

Sharing the same object across parallel tests may lead to race conditions and request contamination.

Creating a fresh specification ensures thread safety.

---

## Q4. Why use an Endpoint enum instead of constants?

Endpoints are domain concepts, not simple strings.

Enums provide:

- Compile-time safety
- Discoverability
- IDE auto-completion
- Future metadata support

---

## Q5. Why create ApiPathResolver?

Endpoint stores only the relative path.

ApiPathResolver composes the final endpoint path.

This centralizes API versioning and avoids changing every endpoint when version changes.

---

## Q6. Why not store '/api/v1' inside every endpoint?

Because API version is environment/configuration specific.

Changing API version should require changing only configuration instead of every endpoint.

---

## Q7. Why is ApiResponse framework-owned?

Tests should depend on framework abstractions rather than third-party libraries.

If Rest Assured is replaced in future, only the mapper changes.

Tests remain unchanged.

---

## Q8. Why create ResponseMapper?

ResponseMapper converts Rest Assured Response into ApiResponse.

It isolates Rest Assured inside the HTTP layer.

---

## Q9. Why is Rest Assured a compile dependency?

Framework engine classes inside src/main use Rest Assured directly.

Therefore Rest Assured must be available during compilation.

---

## Q10. Why shouldn't tests use Rest Assured directly?

Tests should describe business behaviour.

HTTP implementation belongs to the framework.

Keeping Rest Assured inside the framework reduces duplication and coupling.