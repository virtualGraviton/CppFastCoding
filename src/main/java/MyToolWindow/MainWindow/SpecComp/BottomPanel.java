package MyToolWindow.MainWindow.SpecComp;

import MyToolWindow.MyComp.MyPanel;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class BottomPanel extends JPanel {
    JBScrollPane scrollPane;
    TestCasePanel testCasePanel = new TestCasePanel();
    MyPanel buttonPanel;
    Project project;

    public BottomPanel(Project p) {
        project = p;
        scrollPane = new JBScrollPane(testCasePanel);
        buttonPanel = new MyPanel(BoxLayout.Y_AXIS);
        buttonPanel.add(new SC_NewButton(testCasePanel));
        buttonPanel.add(new SC_RunButton(project, testCasePanel));
        buttonPanel.add(new SC_ClrButton(testCasePanel));
        buttonPanel.add(new SC_SettingButton());

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        this.add(scrollPane);
        this.add(buttonPanel);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int w = e.getComponent().getWidth();
                int h = e.getComponent().getHeight();
                scrollPane.setPreferredSize(new Dimension(w - 115, h - 10));
                buttonPanel.setPreferredSize(new Dimension(100, h - 10));
                scrollPane.updateUI();
                buttonPanel.updateUI();
            }
        });
    }
}
