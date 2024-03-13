package MyToolWindow.MainWindow.SpecComp;

import MyToolWindow.MainWindow.MyComp.MyButton;
import MyToolWindow.MainWindow.MyComp.MyPanel;
import PluginServices.CppManager;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.components.JBScrollPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        this.add(scrollPane);
        this.add(buttonPanel);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int w = e.getComponent().getWidth();
                int h = e.getComponent().getHeight();
                scrollPane.setPreferredSize(new Dimension(w - 75, h - 10));
                buttonPanel.setPreferredSize(new Dimension(60, h - 10));
                scrollPane.updateUI();
                buttonPanel.updateUI();
            }
        });
    }
}
