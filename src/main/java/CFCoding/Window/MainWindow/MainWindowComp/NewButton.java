package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyButton;

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