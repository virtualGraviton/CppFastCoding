package cppFastCoding.util;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;

public class ObjUtil {
    public static Project getProject() {
        return ProjectManager.getInstance().getOpenProjects()[0];
    }
}
