package cppFastCoding.listener;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;

import java.awt.*;

public class SaveFileListener implements SaveFileNotifier {
    @Override
    public void beforeAction(Component context) {
        ActionManager actionManager = ActionManager.getInstance();
        try {
            AnAction myAction = actionManager.getAction("SaveAll");
            DataContext dataContext = DataManager.getInstance().getDataContext(context);
            myAction.actionPerformed(AnActionEvent.createFromDataContext("", null, dataContext));
        } catch (Exception ignored) {
        }
    }

    @Override
    public void afterAction() {

    }
}
