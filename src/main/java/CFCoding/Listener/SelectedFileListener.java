package CFCoding.Listener;

import CFCoding.Services.Manager.TestCaseManager;
import CFCoding.Services.Storage.TestCaseStorage;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class SelectedFileListener implements FileEditorManagerListener {

    private final Project project;

    public SelectedFileListener(Project project) {
        this.project = project;
    }

    @Override
    public void selectionChanged(@NotNull FileEditorManagerEvent event) {
        VirtualFile oldFile = event.getOldFile();
        if (oldFile != null) {
            System.out.println(oldFile.getPath());
            TestCaseStorage.getInstance().saveTestCase(oldFile.getPath(), TestCaseManager.getTestCase());
        }

        VirtualFile newFile = event.getNewFile();
        if (newFile != null) {
            System.out.println(newFile.getPath());
            TestCaseManager.setTestCase(TestCaseStorage.getInstance().getTestCase(newFile.getPath()));
        }
    }
}
