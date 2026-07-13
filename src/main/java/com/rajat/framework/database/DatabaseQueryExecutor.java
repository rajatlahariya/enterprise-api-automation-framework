package com.rajat.framework.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rajat.framework.core.exceptions.FrameworkException;
import com.rajat.framework.database.mapper.RowMapper;

public final class DatabaseQueryExecutor {

	private DatabaseQueryExecutor() {
		// Prevent object creation
	}

	public static <T> Optional<T> querySingle(String sql, RowMapper<T> rowMapper, Object... parameters) {

		validateArguments(sql, rowMapper);

		try (Connection connection = DatabaseConnectionFactory.createConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			setParameters(statement, parameters);

			try (ResultSet resultSet = statement.executeQuery()) {

				if (!resultSet.next()) {
					return Optional.empty();
				}

				T result = rowMapper.map(resultSet);

				if (resultSet.next()) {
					throw new FrameworkException("Expected one database row but query returned multiple rows.");
				}

				return Optional.ofNullable(result);
			}

		} catch (SQLException exception) {
			throw new FrameworkException("Failed to execute single-row database query.", exception);
		}
	}

	public static <T> List<T> queryList(String sql, RowMapper<T> rowMapper, Object... parameters) {

		validateArguments(sql, rowMapper);

		try (Connection connection = DatabaseConnectionFactory.createConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			setParameters(statement, parameters);

			try (ResultSet resultSet = statement.executeQuery()) {

				List<T> results = new ArrayList<>();

				while (resultSet.next()) {
					results.add(rowMapper.map(resultSet));
				}

				return List.copyOf(results);
			}

		} catch (SQLException exception) {
			throw new FrameworkException("Failed to execute database query.", exception);
		}
	}

	private static void setParameters(PreparedStatement statement, Object[] parameters) throws SQLException {

		if (parameters == null) {
			return;
		}

		for (int index = 0; index < parameters.length; index++) {

			statement.setObject(index + 1, parameters[index]);
		}
	}

	private static void validateArguments(String sql, RowMapper<?> rowMapper) {

		if (sql == null || sql.isBlank()) {
			throw new IllegalArgumentException("SQL query cannot be null or blank.");
		}

		if (rowMapper == null) {
			throw new IllegalArgumentException("RowMapper cannot be null.");
		}
	}
}