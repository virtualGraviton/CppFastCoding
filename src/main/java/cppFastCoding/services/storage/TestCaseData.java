package cppFastCoding.services.storage;

import com.intellij.util.xmlb.annotations.XCollection;
import cppFastCoding.window.mainWindow.mainWindowComp.TestCase;
import cppFastCoding.window.mainWindow.mainWindowComp.TestCasePanel;

import java.awt.*;
import java.util.ArrayList;

public class TestCaseData {
    @XCollection
    public ArrayList<String> inputs;
    @XCollection
    public ArrayList<String> outputs;
    @XCollection
    public ArrayList<Boolean> isExpand;
    @XCollection
    public int testCaseCount;

    public TestCaseData(TestCasePanel tcp) {
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
        isExpand = new ArrayList<>();
        testCaseCount = tcp.getTestCaseCount();
        for (Component c : tcp.getComponents()) {
            if (c instanceof TestCase tc) {
                inputs.add(tc.getInput());
                outputs.add(tc.getOutput());
                isExpand.add(tc.isExpanded());
            }
        }
    }

    public TestCaseData() {
    }
}
