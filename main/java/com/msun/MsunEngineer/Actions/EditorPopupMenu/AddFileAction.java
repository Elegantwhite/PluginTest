package com.msun.MsunEngineer.Actions.EditorPopupMenu;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;

public class AddFileAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;

        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor != null) {
            VirtualFile file = e.getData(CommonDataKeys.VIRTUAL_FILE);
            if (file != null && file.isInLocalFileSystem()) {
                String filePath = file.getPath();

                //发布插件后的展示功能
                Messages.showInfoMessage(filePath,"添加路径");

                // 发布事件到 MessageBus
//                EditorFileEventTopic publisher = project.getMessageBus()
//                        .syncPublisher(EditorFileEventTopic.TOPIC);
//                publisher.onFileSelected(filePath);
            }
        }
    }
}
