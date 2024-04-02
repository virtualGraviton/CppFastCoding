package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NewButton extends MyButton {
    TestCasePanel testCasePanel;

    NewButton(TestCasePanel t) {
        super("New");
        testCasePanel = t;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddTestCase();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void AddTestCase() {
        testCasePanel.addTextCase();
    }
}
