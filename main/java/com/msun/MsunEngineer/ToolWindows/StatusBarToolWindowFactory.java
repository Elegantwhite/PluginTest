package com.msun.MsunEngineer.ToolWindows;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.impl.status.EditorBasedStatusBarPopup;
import com.intellij.openapi.wm.impl.status.widget.StatusBarEditorBasedWidgetFactory;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StatusBarToolWindowFactory extends StatusBarEditorBasedWidgetFactory {
    @Override
    public @NotNull @NlsContexts.ConfigurableName String getDisplayName() {
        return "";
    }

    @Override
    public @NotNull @NonNls String getId() {
        return "";
    }

    @Override
    public @NotNull StatusBarWidget createWidget(@NotNull Project project) {

        return new EditorBasedStatusBarPopup(project, false){


            private static final String TEXT = "Msun";
            private String tooltip = "Msunagent";

            @NotNull
            @Override
            public String ID() {
                return getClass().getName() + ".widget";
            }

            @NotNull
            @Override
            protected StatusBarWidget createInstance(@NotNull Project project) {
                return createWidget(project);
            }

            @NotNull
            @Override
            protected WidgetState getWidgetState(@Nullable VirtualFile virtualFile) {
                WidgetState state =new WidgetState(tooltip, TEXT,true);

                return state;
            }

            @Nullable
            @Override
            protected ListPopup createPopup(@NotNull DataContext dataContext) {

                return JBPopupFactory.getInstance().createActionGroupPopup(
                        tooltip,
                        (ActionGroup) ActionManager.getInstance().getAction("Msun.StatusBarPopupMenu"),
                        dataContext,
                        JBPopupFactory.ActionSelectionAid.SPEEDSEARCH,
                        false
                );
            }
        };
    }

}
