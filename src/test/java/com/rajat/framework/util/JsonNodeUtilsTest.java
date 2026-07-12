package com.rajat.framework.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import java.time.LocalDateTime;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajat.framework.core.exceptions.FrameworkException;
import com.rajat.framework.testgroup.TestGroups;

@Test(groups = {TestGroups.UNIT, TestGroups.REGRESSION})
public class JsonNodeUtilsTest {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Test
	public void shouldExtractSupportedJsonValues() throws Exception {

		String json = "{" + "\"name\":\"Rajat\"," + "\"age\":29," + "\"active\":true,"
				+ "\"createdAt\":\"2026-07-06T13:36:27.564827\"" + "}";

		JsonNode node = OBJECT_MAPPER.readTree(json);

		assertThat(JsonNodeUtils.getText(node, "name"), equalTo("Rajat"));

		assertThat(JsonNodeUtils.getInteger(node, "age"), equalTo(29));

		assertThat(JsonNodeUtils.getBoolean(node, "active"), equalTo(true));

		assertThat(JsonNodeUtils.getLocalDateTime(node, "createdAt"),
				equalTo(LocalDateTime.parse("2026-07-06T13:36:27.564827")));
	}

	@Test
	public void shouldReturnNullWhenOptionalFieldIsMissing() throws Exception {

		JsonNode node = OBJECT_MAPPER.readTree("{}");

		assertThat(JsonNodeUtils.getText(node, "missing"), nullValue());
	}

	@Test(expectedExceptions = FrameworkException.class, groups = { TestGroups.NEGATIVE })
	public void shouldRejectMissingRequiredText() throws Exception {

		JsonNode node = OBJECT_MAPPER.readTree("{}");

		JsonNodeUtils.getRequiredText(node, "accessToken");
	}
}