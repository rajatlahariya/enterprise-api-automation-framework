package com.rajat.framework.reporting;

import java.util.Map;
import java.util.stream.Collectors;

import com.rajat.framework.api.model.ApiResponse;

import io.qameta.allure.Allure;

public final class AllureAttachmentManager {

	private static final String AUTHORIZATION_HEADER = "authorization";

	private AllureAttachmentManager() {
		// Prevent object creation
	}

	public static void attachApiResponse(String attachmentName, ApiResponse response) {

		if (response == null) {
			return;
		}

		String content = "Status Code: " + response.getStatusCode() + System.lineSeparator() + "Response Time: "
				+ response.getResponseTimeInMs() + " ms" + System.lineSeparator() + "Headers:" + System.lineSeparator()
				+ formatHeaders(response.getHeaders()) + System.lineSeparator() + "Body:" + System.lineSeparator()
				+ safeValue(response.getBody());

		Allure.addAttachment(attachmentName, "text/plain", content, ".txt");
	}

	public static void attachText(String attachmentName, String content) {

		Allure.addAttachment(attachmentName, "text/plain", safeValue(content), ".txt");
	}

	public static void attachJson(String attachmentName, String json) {

		Allure.addAttachment(attachmentName, "application/json", safeValue(json), ".json");
	}

	private static String formatHeaders(Map<String, String> headers) {

		if (headers == null || headers.isEmpty()) {
			return "<none>";
		}

		return headers.entrySet().stream()
				.map(entry -> entry.getKey() + ": " + sanitizeHeader(entry.getKey(), entry.getValue()))
				.collect(Collectors.joining(System.lineSeparator()));
	}

	private static String sanitizeHeader(String headerName, String headerValue) {

		if (headerName != null && headerName.equalsIgnoreCase(AUTHORIZATION_HEADER)) {

			return "<masked>";
		}

		return safeValue(headerValue);
	}

	private static String safeValue(String value) {
		return value == null ? "<null>" : value;
	}
}