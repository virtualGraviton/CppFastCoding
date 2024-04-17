package cppFastCoding.util;

import com.intellij.util.xmlb.annotations.XCollection;
import cppFastCoding.window.mainWindow.mainWindowComp.testCase.TestCase;
import cppFastCoding.window.mainWindow.mainWindowComp.testCase.TestCasePanel;

import java.awt.*;
import java.util.ArrayList;

public class TestCaseData {
    @XCollection
    private ArrayList<String> inputs;
    @XCollection
    private ArrayList<String> outputs;
    @XCollection
    private ArrayList<String> expectOutput;
    @XCollection
    private ArrayList<Boolean> isExpand;
    public int testCaseCount;

    public TestCaseData(TestCasePanel tcp) {
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
        expectOutput = new ArrayList<>();
        isExpand = new ArrayList<>();
        testCaseCount = tcp.getTestCaseCount();
        for (Component c : tcp.getComponents()) {
            if (c instanceof TestCase tc) {
                inputs.add(tc.getInput());
                outputs.add(tc.getOutput());
                expectOutput.add(tc.getExpectOutput());
                isExpand.add(tc.isExpanded());
            }
        }
    }

    public String getInput(int index) {
        return inputs.get(index);
    }

    public String getOutput(int index) {
        return outputs.get(index);
    }

    public String getExpectOutput(int index) {
        return expectOutput.get(index);
    }

    public boolean isExpand(int index) {
        return isExpand.get(index);
    }

    public TestCaseData() {
    }
}
