package CFCodingWindow.MainToolWindow.MainWindowComp;

import CFCodingBase.MyButton;

import java.awt.event.ActionEvent;

public class ClrButton extends MyButton {
    TestCasePanel testCasePanel;

    ClrButton(TestCasePanel t) {
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
