package com.msun.MsunEngineer.AIGettool;

import java.io.IOException;

public class GetAIService {
    private final ConfigLoader configLoader;
    private final DifyApiClient apiClient;
    private final ResponseProcessor responseProcessor;

    public GetAIService() {
        this.configLoader = new ConfigLoader();
        this.apiClient = new DifyApiClient(configLoader.getApiKey(), configLoader.getBaseUrl());
        this.responseProcessor = new ResponseProcessor();
    }

    public String getAI(String chatMessage) throws IOException {
        String jsonResponse = apiClient.callApi(chatMessage);
        return responseProcessor.processResponse(jsonResponse);
    }

    public static void main(String[] args) {
        GetAIService service = new GetAIService();
        try {
            String result = service.getAI("测试 AI 文本生成");
            System.out.println("生成结果: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}