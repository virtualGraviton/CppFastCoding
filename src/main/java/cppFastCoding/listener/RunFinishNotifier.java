package cppFastCoding.listener;

import com.intellij.util.messages.Topic;

public interface RunFinishNotifier {
    @Topic.ProjectLevel
    Topic<RunFinishNotifier> RUN_FINISH_TOPIC =
            Topic.create("Run Finish", RunFinishNotifier.class);

    void beforeAction();

    void afterAction();
}
