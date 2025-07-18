package com.msun.MsunEngineer.Actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.msun.MsunEngineer.Settings.MyPluginSettingsConfigurable;
import org.jetbrains.annotations.NotNull;

public class OpenSettingsAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // 打开设置窗口并跳转到你的配置页
        ShowSettingsUtil.getInstance()
                .showSettingsDialog(e.getProject(), MyPluginSettingsConfigurable.class);
    }
}
