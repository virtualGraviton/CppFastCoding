package listener;

import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import storage.TestCaseStorage;
import window.mainWindow.mainWindowComp.ButtonPanel;
import window.mainWindow.mainWindowComp.TestCasePanel;

public class SelectedFileListener implements FileEditorManagerListener {
    @Override
    public void selectionChanged(@NotNull FileEditorManagerEvent event) {
        VirtualFile oldFile = event.getOldFile();
        event.getManager().getProject();
        if (TestCasePanel.getInstance() != null && oldFile != null && "cpp".equals(oldFile.getName())) {
            TestCaseStorage.getInstance().saveData(oldFile.getPath(), TestCasePanel.getInstance().exportData());
        }

        VirtualFile newFile = event.getNewFile();
        if (TestCasePanel.getInstance() != null) {
            if (newFile != null && "cpp".equals(newFile.getExtension())) {
                TestCasePanel.TestCaseDataList list = TestCaseStorage.getInstance().getData(newFile.getPath());
                TestCasePanel.getInstance().loadData(list);
            } else {
                TestCasePanel.getInstance().loadData(null);
            }
        }

        ButtonPanel.setCurFile(newFile);
    }
}
