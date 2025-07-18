package com.msun.MsunEngineer.AIGettool;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseProcessor {
    private final ObjectMapper mapper;

    public ResponseProcessor() {
        this.mapper = new ObjectMapper();
    }

    public String processResponse(String jsonResponse) throws IOException {
        try {
            Map<String, Object> responseMap = mapper.readValue(jsonResponse, Map.class);
            String answer = (String) responseMap.getOrDefault("answer", "No answer field in response");
            return removeThinkSection(answer);
        } catch (Exception e) {
            throw new IOException("Error parsing response: " + e.getMessage());
        }
    }

    private String removeThinkSection(String text) {
        Pattern pattern = Pattern.compile("<think>.*?</think>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        String cleanedText = matcher.replaceAll("").trim();
        return cleanedText.replaceAll("\\n\\s*\\n", "").trim();
    }
}