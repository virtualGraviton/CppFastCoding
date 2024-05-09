package cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.buttons;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import cppFastCoding.base.MyButton;
import cppFastCoding.util.ObjGetter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewButton extends MyButton {

    public NewButton() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddTestCase();
            }
        });
    }

    private void AddTestCase() {
        Project project = ProjectManager.getInstance().getOpenProjects()[0];
        FileEditorManager editorManager = FileEditorManager.getInstance(project);
        FileEditor fileEditor = editorManager.getSelectedEditor();
        if (fileEditor == null) {
            return;
        }
        VirtualFile focusedFile = fileEditor.getFile();
        if ("cpp".equals(focusedFile.getExtension()))
            ObjGetter.getMainPanel().getTestCasePanel().addTextCase();
    }
}
