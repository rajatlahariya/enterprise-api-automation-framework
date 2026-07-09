# Configuration Interview Notes

## Q1. Why do we need a ConfigManager?

We need `ConfigManager` because configuration should have a single source of truth.

If every test or framework class directly reads `framework.properties`, then property loading logic and property keys get duplicated everywhere.

`ConfigManager` centralizes configuration access and hides implementation details such as property file location and property key names.

---

## Q2. Why not use `Properties` directly everywhere?

`Properties` is only a low-level key-value file reader.

The framework needs a higher-level abstraction that can:

- load configuration once
- cache values
- resolve environment-specific URLs
- validate missing values
- throw meaningful framework exceptions
- hide property keys from test classes

So `Properties` is used internally, but tests and framework classes access values through `ConfigManager`.

---

## Q3. Why use an enum for Environment?

Environment values are fixed.

Using an enum gives:

- compile-time safety
- IDE autocomplete
- prevention of invalid values
- better readability
- fail-fast behavior for unsupported environments

Using a raw `String` would allow invalid values like `qaa`, `productionn`, or `stage-local`.

---

## Q4. Why does missing env default to LOCAL?

Missing environment is a recoverable case.

If the user runs:

```bash
mvn test

Without passing 
-Denv=qa
the framework safely Defaults to Local.