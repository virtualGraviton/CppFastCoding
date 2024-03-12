package MyToolWindow.MainWindow;

import MyToolWindow.MainWindow.SpecComp.BottomPanel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class MainToolWindow implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        toolWindow.getComponent().add(new BottomPanel(project));
        SwingUtilities.invokeLater(() -> toolWindow.getComponent().revalidate());
    }
}