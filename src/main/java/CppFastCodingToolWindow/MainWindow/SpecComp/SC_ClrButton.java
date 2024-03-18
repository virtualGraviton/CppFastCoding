package CppFastCodingToolWindow.MainWindow.SpecComp;

import CppFastCodingToolWindow.MyComp.MyButton;

import java.awt.event.ActionEvent;

public class SC_ClrButton extends MyButton {
    TestCasePanel testCasePanel;

    SC_ClrButton(TestCasePanel t) {
        super("Clear");
        testCasePanel = t;
        this.addActionListener(this::ClearAll);
    }

    private void ClearAll(ActionEvent e) {
        for (int i = 0; i < testCasePanel.testCaseNum; i++) {
            TestCase t = testCasePanel.getTestCase(i);
            t.ClearText(e);
        }
    }
}
