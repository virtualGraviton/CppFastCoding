package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewButton extends MyButton {
    private final TestCasePanel testCasePanel;

    NewButton() {
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
