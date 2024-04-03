package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyButton;
import CFCoding.Services.Manager.CppFileManager;
import CFCoding.Services.Notice;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RunButton extends MyButton {
    private static final Logger logger = LoggerFactory.getLogger(MainPanel.class);
    Project project;
    TestCasePanel testCasePanel;

    RunButton(Project p, TestCasePanel t) {
        super("Run");
        project = p;
        testCasePanel = t;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ExeRun();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void ExeRun() {
        SaveCpp();
        if (testCasePanel.testCaseNum == 0) {
            Notice.ShowBalloon("ERROR", "No test case.");
            return;
        }
        for (Component c : this.getComponents()) {
            if (c instanceof TestCase t) {
                t.ClearText();
            }
        }
        CppFileManager cppFileManager = new CppFileManager(project, testCasePanel);
        cppFileManager.AsyncRunAll();
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
}
