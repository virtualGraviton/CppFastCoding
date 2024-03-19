package CFCodingWindow.MainToolWindow.MainWindowComp;

import CFCodingBase.MyButton;

import java.awt.event.ActionEvent;

public class NewButton extends MyButton {
    TestCasePanel testCasePanel;

    NewButton(TestCasePanel t) {
        super("New");
        testCasePanel = t;
        this.addActionListener(this::AddTestCase);
    }

    private void AddTestCase(ActionEvent e) {
        testCasePanel.addTextCase();
    }
}
