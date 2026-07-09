# Logging Interview Notes

## Q1. Why do we need logging in an API automation framework?

Logs help reconstruct the execution flow after a failure.

In CI/CD, we cannot debug interactively, so logs should show environment, endpoint, request, response, authentication context, and failure layer.

## Q2. Why not use System.out.println?

`System.out.println` has no severity levels, no formatting control, no package-level configuration, no file output control, and is unsuitable for enterprise debugging.

A real framework should use a logging library.

## Q3. Why Log4j2?

Log4j2 is mature, configurable, supports console and file logging, and works well in Java automation frameworks.

It also resolved the SLF4J no-provider warning.

## Q4. Why did we reject FrameworkLogger initially?

Because there was only one logging implementation and no immediate need for masking, correlation IDs, report attachment, or external log shipping.

Creating a wrapper at that stage would be speculative abstraction.

## Q5. When would FrameworkLogger become justified?

If we later need token masking, password masking, correlation IDs, Allure log attachment, or centralized log decoration, then a `FrameworkLogger` abstraction becomes valuable.

At that point it solves a real problem instead of being premature abstraction.

## Q6. What principle guided this decision?

YAGNI — You Aren't Gonna Need It.

We should build for current requirements while keeping the design extensible.