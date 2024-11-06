package cppFastCoding.listener;

import com.intellij.util.messages.Topic;

public interface SaveActionNotifier {
    @Topic.ProjectLevel
    Topic<SaveActionNotifier> SAVE_ACTION_TOPIC =
            Topic.create("Test Case Saved", SaveActionNotifier.class);

    void beforeAction();

    void afterAction(SaveTestCaseContext context);
}
