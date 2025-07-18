package com.msun.MsunEngineer.ToolWindows;

import com.google.gson.Gson;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import com.intellij.ui.jcef.JBCefBrowser;
import com.intellij.ui.jcef.JBCefBrowserBuilder;
import com.msun.MsunEngineer.Actions.EditorPopupMenu.EditorFileEventTopic;
import com.msun.MsunEngineer.Handlers.IdeMessageHandler;
import com.msun.MsunEngineer.Listener.ThemeSyncManager;
import org.cef.browser.CefBrowser;
import org.jetbrains.annotations.NotNull;

public class WindowToolFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        toolWindow.setAvailable(true);
        ContentManager contentManager = toolWindow.getContentManager();
        JBCefBrowserBuilder jbCefBrowserBuilder = new JBCefBrowserBuilder();
        jbCefBrowserBuilder.setUrl("http://localhost:5173");

        boolean isInternal = ApplicationManager.getApplication().isInternal();
        if (isInternal) {
            jbCefBrowserBuilder.setOffScreenRendering(false);
            jbCefBrowserBuilder.setEnableOpenDevToolsMenuItem(true);
        }

        JBCefBrowser jbCefBrowser = jbCefBrowserBuilder.build();
        CefBrowser cefBrowser = jbCefBrowser.getCefBrowser();
        // 在 createToolWindowContent 方法中添加
        ThemeSyncManager.init(cefBrowser);

        // 初始化并绑定消息处理器
        IdeMessageHandler messageHandler = new IdeMessageHandler(project);
        cefBrowser.getClient().addMessageRouter(messageHandler.getMessageRouter());

        Content content = contentManager.getFactory().createContent(jbCefBrowser.getComponent(), "my_window", false);
        contentManager.addContent(content);

        project.getMessageBus().connect().subscribe(EditorFileEventTopic.TOPIC, new EditorFileEventTopic() {
            @Override
            public void onFileSelected(String filePath) {
                // 向前端发送消息
                String script = String.format("console.log((%s))", new Gson().toJson(filePath));
                cefBrowser.executeJavaScript(script, cefBrowser.getURL(), 0);
            }
        });
    }
}
