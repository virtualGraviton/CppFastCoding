package cppFastCoding.listener;

import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.vfs.VirtualFile;
import cppFastCoding.services.storage.TestCaseStorage;
import cppFastCoding.util.ObjGetter;
import cppFastCoding.util.TestCaseData;
import cppFastCoding.window.mainWindow.MainToolWindow;
import org.jetbrains.annotations.NotNull;

public class SelectedFileListener implements FileEditorManagerListener {
    public SelectedFileListener() {
    }

    @Override
    public void selectionChanged(@NotNull FileEditorManagerEvent event) {
        VirtualFile oldFile = event.getOldFile();
        if (MainToolWindow.available)
            if (oldFile != null) {
                TestCaseStorage.getInstance().saveData(oldFile.getPath(), new TestCaseData(ObjGetter.getMainPanel().getTestCasePanel()));
            }

        VirtualFile newFile = event.getNewFile();
        if (MainToolWindow.available)
            if (newFile != null && "cpp".equals(newFile.getExtension())) {
                ObjGetter.getMainPanel().setTestCasePanel(TestCaseStorage.getInstance().getData(newFile.getPath()));
            } else {
                ObjGetter.getMainPanel().setTestCasePanel(null);
            }
    }
}
