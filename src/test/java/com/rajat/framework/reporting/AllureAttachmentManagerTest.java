package com.rajat.framework.reporting;

import java.util.Map;

import org.testng.annotations.Test;

import com.rajat.framework.api.model.ApiResponse;

public class AllureAttachmentManagerTest {

	@Test
	public void shouldAttachApiResponseWithoutThrowingException() {

		ApiResponse response = new ApiResponse(200, "{\"success\":true}",
				Map.of("Content-Type", "application/json", "Authorization", "Bearer secret-token"), 120L);

		AllureAttachmentManager.attachApiResponse("Test API Response", response);
	}

	@Test
	public void shouldAttachJsonContent() {

		AllureAttachmentManager.attachJson("Test JSON", "{\"status\":\"ok\"}");
	}
}