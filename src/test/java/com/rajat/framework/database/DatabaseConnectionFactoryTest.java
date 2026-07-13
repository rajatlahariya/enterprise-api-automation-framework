package com.rajat.framework.database;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.rajat.framework.testgroup.TestGroups;

public class DatabaseConnectionFactoryTest {

	@Test(groups = { TestGroups.INTEGRATION, TestGroups.REGRESSION })
	public void shouldCreateDatabaseConnectionSuccessfully() throws Exception {

		try (Connection connection = DatabaseConnectionFactory.createConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT current_database(), current_user")) {

			assertThat(connection, notNullValue());
			assertThat(connection.isClosed(), equalTo(false));
			assertThat(resultSet.next(), equalTo(true));
			assertThat(resultSet.getString(1), equalTo("automationdb"));
		}
	}
}