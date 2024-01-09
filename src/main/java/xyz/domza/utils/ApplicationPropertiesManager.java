package xyz.domza.utils;

import xyz.domza.DomzasWebUtilsApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ApplicationPropertiesManager {

    public static boolean checkConfig() {
        String filePathString = System.getProperty("user.home") + "/webspace-config/application.properties";
        Path filePath = Paths.get(filePathString);

        // Check if the file exists
        if (Files.exists(filePath)) {
            return true;
        } else {
            System.out.println("Default config file doesn't exist... Creating it now...");
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);

                String contentToWrite = getDefaultProperties();
                Files.write(filePath, contentToWrite.getBytes());

                System.out.println("Config File created successfully in ~/webspace-config/application.properties.\n" +
                        "Please fill out missing details in it.");
            } catch (IOException e) {
                System.err.println("Failed to create the file: " + e.getMessage());
            }
        }
        return false;
    }

    private static String getDefaultProperties() {
        // Load the resource using the class loader
        try (InputStream inputStream = DomzasWebUtilsApplication.class.getClassLoader().getResourceAsStream("default.properties")) {
            if (inputStream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                    StringBuilder content = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }

                    return content.toString();
                }
            } else {
                throw new IOException("Resource not found: default.properties");
            }
        } catch (IOException ignored) {
            return "Error reading default.properties";
        }
    }
}
