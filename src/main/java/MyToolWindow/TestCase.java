package MyToolWindow;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.JBColor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TestCase extends CPanel {
    JLabel title;
    public JLabel statLabel = new JLabel("Pending...");
    CPanel titleRow = new CPanel(BoxLayout.X_AXIS);
    MyButton clearTextButton = new MyButton("ClearText");
    MyButton deleteButton = new MyButton("DeleteTestCase");
    CPanel buttonRow = new CPanel(BoxLayout.X_AXIS);
    JLabel inputLabel = new JLabel("Input:");
    public MyTextArea inputField = new MyTextArea();
    JLabel outputLabel = new JLabel("Output:");
    public MyTextArea outputField = new MyTextArea();
    String fontType;
    int fontSize;

    TestCase(int num) {
        super(BoxLayout.Y_AXIS);
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        fontType = fontPreferences.getFontFamily();
        fontSize = fontPreferences.getSize(fontType);

        title = new JLabel("TestCase #%d    ".formatted(num));
        title.setFont(new Font(fontType, Font.BOLD, fontSize + 2));
        titleRow.add(title);
        statLabel.setFont(new Font(fontType, Font.BOLD, fontSize + 2));
        statLabel.setForeground(JBColor.blue);
        titleRow.add(statLabel);
        this.AddComp(titleRow);

        clearTextButton.addActionListener(this::ClearText);
        clearTextButton.setForeground(JBColor.red);
        deleteButton.addActionListener(this::Delete);
        deleteButton.setForeground(JBColor.red);
        buttonRow.add(clearTextButton);
        buttonRow.add(deleteButton);

        this.AddComp(buttonRow);
        this.AddComp(inputLabel);
        this.AddComp(inputField);
        this.AddComp(outputLabel);
        this.AddComp(outputField);

        this.setBorder(new LineBorder(JBColor.gray, 5, true));
    }

    public void AddComp(JComponent c) {
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(c);
        this.add(Box.createVerticalStrut(7));
    }

    public void changeTitle(int i) {
        title.setText("TestCase #%d    ".formatted(i));
    }

    public void ClearText(ActionEvent e) {
        this.outputField.setText("");
        this.statLabel.setText("Pending...");
        this.statLabel.setForeground(JBColor.blue);
    }

    private void Delete(ActionEvent e) {
        CPanel buttonR = (CPanel) deleteButton.getParent();
        TestCase testCase = (TestCase) buttonR.getParent();
        TestCasePanel gPanel = (TestCasePanel) testCase.getParent();
        gPanel.remove(testCase);
        gPanel.revalidate();
        gPanel.repaint();
        gPanel.titleUdt();
        gPanel.testCaseNum--;
    }
}