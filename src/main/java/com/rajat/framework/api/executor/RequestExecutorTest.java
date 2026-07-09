package com.rajat.framework.api.executor;

import org.testng.annotations.Test;

import com.rajat.framework.api.response.ApiResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RequestExecutorTest {

    @Test
    public void shouldExecuteGetRequestSuccessfully() {

        ApiResponse response = RequestExecutor.get("/actuator/health");

        assertThat(response.getStatusCode(), equalTo(200));
    }
}