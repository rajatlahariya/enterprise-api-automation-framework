package com.rajat.framework.database.model;

import java.time.LocalDateTime;

public record UserDatabaseRecord(Long id, Integer age, LocalDateTime createdAt, String email, String firstName,
		Boolean active, String lastName, String username, String role) {
}