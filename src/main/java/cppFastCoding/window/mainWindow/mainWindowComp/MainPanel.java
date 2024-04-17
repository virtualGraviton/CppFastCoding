package cppFastCoding.window.mainWindow.mainWindowComp;

import com.intellij.ui.components.JBScrollPane;
import cppFastCoding.base.MyLabel;
import cppFastCoding.util.TestCaseData;
import cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.ButtonPanel;
import cppFastCoding.window.mainWindow.mainWindowComp.testCase.TestCase;
import cppFastCoding.window.mainWindow.mainWindowComp.testCase.TestCasePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class MainPanel extends JPanel {
    private static TestCasePanel testCasePanel;
    private final JBScrollPane scrollPane;
    private final ButtonPanel buttonpanel;

    public MainPanel() {
        if (testCasePanel == null) testCasePanel = new TestCasePanel();
        scrollPane = new JBScrollPane(testCasePanel);
        buttonpanel = new ButtonPanel();
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        add(scrollPane);
        add(buttonpanel);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int w = e.getComponent().getWidth();
                int h = e.getComponent().getHeight();
                scrollPane.setPreferredSize(new Dimension(w - buttonpanel.getW() - 15, h - 10));
                buttonpanel.setPreferredSize(new Dimension(buttonpanel.getW(), h - 10));
                scrollPane.updateUI();
                buttonpanel.updateUI();
            }
        });
    }

    public static TestCasePanel getTestCasePanel() {
        if (testCasePanel == null) testCasePanel = new TestCasePanel();
        return testCasePanel;
    }

    public static void setTestCasePanel(TestCaseData data) {
        TestCasePanel tcp = getTestCasePanel();
        for (Component component : tcp.getComponents()) {
            tcp.remove(component);
        }
        tcp.setTestCaseCount(0);
        if (data == null) {
            tcp.addComp(new MyLabel("Please select a cpp file."));
            return;
        }
        for (int i = 0; i < data.getTestCaseCount(); i++) {
            tcp.addTextCase();
            TestCase tc = tcp.getTestCase(i);
            tc.setInput(data.getInput(i));
            tc.setOutput(data.getOutput(i));
            tc.setExpectOutput(data.getExpectOutput(i));
            tc.setExpanded(data.isExpand(i));
        }
    }
}
