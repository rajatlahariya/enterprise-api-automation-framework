package com.rajat.framework.data;

import java.util.Locale;
import java.util.UUID;

import com.rajat.framework.api.model.user.CreateUserRequest;
import com.rajat.framework.api.model.user.UpdateUserRequest;

public final class UserDataFactory {

	private UserDataFactory() {
		// Prevent object creation
	}

	public static CreateUserRequest createDefaultUser() {
		return new CreateUserRequest("Automation", "User", uniqueEmail("automation.user"), 30, true);
	}

	public static CreateUserRequest createUser(String firstName, String lastName, Integer age, Boolean isActive) {
		if (firstName == null || firstName.isBlank()) {
			throw new IllegalArgumentException("First name cannot be null or blank.");
		}

		if (lastName == null || lastName.isBlank()) {
			throw new IllegalArgumentException("Last name cannot be null or blank.");
		}

		String prefix = firstName.trim().toLowerCase(Locale.ROOT)
				+ "."
				+ lastName.trim().toLowerCase(Locale.ROOT);

		return new CreateUserRequest(
				firstName.trim(),
				lastName.trim(),
				uniqueEmail(prefix),
				age,
				isActive
		);
	}

	public static UpdateUserRequest createUpdatedUser(String existingEmail) {
		return new UpdateUserRequest("Updated", "User", existingEmail, 32, true);
	}

	private static String uniqueEmail(String prefix) {
		return prefix + "." + UUID.randomUUID() + "@enterprise.test";
	}
}