package com.rajat.framework.database.repository;

import java.util.List;
import java.util.Optional;

import com.rajat.framework.database.DatabaseQueryExecutor;
import com.rajat.framework.database.mapper.UserRowMapper;
import com.rajat.framework.database.model.UserDatabaseRecord;

public final class UserDatabaseRepository {

	private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

	private static final String SELECT_COLUMNS = "id, age, created_at, email, first_name, "
			+ "is_active, last_name, username, role";

	public Optional<UserDatabaseRecord> findById(Long userId) {

		validateUserId(userId);

		String sql = "SELECT " + SELECT_COLUMNS + " FROM users " + "WHERE id = ?";

		return DatabaseQueryExecutor.querySingle(sql, USER_ROW_MAPPER, userId);
	}

	public Optional<UserDatabaseRecord> findByEmail(String email) {

		validateEmail(email);

		String sql = "SELECT " + SELECT_COLUMNS + " FROM users " + "WHERE email = ?";

		return DatabaseQueryExecutor.querySingle(sql, USER_ROW_MAPPER, email.trim());
	}

	public List<UserDatabaseRecord> findActiveUsers() {

		String sql = "SELECT " + SELECT_COLUMNS + " FROM users " + "WHERE is_active = true " + "ORDER BY id";

		return DatabaseQueryExecutor.queryList(sql, USER_ROW_MAPPER);
	}

	public boolean existsByEmail(String email) {

		validateEmail(email);

		String sql = "SELECT EXISTS (" + "SELECT 1 " + "FROM users " + "WHERE email = ?" + ")";

		return DatabaseQueryExecutor.querySingle(sql, resultSet -> resultSet.getBoolean(1), email.trim()).orElse(false);
	}

	public long countUsers() {

		String sql = "SELECT COUNT(*) FROM users";

		return DatabaseQueryExecutor.querySingle(sql, resultSet -> resultSet.getLong(1)).orElse(0L);
	}

	private void validateUserId(Long userId) {

		if (userId == null || userId <= 0) {
			throw new IllegalArgumentException("User ID must be a positive number.");
		}
	}

	private void validateEmail(String email) {

		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException("Email cannot be null or blank.");
		}
	}
}