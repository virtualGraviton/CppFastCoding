package MyToolWindow.MainWindow.SpecComp;

import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class BottomPanel extends JPanel {
    JBScrollPane scrollPane;
    TestCasePanel testCasePanel = new TestCasePanel();
    ButtonPanel buttonpanel;
    Project project;

    public BottomPanel(Project p) {
        project = p;
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
}
