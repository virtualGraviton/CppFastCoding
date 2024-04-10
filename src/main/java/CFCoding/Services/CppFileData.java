package CFCoding.Services;

import CFCoding.Window.MainWindow.MainWindowComp.TestCase;
import CFCoding.Window.MainWindow.MainWindowComp.TestCasePanel;
import com.intellij.util.xmlb.annotations.XCollection;

import java.awt.*;
import java.util.ArrayList;

public class CppFileData {
    @XCollection
    public ArrayList<String> inputs;

    @XCollection
    public int testCaseCount;

    public CppFileData(TestCasePanel tcp) {
        inputs = new ArrayList<>();
        testCaseCount = tcp.getTestCaseCount();
        for (Component c : tcp.getComponents()) {
            if (c instanceof TestCase tc) {
                inputs.add(tc.getInput());
            }
        }
    }

    public CppFileData() {
    }
}
