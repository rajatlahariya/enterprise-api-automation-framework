package com.rajat.framework.reporting;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.nio.file.Files;
import java.nio.file.Path;

import org.testng.annotations.Test;

public class AllureEnvironmentWriterTest {

	@Test
	public void shouldWriteAllureEnvironmentFile() {

		AllureEnvironmentWriter.write();

		Path environmentFile = Path.of("target", "allure-results", "environment.properties");

		assertThat(Files.exists(environmentFile), equalTo(true));
	}
}