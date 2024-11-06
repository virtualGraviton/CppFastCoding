package cppFastCoding.listener;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import cppFastCoding.window.mainWindow.mainWindowComp.TestCasePanel;
import org.jetbrains.annotations.Nullable;

public record SaveTestCaseContext(String filePath, TestCasePanel testCasePanel) {
    public static @Nullable SaveTestCaseContext create(TestCasePanel testCasePanel) {
        Project project = ProjectManager.getInstance().getOpenProjects()[0];
        FileEditorManager editorManager = FileEditorManager.getInstance(project);
        FileEditor fileEditor = editorManager.getSelectedEditor();
        if (fileEditor == null) return null;
        VirtualFile focusedFile = fileEditor.getFile();
        if (focusedFile == null) return null;
        return new SaveTestCaseContext(focusedFile.getPath(), testCasePanel);
    }
}