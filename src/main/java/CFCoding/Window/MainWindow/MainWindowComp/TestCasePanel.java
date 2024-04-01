package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyPanel;

import javax.swing.*;
import java.awt.*;

public class TestCasePanel extends MyPanel {
    int testCaseNum;

    public TestCasePanel() {
        super(BoxLayout.Y_AXIS);
        testCaseNum = 0;
    }

    public void addTextCase() {
        testCaseNum++;
        TestCase testCase = new TestCase(testCaseNum);
        this.AddComp(testCase);
        this.revalidate();
        this.repaint();
    }

    public void addTextCase(String init) {
        testCaseNum++;
        TestCase testCase = new TestCase(testCaseNum, init);
        this.AddComp(testCase);
        this.revalidate();
        this.repaint();
    }

    public void titleUdt() {
        int count = 0;
        for (Component c : this.getComponents()) {
            if (c instanceof TestCase t) {
                t.changeTitle(++count);
            }
        }
    }

    public void clear() {
        for (Component c : this.getComponents()) {
            this.remove(c);
        }
        testCaseNum = 0;
    }
}
