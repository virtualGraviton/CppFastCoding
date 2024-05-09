package cppFastCoding.window.mainWindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import cppFastCoding.window.mainWindow.mainWindowComp.MainPanel;
import org.jetbrains.annotations.NotNull;

public class MainToolWindow implements ToolWindowFactory {
    public static boolean available = false;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        if (toolWindow.getComponent().getComponentCount() == 0) {
            toolWindow.getComponent().add(new MainPanel());
            available = true;
            toolWindow.getComponent().updateUI();
        }
    }
}