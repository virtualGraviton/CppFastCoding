package cppFastCoding.window.mainWindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import cppFastCoding.util.Icons;
import cppFastCoding.window.mainWindow.mainWindowComp.MainPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class MainToolWindow implements ToolWindowFactory {
    public static boolean available = false;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        if (toolWindow.getComponent().getComponentCount() == 0) {
            toolWindow.getComponent().add(new MainPanel());
            available = true;
            toolWindow.getComponent().updateUI();
        }
        UIManager.addPropertyChangeListener(evt -> {
            if (!evt.getPropertyName().equals("lookAndFeel")) return;
            Color backgroundColor = UIManager.getColor("Panel.background");
            System.out.println(backgroundColor);
            double r = backgroundColor.getRed();
            double g = backgroundColor.getGreen();
            double b = backgroundColor.getBlue();
            double brightness = Math.sqrt(
                    0.299 * Math.pow(r / 255.0, 2) +
                            0.587 * Math.pow(g / 255.0, 2) +
                            0.114 * Math.pow(b / 255.0, 2)
            );
            Icons.isLight = brightness > 0.5;
        });
    }
}