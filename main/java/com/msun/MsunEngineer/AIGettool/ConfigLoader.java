package com.msun.MsunEngineer.AIGettool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final String CONFIG_PATH = "config.properties";
    private String apiKey;
    private String baseUrl;

    public ConfigLoader() {
        loadConfig();
    }

    private void loadConfig() {
        Properties properties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(CONFIG_PATH)) {
            if (is == null) {
                throw new IOException("Cannot find " + CONFIG_PATH);
            }
            properties.load(is);
            apiKey = properties.getProperty("dify.api.key");
            baseUrl = properties.getProperty("dify.api.url");
            if (apiKey == null || baseUrl == null) {
                throw new IOException("Missing dify.api.key or dify.api.url in " + CONFIG_PATH);
            }
        } catch (IOException e) {
            System.err.println("Failed to load config: " + e.getMessage());
            // 回退默认值
            apiKey = "app-XMy64aVpHxOdqn0ZItCBFvPs";
            baseUrl = "http://119.3.215.230:8205/v1/chat-messages";
        }
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
