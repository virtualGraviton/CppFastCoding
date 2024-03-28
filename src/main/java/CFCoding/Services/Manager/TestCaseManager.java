package CFCoding.Services.Manager;

import CFCoding.Window.MainWindow.MainWindowComp.MainPanel;
import CFCoding.Window.MainWindow.MainWindowComp.TestCase;
import CFCoding.Window.MainWindow.MainWindowComp.TestCasePanel;

import java.awt.*;
import java.util.ArrayList;

public final class TestCaseManager {

    public static ArrayList<String> getTestCase() {
        TestCasePanel tcp = MainPanel.testCasePanel;
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

    public static void setTestCase(ArrayList<String> inputs) {
        MainPanel.testCasePanel.clear();
        for (String s : inputs) {
            MainPanel.testCasePanel.addTextCase(s);
        }
    }
}
