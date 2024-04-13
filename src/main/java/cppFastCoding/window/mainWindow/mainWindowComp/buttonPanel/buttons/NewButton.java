package cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.buttons;

import cppFastCoding.base.MyButton;
import cppFastCoding.window.mainWindow.mainWindowComp.MainPanel;
import cppFastCoding.window.mainWindow.mainWindowComp.testCase.TestCasePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewButton extends MyButton {
    private final TestCasePanel testCasePanel;

    public NewButton() {
        super("New");
        testCasePanel = MainPanel.getTestCasePanel();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddTestCase();
            }
        });
    }

    private void AddTestCase() {
        testCasePanel.addTextCase();
    }
}
