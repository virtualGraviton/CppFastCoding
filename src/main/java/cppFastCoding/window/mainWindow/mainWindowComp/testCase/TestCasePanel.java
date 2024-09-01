package cppFastCoding.window.mainWindow.mainWindowComp.testCase;

import com.intellij.ui.JBColor;
import cppFastCoding.action.SaveTestCaseAction;

import javax.swing.*;
import java.awt.*;

public class TestCasePanel extends JPanel {
    private int testCaseCount;

    public TestCasePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        testCaseCount = 0;
        setBorder(BorderFactory.createLineBorder(JBColor.RED, 2));
    }

    public TestCase getTestCase(int idx) {
        return (TestCase) getComponent(idx);
    }

    public void addTextCase() {
        TestCase testCase = new TestCase(++testCaseCount);
        add(testCase);
        updateUI();
        SaveTestCaseAction.actionPerformed();
    }

    public void removeTextCase(int idx) {
        testCaseCount--;
        remove(idx);
        titleUdt();
        updateUI();
        SaveTestCaseAction.actionPerformed();
    }

    private void titleUdt() {
        int count = 0;
        for (Component c : getComponents()) {
            if (c instanceof TestCase t) {
                t.changeTitle(++count);
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
