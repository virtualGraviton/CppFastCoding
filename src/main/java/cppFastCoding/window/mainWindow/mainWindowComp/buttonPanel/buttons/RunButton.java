package cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.buttons;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import cppFastCoding.action.SaveTestCaseAction;
import cppFastCoding.base.MyButton;
import cppFastCoding.services.Notice;
import cppFastCoding.services.manager.CppFileManager;
import cppFastCoding.util.Icons;
import cppFastCoding.util.ObjGetter;
import cppFastCoding.util.stat.Stat;
import cppFastCoding.window.mainWindow.mainWindowComp.testCase.TestCase;
import cppFastCoding.window.mainWindow.mainWindowComp.testCase.TestCasePanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RunButton extends MyButton {
    private static final Logger logger = LoggerFactory.getLogger(RunButton.class);

    public RunButton() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isEnabled()) {
                    Notice.showBalloon("WARN", "Do not click too fast.");
                    return;
                }
                setEnabled(false);
                TestCasePanel tcp = ObjGetter.getMainPanel().getTestCasePanel();
                for (int i = 0; i < tcp.getTestCaseCount(); i++) {
                    TestCase tc = tcp.getTestCase(i);
                    tc.getDeleteButton().setEnabled(false);
                }
                run();
            }
        });
        setIcon(Icons.Run.getIcon());
        setDisabledIcon(Icons.Run.getIcon());
    }

    private void run() {
        save();
        if (ObjGetter.getMainPanel().getTestCasePanel().getTestCaseCount() == 0) {
            setEnabled(true);
            Notice.showBalloon("ERROR", "No test case.");
            return;
        }
        for (Component c : ObjGetter.getMainPanel().getTestCasePanel().getComponents()) {
            if (c instanceof TestCase t) {
                t.setOutput(null);
                t.setStat(Stat.PD);
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
        SaveTestCaseAction.actionPerformed();
    }
}
