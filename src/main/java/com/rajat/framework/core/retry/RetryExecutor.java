package com.rajat.framework.core.retry;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Supplier;

public final class RetryExecutor {

	private RetryExecutor() {
		// Prevent object creation
	}

	public static <T> T execute(Supplier<T> operation, int maxAttempts, Duration delay) {

		Objects.requireNonNull(operation, "Operation cannot be null.");

		Objects.requireNonNull(delay, "Delay cannot be null.");

		if (maxAttempts < 1) {
			throw new IllegalArgumentException("Maximum attempts must be at least one.");
		}

		if (delay.isNegative()) {
			throw new IllegalArgumentException("Retry delay cannot be negative.");
		}

		RuntimeException lastException = null;

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {

			try {
				return operation.get();

			} catch (RuntimeException exception) {

				lastException = exception;

				if (attempt == maxAttempts) {
					break;
				}

				sleep(delay);
			}
		}

		throw lastException;
	}

	private static void sleep(Duration delay) {

		if (delay.isZero()) {
			return;
		}

		try {
			Thread.sleep(delay.toMillis());

		} catch (InterruptedException exception) {

			Thread.currentThread().interrupt();

			throw new IllegalStateException("Retry execution was interrupted.", exception);
		}
	}
}