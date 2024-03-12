package toolWindow;

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
import PluginServices.CppManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class BottomPanel extends JPanel {
    JBScrollPane scrollPane;
    TestCasePanel testCasePanel = new TestCasePanel();
    CPanel buttonPanel;
    MyButton newButton = new MyButton("New");
    MyButton runButton = new MyButton("Run");
    MyButton clrButton = new MyButton("Clear");
    Project project;
    private static final Logger logger = LoggerFactory.getLogger(BottomPanel.class);

    BottomPanel(Project p) {
        project = p;
        scrollPane = new JBScrollPane(testCasePanel);
        newButton.addActionListener(this::AddTestCase);
        runButton.addActionListener(this::ExeRun);
        clrButton.addActionListener(this::ClearAll);
        buttonPanel = new CPanel(BoxLayout.Y_AXIS);
        buttonPanel.add(newButton);
        buttonPanel.add(runButton);
        buttonPanel.add(clrButton);

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

    private void ClearAll(ActionEvent e) {
        for (int i = 0; i < testCasePanel.testCaseNum; i++) {
            TestCase t = testCasePanel.getTestCase(i);
            t.ClearText(e);
        }
    }

    private void ExeRun(ActionEvent e) {
        SaveCpp();
        if (testCasePanel.testCaseNum == 0) {
            Messages.showMessageDialog("Empty test case.", "Error", Messages.getInformationIcon());
            return;
        }
        CppManager cm = new CppManager(project);
        if (cm.cppCompile() == cm.CompileFailed) return;

        for (int i = 0; i < testCasePanel.testCaseNum; i++) {
            cm.cppRun(testCasePanel.getTestCase(i));
        }
    }

    public void SaveCpp() {
        ActionManager actionManager = ActionManager.getInstance();
        try {
            AnAction myAction = actionManager.getAction("SaveAll");

            DataContext dataContext = DataManager.getInstance().getDataContext(this);
            myAction.actionPerformed(AnActionEvent.createFromDataContext("", null, dataContext));
        } catch (Exception exception) {
            logger.error("Save Failed", exception);
        }
    }

    private void AddTestCase(ActionEvent e) {
        testCasePanel.addTextCase();
    }
}
