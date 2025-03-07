package window.mainWindow.mainWindowComp.testCase;

import base.MyButton;
import base.MyLabel;
import com.intellij.openapi.project.Project;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import com.intellij.util.xmlb.annotations.Transient;
import com.intellij.util.xmlb.annotations.XCollection;
import util.CppFileRunner;
import util.Icons;
import util.Notice;
import util.RoundedBorder;
import window.mainWindow.mainWindowComp.TestCasePanel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestCase extends JPanel {
    private final StatLabel statLabel = new StatLabel("Pending...");
    private final MyButton deleteButton = new MyButton();
    private final MyButton expandButton = new MyButton();
    private final DataTextArea inputField;
    private final DataTextArea outputField;
    private final DataTextArea expectOutputField;
    private final MyLabel title = new MyLabel();
    private boolean isExpanded = true;
    private int idx;

    public TestCase(int testCaseNum, Project p) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        inputField = new DataTextArea(p);
        outputField = new DataTextArea(p);
        expectOutputField = new DataTextArea(p);
        idx = testCaseNum;
        title.setText("Test Case #" + idx);
        title.setFontSize(title.getFontSize() + 4);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getComponent().isEnabled()) TestCasePanel.getInstance().removeTextCase(idx - 1);
                else Notice.showBalloon("Error", "TestCase Running!");
            }
        });
        deleteButton.setDisabledIcon(Icons.Delete.getIcon());
        expandButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setExpanded(!isExpanded);
            }
        });
        JPanel titleRow = new JPanel();
        titleRow.setLayout(new BoxLayout(titleRow, BoxLayout.X_AXIS));
        titleRow.add(title);
        titleRow.add(statLabel);
        titleRow.add(expandButton);
        titleRow.add(deleteButton);
        titleRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(titleRow);
        titleRow.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component component = e.getComponent();
                int width = component == null ? 0 : component.getSize().width;
                deleteButton.setIcon(Icons.Delete.getIcon());
                setExpanded(isExpanded);
                inputField.setMinWidth(width);
                outputField.setMinWidth(width);
                expectOutputField.setMinWidth(width);
                SwingUtilities.invokeLater(() -> updateUI());
            }
        });
        MyLabel inputLabel = new MyLabel("Input:");
        MyLabel outputLabel = new MyLabel("Output:");
        MyLabel expectLabel = new MyLabel("Expected Output:");
        add(inputLabel);
        add(inputField);
        add(outputLabel);
        add(outputField);
        add(expectLabel);
        add(expectOutputField);
        setBorder(new CompoundBorder(JBUI.Borders.empty(3), new CompoundBorder(new RoundedBorder(16, 1, JBColor.gray), JBUI.Borders.empty(7, 7, 10, 7))));
    }

    public int getIdx() {
        return idx;
    }

    public void changeTitle(int i) {
        idx = i;
        title.setText("Test Case #" + idx);
    }

    public void setStat(CppFileRunner.Stat stat) {
        statLabel.setStat(stat);
        updateUI();
    }

    public String getInput() {
        return inputField.getText();
    }

    public void setInput(String input) {
        inputField.setText(input);
    }

    public String getOutput() {
        return outputField.getText();
    }

    public void setOutput(String output) {
        outputField.setText(output);
    }

    public String getExpectOutput() {
        return expectOutputField.getText();
    }

    public void setExpectOutput(String expectOutput) {
        expectOutputField.setText(expectOutput);
    }

    public MyButton getDeleteButton() {
        return deleteButton;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        if (isExpanded != expanded) {
            isExpanded = expanded;

            boolean flag = false;
            for (Component c : getComponents()) {
                if (flag) c.setVisible(isExpanded);
                if (!(c instanceof Box.Filler)) flag = true;
            }
        }
        if (isExpanded) expandButton.setIcon(Icons.Collapse.getIcon());
        else expandButton.setIcon(Icons.Expand.getIcon());
    }

    public TestCaseData exportData() {
        return TestCaseData.packageData(this);
    }

    public void loadData(TestCaseData data) {
        setInput(data.getInput());
        setOutput(data.getOutput());
        setExpectOutput(data.getExpectedOutput());
        setExpanded(data.isExpanded());
        if (data.getStat() == null) setStat(CppFileRunner.Stat.PD);
        else setStat(data.getStat());
    }

    public static class TestCaseData {
        @XCollection
        private String input;
        @XCollection
        private String output;
        @XCollection
        private String expectedOutput;
        @XCollection
        private boolean expanded;
        @Transient
        private CppFileRunner.Stat stat;
        private TestCase testCaseRef;

        public TestCaseData() {
        }

        public static TestCaseData packageData(TestCase testCase) {
            TestCaseData data = new TestCaseData();
            data.input = testCase.getInput();
            data.output = testCase.getOutput();
            data.expectedOutput = testCase.getExpectOutput();
            data.expanded = testCase.isExpanded();
            data.testCaseRef = testCase;
            return data;
        }

        public String getInput() {
            return input;
        }

        public String getOutput() {
            return output;
        }

        public void setOutput(String output) {
            this.output = output;
        }

        public String getExpectedOutput() {
            return expectedOutput;
        }

        public boolean isExpanded() {
            return expanded;
        }

        public void setExpanded(boolean expanded) {
            this.expanded = expanded;
        }

        public CppFileRunner.Stat getStat() {
            return stat;
        }

        public void setStat(CppFileRunner.Stat stat) {
            this.stat = stat;
        }

        public TestCase getTestCaseRef() {
            return testCaseRef;
        }
    }
}
