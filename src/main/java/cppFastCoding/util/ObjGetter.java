package cppFastCoding.util;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import cppFastCoding.window.mainWindow.mainWindowComp.MainPanel;

public class ObjGetter {
    public static Project getProject() {
        return ProjectManager.getInstance().getOpenProjects()[0];
    }

    public static ToolWindow getToolWindow() {
        return ToolWindowManager.getInstance(getProject()).getToolWindow("CppFastCodingPlugin");
    }

    public static MainPanel getMainPanel() {
        return (MainPanel) getToolWindow().getComponent().getComponent(0);
    }
}
