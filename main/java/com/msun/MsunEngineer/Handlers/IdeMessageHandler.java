package com.msun.MsunEngineer.Handlers;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.application.ApplicationManager;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefQueryCallback;
import org.cef.handler.CefMessageRouterHandlerAdapter;
import org.cef.browser.CefMessageRouter;
import org.json.JSONObject;
import com.msun.MsunEngineer.AIGettool.GetAIService;

import java.io.IOException;

public class IdeMessageHandler extends CefMessageRouterHandlerAdapter {
    private final Project project;
    private final CefMessageRouter messageRouter;

    public IdeMessageHandler(Project project) {
        this.project = project;
        this.messageRouter = CefMessageRouter.create(new CefMessageRouter.CefMessageRouterConfig("ideMessage", "cancelIdeMessage"));
        this.messageRouter.addHandler(this, true);
    }

    public CefMessageRouter getMessageRouter() {
        return messageRouter;
    }

    @Override
    public boolean onQuery(CefBrowser browser, CefFrame frame, long queryId, String request, boolean persistent, CefQueryCallback callback) {
        try {
            JSONObject jsonRequest = new JSONObject(request);
            String type = jsonRequest.optString("type");
            String content = jsonRequest.optString("content");

            if ("textProcessing".equals(type) && content != null) {
                ApplicationManager.getApplication().invokeLater(() -> {
                    try {
                        GetAIService service = new GetAIService();
                        String result = service.getAI(content);
                        callback.success(result);
                    } catch (IOException e) {
                        callback.failure(500, "AI 调用失败: " + e.getMessage());
                    }
                });
                return true;
            } else if ("我从前端来".equals(request)) {
                ApplicationManager.getApplication().invokeLater(() -> {
                    Messages.showInfoMessage(project, request, "Message from Frontend");
                    callback.success("Message received: " + request);
                });
                return true;
            }
        } catch (Exception e) {
            callback.failure(500, "JSON 解析失败: " + e.getMessage());
            return true;
        }
        return false;
    }
}
