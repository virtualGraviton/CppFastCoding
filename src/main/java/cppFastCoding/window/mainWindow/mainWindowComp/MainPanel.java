package cppFastCoding.window.mainWindow.mainWindowComp;

import com.intellij.ui.components.JBScrollPane;
import cppFastCoding.services.storage.TestCaseData;
import cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.ButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class MainPanel extends JPanel {
    private static TestCasePanel testCasePanel;
    private final JBScrollPane scrollPane;
    private final ButtonPanel buttonpanel;

    public MainPanel() {
        if (testCasePanel == null)
            testCasePanel = new TestCasePanel();
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
        while (tcp.getTestCaseCount() > 0) {
            tcp.removeTextCase(0);
        }
        for (int i = 0; i < data.testCaseCount; i++) {
            tcp.addTextCase();
            TestCase tc = tcp.getTestCase(i);
            tc.setInput(data.inputs.get(i));
            tc.setOutput(data.outputs.get(i));
            tc.setExpanded(data.isExpand.get(i));
        }
    }
}