package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyButton;
import CFCoding.Services.CppManager;
import CFCoding.Services.Notice;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;

public class RunButton extends MyButton {
    private static final Logger logger = LoggerFactory.getLogger(BottomPanel.class);
    Project project;
    TestCasePanel testCasePanel;

    RunButton(Project p, TestCasePanel t) {
        super("Run");
        project = p;
        testCasePanel = t;
        this.addActionListener(this::ExeRun);
    }

    private void ExeRun(ActionEvent e) {
        SaveCpp();
        if (testCasePanel.testCaseNum == 0) {
            Notice.ShowBalloon("ERROR", "Empty test case.");
            return;
        }
        for (int i = 0; i < testCasePanel.testCaseNum; i++) {
            TestCase t = testCasePanel.getTestCase(i);
            t.ClearText(e);
        }
        CppManager cppManager = new CppManager(project, testCasePanel);
        cppManager.AsyncRunAll();
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
