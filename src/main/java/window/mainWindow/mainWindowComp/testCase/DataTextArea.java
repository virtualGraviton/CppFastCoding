package window.mainWindow.mainWindowComp.testCase;

import base.MyTextArea;
import com.intellij.openapi.project.Project;
import com.intellij.ui.DocumentAdapter;
import com.intellij.util.ui.JBUI;
import listener.SaveTestCaseListener;
import org.jetbrains.annotations.NotNull;
import window.mainWindow.mainWindowComp.TestCasePanel;

import javax.swing.event.DocumentEvent;

public class DataTextArea extends MyTextArea {
    public DataTextArea(Project project) {
        getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                SaveTestCaseListener publish = project.getMessageBus()
                        .syncPublisher(SaveTestCaseListener.SAVE_ACTION_TOPIC);
                publish.saveAction(SaveTestCaseListener.SaveTestCaseEvent.create(TestCasePanel.getInstance()));
            }
        });
        setAlignmentX(LEFT_ALIGNMENT);
        JBUI.Borders.empty(0, 0, 3, 10);
    }
}
