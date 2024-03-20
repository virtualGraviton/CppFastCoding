package CFCoding.Base;

import CFCoding.Window.MainWindow.MainWindowComp.TestCase;
import CFCoding.Window.MainWindow.MainWindowComp.TestCasePanel;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.awt.*;
import java.util.ArrayList;

public class TestCaseData {
    String CppFileName;
    ArrayList<String> inputs = new ArrayList<>();

    TestCaseData(Project p, TestCasePanel tcp) {
        FileEditorManager editorManager = FileEditorManager.getInstance(p);
        FileEditor fileEditor = editorManager.getSelectedEditor();
        if (fileEditor == null) {
            return;
        }
        VirtualFile focusedFile = fileEditor.getFile();
        CppFileName = focusedFile.getPath();
        System.out.println(CppFileName);
        for (Component c : tcp.getComponents()) {
            TestCase t = (TestCase) c;
            inputs.add(t.inputField.getText());
        }
    }
}
