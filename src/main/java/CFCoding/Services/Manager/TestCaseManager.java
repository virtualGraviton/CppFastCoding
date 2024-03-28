package CFCoding.Services.Manager;

import CFCoding.Window.MainWindow.MainWindowComp.MainPanel;
import CFCoding.Window.MainWindow.MainWindowComp.TestCase;
import CFCoding.Window.MainWindow.MainWindowComp.TestCasePanel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public final class TestCaseManager {
    private final Project project;
    private static TestCaseManager instance;

    TestCaseManager(Project p) {
        project = p;
    }

    public static TestCaseManager getInstance(Project p) {
        if (instance == null) {
            instance = new TestCaseManager(p);
        }
        return instance;
    }

    private TestCasePanel getPanel() {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        ToolWindow toolWindow = toolWindowManager.getToolWindow("CppFastCodingPlugin");
        if (toolWindow != null) {
            JComponent c = toolWindow.getComponent();
            if (c instanceof MainPanel) {
                return ((MainPanel) c).getTestCasePanel();
            }
        }
        return null;
    }

    public ArrayList<String> getTestCase() {
        TestCasePanel tcp = getPanel();
        ArrayList<String> r = new ArrayList<>();
        if (tcp != null) {
            for (Component c : tcp.getComponents()) {
                if (c instanceof TestCase) {
                    r.add(((TestCase) c).inputField.getText());
                }
            }
        }
        return r;
    }

    public void setTestCase(ArrayList<String> inputs) {
        TestCasePanel tcp = getPanel();
        if (tcp != null) {
            tcp = new TestCasePanel();
            for (String s : inputs) {
                tcp.addTextCase(s);
            }
        }
    }
}
