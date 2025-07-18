package com.msun.MsunEngineer.Settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MyPluginSettingsConfigurable implements Configurable {

    private JPanel mainPanel;
    private JTextField sampleTextField;

    public MyPluginSettingsConfigurable() {
        // 初始化 UI 组件
        mainPanel = new JPanel();
        sampleTextField = new JTextField(20);
        mainPanel.add(new JLabel("示例设置项:"));
        mainPanel.add(sampleTextField);
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Msun Plugin Settings";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return mainPanel;
    }

    @Override
    public boolean isModified() {
        // 判断是否修改了默认值（简单示例）
        return !sampleTextField.getText().isEmpty();
    }

    @Override
    public void apply() throws ConfigurationException {
        // 保存设置逻辑
        System.out.println("应用设置: " + sampleTextField.getText());
    }

    @Override
    public void reset() {
        // 重置设置
        sampleTextField.setText("");
    }

    @Override
    public void disposeUIResources() {
        // 清理资源
    }
}
