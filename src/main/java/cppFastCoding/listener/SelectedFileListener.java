package cppFastCoding.listener;

import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.vfs.VirtualFile;
import cppFastCoding.services.storage.TestCaseStorage;
import cppFastCoding.util.TestCaseData;
import cppFastCoding.window.mainWindow.MainToolWindow;
import cppFastCoding.window.mainWindow.mainWindowComp.TestCasePanel;
import org.jetbrains.annotations.NotNull;

public class SelectedFileListener implements FileEditorManagerListener {
    @Override
    public void selectionChanged(@NotNull FileEditorManagerEvent event) {
        VirtualFile oldFile = event.getOldFile();
        event.getManager().getProject();
        if (MainToolWindow.available && oldFile != null) {
            TestCaseStorage.getInstance().saveData(oldFile.getPath(), new TestCaseData(TestCasePanel.getInstance()));
        }

        VirtualFile newFile = event.getNewFile();
        if (MainToolWindow.available) {
            if (newFile != null && "cpp".equals(newFile.getExtension())) {
                TestCasePanel.setTestCasePanel(TestCaseStorage.getInstance().getData(newFile.getPath()));
            } else {
                TestCasePanel.setTestCasePanel(null);
            }
        }
    }
}
