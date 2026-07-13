package com.rajat.framework.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.rajat.framework.core.configuration.ConfigManager;
import com.rajat.framework.core.exceptions.FrameworkException;

public final class DatabaseConnectionFactory {

	private DatabaseConnectionFactory() {
		// Prevent object creation
	}

	public static Connection createConnection() {

		try {
			return DriverManager.getConnection(ConfigManager.getDatabaseUrl(), ConfigManager.getDatabaseUsername(),
					ConfigManager.getDatabasePassword());

		} catch (SQLException exception) {
			throw new FrameworkException("Failed to create database connection.", exception);
		}
	}
}