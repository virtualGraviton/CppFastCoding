package MyToolWindow.MainWindow;

import MyToolWindow.MainWindow.SpecComp.BottomPanel;
import PluginServices.MyNotice;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class MainToolWindow implements ToolWindowFactory {
    public BottomPanel bottomPanel;
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        bottomPanel = new BottomPanel(project);
        toolWindow.getComponent().add(bottomPanel);
        SwingUtilities.invokeLater(() -> toolWindow.getComponent().revalidate());
    }
}