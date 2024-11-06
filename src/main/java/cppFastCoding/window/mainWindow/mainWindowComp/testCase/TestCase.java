package cppFastCoding.window.mainWindow.mainWindowComp.testCase;

import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import cppFastCoding.base.MyButton;
import cppFastCoding.base.MyLabel;
import cppFastCoding.util.Icons;
import cppFastCoding.util.RoundedBorder;
import cppFastCoding.util.stat.Stat;
import cppFastCoding.window.mainWindow.mainWindowComp.TestCasePanel;

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
    private final DataTextArea inputField = new DataTextArea();
    private final DataTextArea outputField = new DataTextArea();
    private final DataTextArea expectOutputField = new DataTextArea();
    private final MyLabel title = new MyLabel();
    private boolean isExpanded = true;
    private int idx;

    public TestCase(int testCaseNum) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        idx = testCaseNum;
        title.setText("Test Case #" + idx);
        title.setFontSize(title.getFontSize() + 4);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getComponent().isEnabled())
                    TestCasePanel.getInstance().removeTextCase(idx - 1);
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
        setBorder(new CompoundBorder(JBUI.Borders.empty(3),
                new CompoundBorder(new RoundedBorder(16, 1.5F, JBColor.BLACK),
                        JBUI.Borders.empty(7, 7, 10, 7))));
    }

    public void changeTitle(int i) {
        idx = i;
        title.setText("Test Case #" + idx);
    }

    public void setStat(Stat stat) {
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
}
