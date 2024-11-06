package cppFastCoding.window.mainWindow.mainWindowComp;

import com.intellij.openapi.project.Project;
import com.intellij.ui.JBColor;
import cppFastCoding.base.MyLabel;
import cppFastCoding.listener.SaveActionNotifier;
import cppFastCoding.listener.SaveTestCaseContext;
import cppFastCoding.util.ObjUtil;
import cppFastCoding.util.TestCaseData;
import cppFastCoding.window.mainWindow.mainWindowComp.testCase.TestCase;

import javax.swing.*;
import java.awt.*;

public class TestCasePanel extends JPanel {
    private int testCaseCount;
    private static TestCasePanel instance;

    public TestCasePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        testCaseCount = 0;
        setBorder(BorderFactory.createLineBorder(JBColor.RED, 2));
        instance = this;
    }

    public TestCase getTestCase(int idx) {
        return (TestCase) getComponent(idx);
    }

    public void addTextCase() {
        TestCase testCase = new TestCase(++testCaseCount);
        add(testCase);
        updateUI();
        Project project = ObjUtil.getProject();
        SaveActionNotifier publish = project.getMessageBus()
                .syncPublisher(SaveActionNotifier.SAVE_ACTION_TOPIC);
        publish.afterAction(SaveTestCaseContext.create(this));
    }

    public void removeTextCase(int idx) {
        testCaseCount--;
        remove(idx);
        titleUdt();
        updateUI();
        Project project = ObjUtil.getProject();
        SaveActionNotifier publish = project.getMessageBus()
                .syncPublisher(SaveActionNotifier.SAVE_ACTION_TOPIC);
        publish.afterAction(SaveTestCaseContext.create(this));
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

    public static TestCasePanel getInstance() {
        return instance;
    }

    public static void setTestCasePanel(TestCaseData data) {
        for (Component component : instance.getComponents()) instance.remove(component);
        instance.setTestCaseCount(0);
        if (data == null) {
            instance.add(new MyLabel("Please select a cpp file."));
            return;
        }
        for (int i = 0; i < data.getTestCaseCount(); i++) {
            instance.addTextCase();
            TestCase tc = instance.getTestCase(i);
            tc.setInput(data.getInput(i));
            tc.setOutput(data.getOutput(i));
            tc.setExpectOutput(data.getExpectOutput(i));
            tc.setExpanded(data.isExpand(i));
        }
    }
}
