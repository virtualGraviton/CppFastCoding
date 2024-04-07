package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyPanel;

import javax.swing.*;
import java.awt.*;

public class TestCasePanel extends MyPanel {
    private int testCaseNum;

    public TestCasePanel() {
        super(BoxLayout.Y_AXIS, 7);
        testCaseNum = 0;
    }

    public void addTextCase() {
        testCaseNum++;
        TestCase testCase = new TestCase(testCaseNum);
        addComp(testCase);
        revalidate();
        repaint();
    }

    public void addTextCase(String init) {
        testCaseNum++;
        TestCase testCase = new TestCase(testCaseNum, init);
        addComp(testCase);
        revalidate();
        repaint();
    }

    public void removeTextCase(int idx) {
        testCaseNum--;
        remove(idx * 2 - 2);
        remove(idx * 2 - 2);
        revalidate();
        repaint();
        titleUdt();
    }

    public void titleUdt() {
        int count = 0;
        for (Component c : getComponents()) {
            if (c instanceof TestCase t) {
                t.changeTitle(++count);
            }
        }
    }

    public void clear() {
        for (Component c : getComponents()) {
            remove(c);
        }
        testCaseNum = 0;
    }

    public int getTestCaseNum() {
        return testCaseNum;
    }
}
