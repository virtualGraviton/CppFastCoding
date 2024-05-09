package cppFastCoding.action;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import cppFastCoding.services.storage.TestCaseStorage;
import cppFastCoding.util.ObjGetter;
import cppFastCoding.util.TestCaseData;

public class SaveTestCaseAction {
    static public void actionPerformed() {
        Project project = ObjGetter.getProject();
        FileEditorManager editorManager = FileEditorManager.getInstance(project);
        FileEditor fileEditor = editorManager.getSelectedEditor();
        if (fileEditor == null) return;
        VirtualFile focusedFile = fileEditor.getFile();
        if (focusedFile == null) return;
        TestCaseStorage.getInstance().saveData(focusedFile.getPath(), new TestCaseData(ObjGetter.getMainPanel().getTestCasePanel()));
    }
}
