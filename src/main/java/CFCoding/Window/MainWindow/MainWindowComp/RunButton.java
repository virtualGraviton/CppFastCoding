package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyButton;
import CFCoding.Services.Manager.CppFileManager;
import CFCoding.Services.Notice;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RunButton extends MyButton {
    private static final Logger logger = LoggerFactory.getLogger(MainPanel.class);
    private final TestCasePanel testCasePanel;

    RunButton() {
        super("Run");
        testCasePanel = MainPanel.getTestCasePanel();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                run();
            }
        });
    }

    private void run() {
        save();
        if (testCasePanel.getTestCaseNum() == 0) {
            Notice.showBalloon("ERROR", "No test case.");
            return;
        }
        for (Component c : getComponents()) {
            if (c instanceof TestCase t) {
                t.ClearText();
            }
        }
        CppFileManager cppFileManager = new CppFileManager();
        cppFileManager.asyncRunAll();
    }

    private void save() {
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
