package com.rajat.framework.api.mapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajat.framework.api.model.ApiResponse;
import com.rajat.framework.api.model.common.PagedResult;
import com.rajat.framework.api.model.common.Pagination;
import com.rajat.framework.api.model.user.User;
import com.rajat.framework.core.exceptions.FrameworkException;
import com.rajat.framework.util.JsonNodeUtils;

public final class UserPageMapper {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private UserPageMapper() {
		// Prevent object creation
	}

	public static PagedResult<User> from(ApiResponse response) {

		validateResponse(response);

		try {
			JsonNode rootNode = OBJECT_MAPPER.readTree(response.getBody());

			JsonNode dataNode = rootNode.get("data");
			JsonNode paginationNode = rootNode.get("pagination");

			if (dataNode == null || !dataNode.isArray()) {
				throw new FrameworkException("Paged user response must contain a data array.");
			}

			if (paginationNode == null || !paginationNode.isObject()) {
				throw new FrameworkException("Paged user response must contain pagination details.");
			}

			List<User> users = new ArrayList<>();

			for (JsonNode userNode : dataNode) {
				users.add(mapUser(userNode));
			}

			Pagination pagination = mapPagination(paginationNode);

			return new PagedResult<>(users, pagination);

		} catch (JsonProcessingException exception) {
			throw new FrameworkException("Unable to parse paged user response.", exception);
		}
	}

	private static User mapUser(JsonNode userNode) {
		return new User(JsonNodeUtils.getInteger(userNode, "id"), JsonNodeUtils.getText(userNode, "firstName"),
				JsonNodeUtils.getText(userNode, "lastName"), JsonNodeUtils.getText(userNode, "email"),
				JsonNodeUtils.getInteger(userNode, "age"), JsonNodeUtils.getBoolean(userNode, "isActive"),
				JsonNodeUtils.getLocalDateTime(userNode, "createdAt"));
	}

	private static Pagination mapPagination(JsonNode paginationNode) {

		return new Pagination(JsonNodeUtils.getInteger(paginationNode, "page"),
				JsonNodeUtils.getInteger(paginationNode, "size"),
				JsonNodeUtils.getLong(paginationNode, "totalElements"),
				JsonNodeUtils.getInteger(paginationNode, "totalPages"),
				JsonNodeUtils.getBoolean(paginationNode, "first"), JsonNodeUtils.getBoolean(paginationNode, "last"));
	}

	private static void validateResponse(ApiResponse response) {

		if (response == null) {
			throw new FrameworkException("Paged user API response cannot be null.");
		}

		if (response.getStatusCode() < 200 || response.getStatusCode() >= 300) {

			throw new FrameworkException("Paged user API request failed. Status: " + response.getStatusCode()
					+ ", Body: " + response.getBody());
		}

		if (response.getBody() == null || response.getBody().isBlank()) {

			throw new FrameworkException("Paged user API response body cannot be blank.");
		}
	}
}