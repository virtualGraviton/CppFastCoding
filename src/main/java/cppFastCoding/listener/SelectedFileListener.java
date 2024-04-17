package cppFastCoding.listener;

import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.vfs.VirtualFile;
import cppFastCoding.services.storage.TestCaseStorage;
import cppFastCoding.util.TestCaseData;
import cppFastCoding.window.mainWindow.mainWindowComp.MainPanel;
import org.jetbrains.annotations.NotNull;

public class SelectedFileListener implements FileEditorManagerListener {
    public SelectedFileListener() {
    }

    @Override
    public void selectionChanged(@NotNull FileEditorManagerEvent event) {
        VirtualFile oldFile = event.getOldFile();
        if (oldFile != null) {
            TestCaseStorage.getInstance().saveData(oldFile.getPath(), new TestCaseData(MainPanel.getTestCasePanel()));
        }

        VirtualFile newFile = event.getNewFile();
        if (newFile != null && "cpp".equals(newFile.getExtension())) {
            MainPanel.setTestCasePanel(TestCaseStorage.getInstance().getData(newFile.getPath()));
        } else {
            MainPanel.setTestCasePanel(null);
        }
    }
}
