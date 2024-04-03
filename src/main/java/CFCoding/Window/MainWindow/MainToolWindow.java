package CFCoding.Window.MainWindow;

import CFCoding.Window.MainWindow.MainWindowComp.MainPanel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MainToolWindow implements ToolWindowFactory {
    public MainPanel mainPanel;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        mainPanel = new MainPanel(project);
        toolWindow.getComponent().add(mainPanel);
        SwingUtilities.invokeLater(() -> toolWindow.getComponent().revalidate());
    }
}