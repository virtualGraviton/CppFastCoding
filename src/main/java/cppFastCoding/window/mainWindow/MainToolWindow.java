package cppFastCoding.window.mainWindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import cppFastCoding.window.mainWindow.mainWindowComp.MainPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MainToolWindow implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        toolWindow.getComponent().add(new MainPanel());
        SwingUtilities.invokeLater(() -> toolWindow.getComponent().revalidate());
    }
}