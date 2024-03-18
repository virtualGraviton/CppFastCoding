package CppFastCodingToolWindow.MainWindow.SpecComp;

import CppFastCodingToolWindow.MyComp.MyButton;
import CppFastCodingToolWindow.MyComp.MyLabel;
import CppFastCodingToolWindow.MyComp.MyPanel;
import CppFastCodingToolWindow.MyComp.MyTextArea;
import PluginServices.MyNotice;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.JBColor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TestCase extends MyPanel {
    public static int AC = 0;
    public static int TLE = 1;
    public static int RE = 2;
    public static int RUN = -1;
    public static int PD = -2;
    public JLabel statLabel = new JLabel("Pending...");
    public MyTextArea inputField = new MyTextArea();
    public MyTextArea outputField = new MyTextArea();
    JLabel title;
    MyPanel titleRow = new MyPanel(BoxLayout.X_AXIS, JBColor.gray);
    MyButton clearTextButton = new MyButton("ClearText");
    MyButton deleteButton = new MyButton("DeleteTestCase");
    MyPanel buttonRow = new MyPanel(BoxLayout.X_AXIS, JBColor.gray);
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
        this.AddComp(new MyLabel("Input:"));
        this.AddComp(inputField);
        this.AddComp(new MyLabel("Output:"));
        this.AddComp(outputField);

        this.setBorder(new LineBorder(JBColor.black, 5, true));
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
        this.SetStat(TestCase.PD);
    }

    private void Delete(ActionEvent e) {
        MyPanel buttonR = (MyPanel) deleteButton.getParent();
        TestCase testCase = (TestCase) buttonR.getParent();
        TestCasePanel gPanel = (TestCasePanel) testCase.getParent();
        gPanel.remove(testCase);
        gPanel.revalidate();
        gPanel.repaint();
        gPanel.titleUdt();
        gPanel.testCaseNum--;
    }

    public void SetStat(int stat) {
        if (stat == TestCase.AC) {
            this.statLabel.setText("Accepted");
            this.statLabel.setForeground(JBColor.green);
        } else if (stat == TestCase.TLE) {
            this.statLabel.setText("TimeLimitExceed");
            MyNotice.ShowBalloon("INFO", "Info: TimeLimitExceed");
            this.statLabel.setForeground(JBColor.black);
        } else if (stat == TestCase.RE) {
            this.statLabel.setText("RuntimeError");
            MyNotice.ShowBalloon("INFO", "Info: RuntimeError");
            this.statLabel.setForeground(JBColor.red);
        } else if (stat == TestCase.PD) {
            this.statLabel.setText("Pending...");
            this.statLabel.setForeground(JBColor.blue);
        } else if (stat == TestCase.RUN) {
            this.statLabel.setText("Running...");
            this.statLabel.setForeground(JBColor.blue);
        }
        this.updateUI();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new JBColor(JBColor.gray, JBColor.gray));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
