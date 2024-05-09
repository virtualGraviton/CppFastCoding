package cppFastCoding.window.mainWindow.mainWindowComp.testCase;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.JBColor;
import cppFastCoding.base.MyButton;
import cppFastCoding.base.MyLabel;
import cppFastCoding.base.MyPanel;
import cppFastCoding.util.Icons;
import cppFastCoding.util.ObjGetter;
import cppFastCoding.util.stat.Stat;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestCase extends MyPanel {
    private final StatLabel statLabel = new StatLabel("Pending...");
    private final MyButton deleteButton = new MyButton();
    private final MyButton expandButton = new MyButton();
    private final MyPanel titleRow = new MyPanel(BoxLayout.X_AXIS, 10);
    private final DataTextArea inputField = new DataTextArea();
    private final DataTextArea outputField = new DataTextArea();
    private final DataTextArea expectOutputField = new DataTextArea();
    private boolean isExpanded = true;
    private int idx;
    private MyLabel title;
    private Dimension iconSize = new Dimension(20, 20);

    public TestCase(int testCaseNum) {
        super(BoxLayout.Y_AXIS, 7);
        init(testCaseNum);
    }

    private void init(int num) {
        idx = num;
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        String fontType = fontPreferences.getFontFamily();
        int fontSize = fontPreferences.getSize(fontType);

        title = new MyLabel("Test #%d".formatted(num + 1));
        title.setFont(new Font(fontType, Font.BOLD, fontSize + 4));
        titleRow.addComp(title);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getComponent().isEnabled())
                    ObjGetter.getMainPanel().getTestCasePanel().removeTextCase(idx);
            }
        });
        expandButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setExpanded(!isExpanded);
            }
        });
        titleRow.addComp(statLabel);
        titleRow.addComp(expandButton);
        titleRow.addComp(deleteButton);
        addComp(titleRow);
        titleRow.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component component = e.getComponent();
                int width = component == null ? 0 : component.getSize().width;
                int height = component == null ? 0 : component.getHeight();
                iconSize = new Dimension(height - 6, height - 6);
                deleteButton.setIcon(Icons.Delete.getIcon(iconSize));
                setExpanded(isExpanded);
                inputField.setMinWidth(width);
                outputField.setMinWidth(width);
                expectOutputField.setMinWidth(width);
                SwingUtilities.invokeLater(() -> updateUI());
            }
        });

        addComp(new MyLabel("Input:"));
        addComp(inputField);
        addComp(new MyLabel("Output:"));
        addComp(outputField);
        addComp(new MyLabel("Expected Output:"));
        addComp(expectOutputField);
        setBorder(new LineBorder(JBColor.lightGray, 5));
    }

    public void addComp(JComponent c) {
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(c);
        add(Box.createVerticalStrut(7));
    }

    public void changeTitle(int i) {
        idx = i;
        title.setText("Test #%d".formatted(i + 1));
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
                if (flag) {
                    c.setVisible(isExpanded);
                }
                if (!(c instanceof Box.Filler)) {
                    flag = true;
                }
            }
        }
        if (isExpanded) expandButton.setIcon(Icons.Collapse.getIcon(iconSize));
        else expandButton.setIcon(Icons.Expand.getIcon(iconSize));
    }
}
