package com.msun.MsunEngineer.Actions.EditorPopupMenu;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class AddSelectionAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        // 获取当前 Editor 对象
        Editor editor = e.getData(CommonDataKeys.EDITOR);

        if (editor != null) {
            // 获取当前选中的文本
            String selectedText = editor.getSelectionModel().getSelectedText();

            //插件展示
            Messages.showInfoMessage("当前选中的文本是：" + selectedText, "添加选中文本");
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        // 默认设置为不可用
        boolean canPerform = false;
        // 获取当前 Editor
        Editor editor = e.getData(CommonDataKeys.EDITOR);

        if (editor != null) {
            // 获取选中的文本
            String selectedText = editor.getSelectionModel().getSelectedText();
            canPerform = selectedText != null && !selectedText.isEmpty();
        }
        // 设置菜单项是否可用
        e.getPresentation().setEnabled(canPerform);
    }
}
