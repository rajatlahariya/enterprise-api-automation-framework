package com.rajat.framework.api.builder;

import java.util.LinkedHashMap;
import java.util.Map;

import com.rajat.framework.api.model.user.UserSearchCriteria;

public final class QueryParameterBuilder {

	private QueryParameterBuilder() {
		// Prevent object creation
	}

	public static Map<String, Object> from(UserSearchCriteria criteria) {

		if (criteria == null) {
			throw new IllegalArgumentException("Search criteria cannot be null.");
		}

		Map<String, Object> parameters = new LinkedHashMap<>();

		putIfPresent(parameters, "firstName", criteria.getFirstName());

		putIfPresent(parameters, "username", criteria.getUsername());

		putIfPresent(parameters, "email", criteria.getEmail());

		putIfPresent(parameters, "role", criteria.getRole());

		putIfPresent(parameters, "isActive", criteria.getIsActive());

		putIfPresent(parameters, "minAge", criteria.getMinAge());

		putIfPresent(parameters, "maxAge", criteria.getMaxAge());

		parameters.put("page", criteria.getPage());
		parameters.put("size", criteria.getSize());
		parameters.put("sort", criteria.getSort());

		return parameters;
	}

	private static void putIfPresent(Map<String, Object> parameters, String key, Object value) {

		if (value != null) {
			parameters.put(key, value);
		}
	}
}