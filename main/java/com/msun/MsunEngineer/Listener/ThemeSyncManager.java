package com.msun.MsunEngineer.Listener;

import com.intellij.util.ui.UIUtil;
import com.intellij.openapi.diagnostic.Logger;
import org.cef.browser.CefBrowser;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.*;
import java.awt.*;

// 引入正确的包

public class ThemeSyncManager {
    private static final Logger LOG = Logger.getInstance(ThemeSyncManager.class);
    private static CefBrowser browser;

    public static void init(CefBrowser cefBrowser) {
        browser = cefBrowser;
        sendCurrentTheme(); // 初始发送一次主题颜色


        // 监听 UIManager 主题变化
        UIManager.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("lookAndFeel".equals(evt.getPropertyName())) {
                    System.out.println("123456");
                    // 在这里执行你的主题同步逻辑
                    browser.executeJavaScript("console.log('Hello from IDEA')", browser.getURL(), 0);
                    sendCurrentTheme();
                }
            }
        });
    }

    private static void sendCurrentTheme() {
        ThemeInfo theme = new ThemeInfo();
        String json = theme.toJson();
        sendToWeb(json);
    }

    private static void sendToWeb(String json) {
        if (browser != null) {
            String jsCode = String.format("if (window.receiveIDETheme) { window.receiveIDETheme(%s); }", json);
            browser.executeJavaScript(jsCode, browser.getURL(), 0);
        }
    }

    private static class ThemeInfo {
        public String background = toHex(UIUtil.getPanelBackground());
        public String foreground = toHex(UIUtil.getLabelForeground());
        public String buttonBg = toHex(UIUtil.getButtonSelectColor());
        public String textFieldBg = toHex(UIUtil.getTextFieldBackground());

        private static String toHex(Color color) {
            return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        }

        public String toJson() {
            return String.format(
                    "{background: '%s', foreground: '%s', buttonBg: '%s', textFieldBg: '%s'}",
                    background, foreground, buttonBg, textFieldBg
            );
        }
    }
}
