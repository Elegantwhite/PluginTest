package com.msun.MsunEngineer.ToolWindows;


import com.intellij.openapi.application.ApplicationManager;
import com.intellij.ui.jcef.JBCefBrowser;
import com.intellij.ui.jcef.JBCefBrowserBuilder;
import com.msun.MsunEngineer.Listener.ThemeSyncManager;

public class BrowserManager {

    private static JBCefBrowser jbCefBrowser;
    public static JBCefBrowser createBrowser() {
        JBCefBrowserBuilder jbCefBrowserBuilder = new JBCefBrowserBuilder();
        jbCefBrowserBuilder.setUrl("http://localhost:5173");

        boolean isInternal = ApplicationManager.getApplication().isInternal();
        if (isInternal) {
            jbCefBrowserBuilder.setOffScreenRendering(false);
            jbCefBrowserBuilder.setEnableOpenDevToolsMenuItem(true);
        }

        jbCefBrowser = jbCefBrowserBuilder.build();

        // 初始化 ThemeSyncManager
        ThemeSyncManager.init(jbCefBrowser.getCefBrowser());

        return jbCefBrowser;
    }

    public static JBCefBrowser getJbCefBrowser() {
        if (jbCefBrowser == null) {
            createBrowser(); // 如果不存在，则重新创建
        }
        return jbCefBrowser;
    }
}
