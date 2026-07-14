package com.rajat.framework.core.retry;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import org.testng.annotations.Test;

import com.rajat.framework.testgroup.TestGroups;

@Test(groups = {TestGroups.UNIT, TestGroups.REGRESSION})
public class RetryExecutorTest {

	@Test
	public void shouldReturnResultWithoutRetryWhenFirstAttemptSucceeds() {

		AtomicInteger attempts = new AtomicInteger();

		String result = RetryExecutor.execute(() -> {
			attempts.incrementAndGet();
			return "SUCCESS";
		}, 3, Duration.ZERO);

		assertThat(result, equalTo("SUCCESS"));
		assertThat(attempts.get(), equalTo(1));
	}

	@Test
	public void shouldRetryUntilOperationSucceeds() {

		AtomicInteger attempts = new AtomicInteger();

		String result = RetryExecutor.execute(() -> {
			int currentAttempt = attempts.incrementAndGet();

			if (currentAttempt < 3) {
				throw new RuntimeException("Temporary failure");
			}

			return "SUCCESS";
		}, 3, Duration.ZERO);

		assertThat(result, equalTo("SUCCESS"));
		assertThat(attempts.get(), equalTo(3));
	}

	@Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Permanent failure", groups = { TestGroups.NEGATIVE })
	public void shouldThrowLastExceptionAfterMaximumAttempts() {

		AtomicInteger attempts = new AtomicInteger();

		try {
			RetryExecutor.execute(() -> {
				attempts.incrementAndGet();

				throw new RuntimeException("Permanent failure");
			}, 3, Duration.ZERO);
		} finally {
			assertThat(attempts.get(), equalTo(3));
		}
	}

	@Test(expectedExceptions = IllegalArgumentException.class, groups = { TestGroups.NEGATIVE })
	public void shouldRejectZeroMaximumAttempts() {

		RetryExecutor.execute(() -> "SUCCESS", 0, Duration.ZERO);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, groups = { TestGroups.NEGATIVE })
	public void shouldRejectNegativeRetryDelayBeforeExecutingOperation() {

		AtomicInteger attempts = new AtomicInteger();

		try {
			RetryExecutor.execute(() -> {
				attempts.incrementAndGet();
				return "SUCCESS";
			}, 2, Duration.ofMillis(-1));
		} finally {
			assertThat(attempts.get(), equalTo(0));
		}
	}

	@Test(expectedExceptions = NullPointerException.class, groups = { TestGroups.NEGATIVE })
	public void shouldRejectNullOperation() {

		RetryExecutor.execute(null, 2, Duration.ZERO);
	}
}