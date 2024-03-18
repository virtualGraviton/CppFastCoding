package CppFastCodingToolWindow.MainWindow.SpecComp;

import CppFastCodingToolWindow.MyComp.MyButton;

import java.awt.event.ActionEvent;

public class SC_NewButton extends MyButton {
    TestCasePanel testCasePanel;

    SC_NewButton(TestCasePanel t) {
        super("New");
        testCasePanel = t;
        this.addActionListener(this::AddTestCase);
    }

    private void AddTestCase(ActionEvent e) {
        testCasePanel.addTextCase();
    }
}
