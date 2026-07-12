package com.rajat.framework.api.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.testng.annotations.Test;

import com.rajat.framework.api.model.common.PagedResult;
import com.rajat.framework.api.model.user.User;
import com.rajat.framework.api.model.user.UserSearchCriteria;
import com.rajat.framework.testgroup.TestGroups;

@Test(groups = { TestGroups.INTEGRATION, TestGroups.REGRESSION, TestGroups.SEARCH })
public class UserSearchTest {

    private final UserClient userClient = new UserClient();

    @Test(groups = { TestGroups.SMOKE })
    public void shouldReturnRequestedPageSize() {
        UserSearchCriteria criteria = UserSearchCriteria.builder()
                .page(0)
                .size(5)
                .sort("id,asc")
                .build();

        PagedResult<User> result = userClient.getUsers(criteria);

        assertThat(result, notNullValue());
        assertThat(result.getPagination(), notNullValue());
        assertThat(result.getPagination().getPage(), equalTo(0));
        assertThat(result.getPagination().getSize(), equalTo(5));
        assertThat(result.getNumberOfElements() <= 5, equalTo(true));
    }

    @Test
    public void shouldFilterActiveUsers() {
        UserSearchCriteria criteria = UserSearchCriteria.builder()
                .isActive(true)
                .size(20)
                .build();

        PagedResult<User> result = userClient.getUsers(criteria);

        assertThat(result, notNullValue());
        result.getContent().forEach(user ->
                assertThat(user.getIsActive(), equalTo(true)));
    }

    @Test
    public void shouldSortUsersByAgeDescending() {
        UserSearchCriteria criteria = UserSearchCriteria.builder()
                .size(20)
                .sort("age,desc")
                .build();

        List<User> users = userClient.getUsers(criteria).getContent();

        for (int index = 0; index < users.size() - 1; index++) {
            assertThat(
                    "Users are not sorted by age descending.",
                    users.get(index).getAge() >= users.get(index + 1).getAge(),
                    equalTo(true));
        }
    }

    @Test
    public void shouldSupportFilteringSortingAndPaginationTogether() {
        UserSearchCriteria criteria = UserSearchCriteria.builder()
                .isActive(true)
                .minAge(25)
                .maxAge(35)
                .page(0)
                .size(10)
                .sort("age,asc")
                .build();

        PagedResult<User> result = userClient.getUsers(criteria);
        List<User> users = result.getContent();

        assertThat(result.getPagination().getPage(), equalTo(0));

        users.forEach(user -> {
            assertThat(user.getIsActive(), equalTo(true));
            assertThat(user.getAge() >= 25, equalTo(true));
            assertThat(user.getAge() <= 35, equalTo(true));
        });

        for (int index = 0; index < users.size() - 1; index++) {
            assertThat(
                    "Users are not sorted by age ascending.",
                    users.get(index).getAge() <= users.get(index + 1).getAge(),
                    equalTo(true));
        }
    }
}
