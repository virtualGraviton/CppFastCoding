package listener;

import base.MyButton;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.Topic;
import window.mainWindow.mainWindowComp.TestCasePanel;

public interface RunFileListener {
    @Topic.ProjectLevel
    Topic<RunFileListener> RUN_FILE_TOPIC = Topic.create("Run File", RunFileListener.class);

    default void runStartAction(RunFileEvent event) {
    }

    default void runFinishAction(RunFileEvent event) {
    }

    record RunFileEvent(Project project, VirtualFile file, TestCasePanel.TestCaseDataList dataList, MyButton button) {

    }
}