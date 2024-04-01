package CFCoding.Listener;

import CFCoding.Services.Manager.TestCaseManager;
import CFCoding.Services.Storage.TestCaseStorage;
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
            TestCaseStorage.getInstance().saveTestCase(oldFile.getPath(), TestCaseManager.getTestCase());
        }

        VirtualFile newFile = event.getNewFile();
        if (newFile != null) {
            TestCaseManager.setTestCase(TestCaseStorage.getInstance().getTestCase(newFile.getPath()));
        }
    }
}
