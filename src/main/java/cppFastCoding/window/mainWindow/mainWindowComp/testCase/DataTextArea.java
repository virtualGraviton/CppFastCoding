package cppFastCoding.window.mainWindow.mainWindowComp.testCase;

import com.intellij.openapi.project.Project;
import com.intellij.ui.DocumentAdapter;
import com.intellij.util.ui.JBUI;
import cppFastCoding.base.MyTextArea;
import cppFastCoding.listener.SaveActionNotifier;
import cppFastCoding.listener.SaveTestCaseContext;
import cppFastCoding.util.ObjUtil;
import cppFastCoding.window.mainWindow.mainWindowComp.TestCasePanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.DocumentEvent;

public class DataTextArea extends MyTextArea {
    public DataTextArea() {
        getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                Project project = ObjUtil.getProject();
                SaveActionNotifier publish = project.getMessageBus()
                        .syncPublisher(SaveActionNotifier.SAVE_ACTION_TOPIC);
                publish.afterAction(SaveTestCaseContext.create(TestCasePanel.getInstance()));
            }
        });
        setAlignmentX(LEFT_ALIGNMENT);
        JBUI.Borders.empty(0, 0, 3, 10);
    }
}
