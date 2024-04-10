package CFCoding.Listener;

import CFCoding.Services.CppFileData;
import CFCoding.Services.Storage.TestCaseStorage;
import CFCoding.Window.MainWindow.MainWindowComp.MainPanel;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class SelectedFileListener implements FileEditorManagerListener {


    public SelectedFileListener() {
    }

    @Override
    public void selectionChanged(@NotNull FileEditorManagerEvent event) {
        VirtualFile oldFile = event.getOldFile();
        if (oldFile != null) {
            TestCaseStorage.getInstance().saveData(oldFile.getPath(), new CppFileData(MainPanel.getTestCasePanel()));
        }

        VirtualFile newFile = event.getNewFile();
        if (newFile != null) {
            MainPanel.setTestCasePanel(TestCaseStorage.getInstance().getData(newFile.getPath()));
        }
    }
}
