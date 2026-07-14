package com.rajat.framework.database;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.util.List;
import java.util.Optional;

import org.testng.annotations.Test;

import com.rajat.framework.testgroup.TestGroups;

public class DatabaseQueryExecutorTest {

	@Test(groups = { TestGroups.INTEGRATION, TestGroups.REGRESSION })
	public void shouldExecuteSingleRowQuery() {

		Optional<String> databaseName = DatabaseQueryExecutor.querySingle("SELECT current_database()",
				resultSet -> resultSet.getString(1));

		assertThat(databaseName.isPresent(), equalTo(true));

		assertThat(databaseName.get(), equalTo("automationdb"));
	}

	@Test(groups = { TestGroups.INTEGRATION, TestGroups.REGRESSION })
	public void shouldReturnEmptyWhenNoRowMatches() {

		Optional<Integer> result = DatabaseQueryExecutor.querySingle("SELECT id FROM users WHERE id = ?",
				resultSet -> resultSet.getInt("id"), -1);

		assertThat(result.isEmpty(), equalTo(true));
	}

	@Test(groups = { TestGroups.INTEGRATION, TestGroups.REGRESSION })
	public void shouldExecuteListQueryWithParameters() {

		List<Integer> userIds = DatabaseQueryExecutor.queryList("""
				SELECT id
				FROM users
				WHERE age >= ?
				ORDER BY id
				LIMIT 5
				""", resultSet -> resultSet.getInt("id"), 18);

		assertThat(userIds.size() <= 5, equalTo(true));
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void shouldRejectBlankSql() {

		DatabaseQueryExecutor.queryList(" ", resultSet -> resultSet.getInt(1));
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void shouldRejectNullRowMapper() {

		DatabaseQueryExecutor.queryList("SELECT id FROM users", null);
	}
}