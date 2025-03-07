package listener;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.Topic;
import org.jetbrains.annotations.Nullable;
import window.mainWindow.mainWindowComp.TestCasePanel;

public interface SaveTestCaseListener {
    @Topic.ProjectLevel
    Topic<SaveTestCaseListener> SAVE_ACTION_TOPIC =
            Topic.create("Test Case Saved", SaveTestCaseListener.class);

    void saveAction(SaveTestCaseEvent event);

    record SaveTestCaseEvent(String filePath, TestCasePanel testCasePanel) {
        public static @Nullable SaveTestCaseListener.SaveTestCaseEvent create(TestCasePanel testCasePanel) {
            Project project = ProjectManager.getInstance().getOpenProjects()[0];
            FileEditorManager editorManager = FileEditorManager.getInstance(project);
            FileEditor fileEditor = editorManager.getSelectedEditor();
            if (fileEditor == null) return null;
            VirtualFile focusedFile = fileEditor.getFile();
            if (focusedFile == null) return null;
            return new SaveTestCaseEvent(focusedFile.getPath(), testCasePanel);
        }
    }
}
