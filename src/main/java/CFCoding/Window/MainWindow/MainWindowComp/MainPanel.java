package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Services.CppFileData;
import com.intellij.ui.components.JBScrollPane;

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

    public static void setTestCasePanel(CppFileData data) {
        while (testCasePanel.getTestCaseCount() > 0) {
            testCasePanel.removeTextCase(0);
        }
        for (int i = 0; i < data.testCaseCount; i++) {
            testCasePanel.addTextCase();
            TestCase tc = testCasePanel.getTestCase(i);
            tc.setInput(data.inputs.get(i));
        }
    }
}
