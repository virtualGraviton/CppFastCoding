package CFCoding.Window.MainWindow.MainWindowComp;

import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class MainPanel extends JPanel {
    private static TestCasePanel testCasePanel;
    JBScrollPane scrollPane;
    ButtonPanel buttonpanel;
    Project project;

    public MainPanel(Project p) {
        project = p;
        if (testCasePanel == null)
            testCasePanel = new TestCasePanel();
        scrollPane = new JBScrollPane(testCasePanel);
        buttonpanel = new ButtonPanel(project, testCasePanel);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        this.add(scrollPane);
        this.add(buttonpanel);
        this.addComponentListener(new ComponentAdapter() {
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
}
