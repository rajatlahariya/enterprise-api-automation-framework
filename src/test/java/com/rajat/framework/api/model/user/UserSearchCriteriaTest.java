package com.rajat.framework.api.model.user;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import org.testng.annotations.Test;

import com.rajat.framework.testgroup.TestGroups;

public class UserSearchCriteriaTest {

	@Test(groups = { TestGroups.SMOKE, TestGroups.CRUD })
	public void shouldBuildDefaultPaginationCriteria() {

		UserSearchCriteria criteria = UserSearchCriteria.builder().build();

		assertThat(criteria.getPage(), equalTo(0));
		assertThat(criteria.getSize(), equalTo(10));
		assertThat(criteria.getSort(), equalTo("id,asc"));
		assertThat(criteria.getFirstName(), nullValue());
	}

	@Test(groups = { TestGroups.SMOKE, TestGroups.CRUD })
	public void shouldBuildFilteringAndSortingCriteria() {

		UserSearchCriteria criteria = UserSearchCriteria.builder().firstName(" Aarav ").email("enterprise.test")
				.role("ROLE_ADMIN").isActive(true).minAge(20).maxAge(40).page(1).size(20).sort("age,desc").build();

		assertThat(criteria.getFirstName(), equalTo("Aarav"));
		assertThat(criteria.getEmail(), equalTo("enterprise.test"));
		assertThat(criteria.getRole(), equalTo("ROLE_ADMIN"));
		assertThat(criteria.getIsActive(), equalTo(true));
		assertThat(criteria.getMinAge(), equalTo(20));
		assertThat(criteria.getMaxAge(), equalTo(40));
		assertThat(criteria.getPage(), equalTo(1));
		assertThat(criteria.getSize(), equalTo(20));
		assertThat(criteria.getSort(), equalTo("age,desc"));
	}

	@Test(expectedExceptions = IllegalArgumentException.class, groups = { TestGroups.SMOKE, TestGroups.CRUD })
	public void shouldRejectInvalidAgeRange() {

		UserSearchCriteria.builder().minAge(40).maxAge(20).build();
	}

	@Test(expectedExceptions = IllegalArgumentException.class, groups = { TestGroups.SMOKE, TestGroups.CRUD })
	public void shouldRejectNegativePageNumber() {

		UserSearchCriteria.builder().page(-1).build();
	}
}