package cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.buttons;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import cppFastCoding.base.MyButton;
import cppFastCoding.services.Notice;
import cppFastCoding.services.ResultStat;
import cppFastCoding.services.manager.CppFileManager;
import cppFastCoding.window.mainWindow.mainWindowComp.MainPanel;
import cppFastCoding.window.mainWindow.mainWindowComp.TestCase;
import cppFastCoding.window.mainWindow.mainWindowComp.TestCasePanel;
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
                if (!isEnabled()) {
                    Notice.showBalloon("WARN", "Do not click too fast.");
                    return;
                }
                setEnabled(false);
                run();
            }
        });
    }

    private void run() {
        save();
        if (testCasePanel.getTestCaseCount() == 0) {
            Notice.showBalloon("ERROR", "No test case.");
            return;
        }
        for (Component c : getComponents()) {
            if (c instanceof TestCase t) {
                t.setOutput("");
                t.setStat(ResultStat.PD);
            }
        }
        CppFileManager cppFileManager = new CppFileManager(this);
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
