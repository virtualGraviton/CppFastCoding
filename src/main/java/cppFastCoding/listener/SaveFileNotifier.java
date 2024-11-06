package cppFastCoding.listener;

import com.intellij.util.messages.Topic;

import java.awt.*;

public interface SaveFileNotifier {
    @Topic.ProjectLevel
    Topic<SaveFileNotifier> SAVE_FILE_TOPIC =
            Topic.create("CPP File Saved", SaveFileNotifier.class);

    void beforeAction(Component context);

    void afterAction();
}
