package cppFastCoding.window.mainWindow.mainWindowComp.testCase;

import cppFastCoding.base.MyPanel;

import javax.swing.*;
import java.awt.*;

public class TestCasePanel extends MyPanel {
    private int testCaseCount;

    public TestCasePanel() {
        super(BoxLayout.Y_AXIS, 7);
        testCaseCount = 0;
    }

    public TestCase getTestCase(int idx) {
        return (TestCase) getComponent(idx * 2);
    }

    public void addTextCase() {
        TestCase testCase = new TestCase(testCaseCount++);
        addComp(testCase);
        revalidate();
        repaint();
    }

    public void removeTextCase(int idx) {
        testCaseCount--;
        remove(idx * 2);
        remove(idx * 2);
        revalidate();
        repaint();
        titleUdt();
    }

    private void titleUdt() {
        int count = 0;
        for (Component c : getComponents()) {
            if (c instanceof TestCase t) {
                t.changeTitle(count++);
            }
        }
    }

    public int getTestCaseCount() {
        return testCaseCount;
    }

    public void setTestCaseCount(int testCaseCount) {
        this.testCaseCount = testCaseCount;
    }
}
