package CFCoding.Services.Manager;

import CFCoding.Window.MainWindow.MainToolWindow;
import CFCoding.Window.MainWindow.MainWindowComp.TestCase;
import CFCoding.Window.MainWindow.MainWindowComp.TestCasePanel;
import com.intellij.openapi.project.Project;

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
        if (MainToolWindow.mainPanel != null) {
            return MainToolWindow.mainPanel.getTestCasePanel();
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
