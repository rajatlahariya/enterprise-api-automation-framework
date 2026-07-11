package com.rajat.framework.api.builder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.testng.annotations.Test;

import com.rajat.framework.api.model.user.UserSearchCriteria;
import com.rajat.framework.testgroup.TestGroups;

public class QueryParameterBuilderTest {

	@Test(groups = { TestGroups.SMOKE, TestGroups.CRUD })
	public void shouldBuildOnlyPopulatedParameters() {

		UserSearchCriteria criteria = UserSearchCriteria.builder().firstName("Raj").minAge(25).page(2).size(5)
				.sort("age,desc").build();

		Map<String, Object> parameters = QueryParameterBuilder.from(criteria);

		assertThat(parameters.get("firstName"), equalTo("Raj"));

		assertThat(parameters.get("minAge"), equalTo(25));

		assertThat(parameters.get("page"), equalTo(2));

		assertThat(parameters.get("size"), equalTo(5));

		assertThat(parameters.get("sort"), equalTo("age,desc"));

		assertThat(parameters.containsKey("email"), equalTo(false));

		assertThat(parameters.containsKey("isActive"), equalTo(false));
	}

	@Test(expectedExceptions = IllegalArgumentException.class, groups =

	{ TestGroups.SMOKE, TestGroups.CRUD })

	public void shouldRejectNullCriteria() {
		QueryParameterBuilder.from(null);
	}
}