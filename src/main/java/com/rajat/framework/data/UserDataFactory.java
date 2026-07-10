package com.rajat.framework.data;

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

		return new CreateUserRequest(firstName, lastName,
				uniqueEmail(firstName.toLowerCase() + "." + lastName.toLowerCase()), age, isActive);
	}

	public static UpdateUserRequest createUpdatedUser(String existingEmail) {
		return new UpdateUserRequest("Updated", "User", existingEmail, 32, true);
	}

	private static String uniqueEmail(String prefix) {
		return prefix + "." + System.currentTimeMillis() + "@enterprise.test";
	}
}