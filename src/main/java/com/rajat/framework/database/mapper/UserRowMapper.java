package com.rajat.framework.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.rajat.framework.database.model.UserDatabaseRecord;

public final class UserRowMapper implements RowMapper<UserDatabaseRecord> {

	@Override
	public UserDatabaseRecord map(ResultSet resultSet) throws SQLException {

		Timestamp createdAt = resultSet.getTimestamp("created_at");

		return new UserDatabaseRecord(resultSet.getLong("id"), getNullableInteger(resultSet, "age"),
				createdAt == null ? null : createdAt.toLocalDateTime(), resultSet.getString("email"),
				resultSet.getString("first_name"), getNullableBoolean(resultSet, "is_active"),
				resultSet.getString("last_name"), resultSet.getString("username"), resultSet.getString("role"));
	}

	private Integer getNullableInteger(ResultSet resultSet, String columnName) throws SQLException {

		int value = resultSet.getInt(columnName);

		return resultSet.wasNull() ? null : value;
	}

	private Boolean getNullableBoolean(ResultSet resultSet, String columnName) throws SQLException {

		boolean value = resultSet.getBoolean(columnName);

		return resultSet.wasNull() ? null : value;
	}
}