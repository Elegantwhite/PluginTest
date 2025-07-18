package com.msun.MsunEngineer.Actions.EditorPopupMenu;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

public class MyCustomAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        // 当菜单项被点击时执行的操作
        Messages.showInfoMessage("你点击了自定义菜单项！", "提示");
    }
}
