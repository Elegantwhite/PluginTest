package com.msun.MsunEngineer.AIGettool;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DifyApiClient {
    private final String apiKey;
    private final String baseUrl;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public DifyApiClient(String apiKey, String baseUrl) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
                .build();
        this.mapper = new ObjectMapper();
    }

    public String callApi(String query) throws IOException {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Query cannot be empty");
        }

        // 构造请求体
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("inputs", new HashMap<>());
        requestBody.put("query", query);
        requestBody.put("response_mode", "blocking");
        requestBody.put("conversation_id", "");
        requestBody.put("user", "abc-123");

        // 转换为 JSON
        String jsonBody = mapper.writeValueAsString(requestBody);

        // 构造请求
        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(baseUrl)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        // 发送请求并返回响应
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + ": " + response.body().string());
            }
            return response.body().string();
        }
    }
}