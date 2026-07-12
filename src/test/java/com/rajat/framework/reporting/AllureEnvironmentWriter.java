package com.rajat.framework.reporting;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import com.rajat.framework.core.configuration.ConfigManager;
import com.rajat.framework.core.environment.EnvironmentManager;

public final class AllureEnvironmentWriter {

	private static final Path OUTPUT_FILE = Path.of("target", "allure-results", "environment.properties");

	private AllureEnvironmentWriter() {
		// Prevent object creation
	}

	public static void write() {

		Properties properties = new Properties();

		properties.setProperty("Framework Version", "0.7.0-SNAPSHOT");

		properties.setProperty("Active Environment", EnvironmentManager.getActiveEnvironment().name());

		properties.setProperty("Base URL", ConfigManager.getBaseUrl());

		properties.setProperty("Java Version", System.getProperty("java.version"));

		properties.setProperty("Operating System",
				System.getProperty("os.name") + " " + System.getProperty("os.version"));

		properties.setProperty("Architecture", System.getProperty("os.arch"));

		createOutputDirectory();

		try (OutputStream outputStream = Files.newOutputStream(OUTPUT_FILE)) {

			properties.store(outputStream, "Allure Environment Information");

		} catch (IOException exception) {
			throw new IllegalStateException("Failed to write Allure environment information.", exception);
		}
	}

	private static void createOutputDirectory() {
		try {
			Files.createDirectories(OUTPUT_FILE.getParent());
		} catch (IOException exception) {
			throw new IllegalStateException("Failed to create Allure results directory.", exception);
		}
	}
}