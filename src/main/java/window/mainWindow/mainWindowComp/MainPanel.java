package window.mainWindow.mainWindowComp;

import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class MainPanel extends JPanel {
    private static TestCasePanel testCasePanel;
    private final JBScrollPane scrollPane;
    private final ButtonPanel buttonpanel;

    public MainPanel(Project project) {
        if (testCasePanel == null) testCasePanel = new TestCasePanel(project);
        scrollPane = new JBScrollPane(testCasePanel);
        scrollPane.setBorder(JBUI.Borders.empty());
        testCasePanel.setBorder(JBUI.Borders.empty());
        buttonpanel = new ButtonPanel(project);
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        add(scrollPane);
        add(buttonpanel);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int w = e.getComponent().getWidth();
                int h = e.getComponent().getHeight();
                scrollPane.setPreferredSize(new Dimension(w - 70 - 15, h - 10));
                buttonpanel.setPreferredSize(new Dimension(70, h - 10));
                SwingUtilities.invokeLater(() -> updateUI());
            }
        });
    }
}
