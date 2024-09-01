package cppFastCoding.window.mainWindow.mainWindowComp.testCase;

import com.intellij.ui.DocumentAdapter;
import com.intellij.util.ui.JBUI;
import cppFastCoding.action.SaveTestCaseAction;
import cppFastCoding.base.MyTextArea;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.DocumentEvent;

public class DataTextArea extends MyTextArea {
    public DataTextArea() {
        getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                SaveTestCaseAction.actionPerformed();
            }
        });
        setAlignmentX(LEFT_ALIGNMENT);
        JBUI.Borders.empty(0, 0, 3, 10);
    }
}
