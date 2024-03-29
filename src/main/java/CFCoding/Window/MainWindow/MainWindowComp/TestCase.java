package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyButton;
import CFCoding.Base.MyLabel;
import CFCoding.Base.MyPanel;
import CFCoding.Base.MyTextArea;
import CFCoding.Services.Notice;
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
    private JLabel title;
    MyButton expandButton = new MyButton("-");
    MyPanel titleRow = new MyPanel(BoxLayout.X_AXIS);
    MyButton deleteButton = new MyButton("Del");
    MyPanel buttonRow = new MyPanel(BoxLayout.X_AXIS, JBColor.gray);
    String fontType;
    int fontSize;
    public boolean isExpanded = true;

    TestCase(int testCaseNum) {
        super(BoxLayout.Y_AXIS);
        baseInit(testCaseNum);
    }

    public TestCase(int testCaseNum, String init) {
        super(BoxLayout.Y_AXIS);
        baseInit(testCaseNum);
        inputField.setText(init);
    }

    private void baseInit(int num) {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        fontType = fontPreferences.getFontFamily();
        fontSize = fontPreferences.getSize(fontType);

        title = new JLabel("TestCase #%d    ".formatted(num));
        title.setFont(new Font(fontType, Font.BOLD, fontSize + 2));
        titleRow.add(title);

        statLabel.setFont(new Font(fontType, Font.BOLD, fontSize + 2));
        titleRow.add(statLabel);

        expandButton.addActionListener(this::Expand);
        titleRow.add(expandButton);

        this.AddComp(titleRow);

        deleteButton.addActionListener(this::Delete);
        deleteButton.setForeground(JBColor.red);
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

    public void ClearText() {
        this.outputField.setText("");
        this.SetStat(PD);
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
        if (stat == AC) {
            this.statLabel.setText("Accepted");
            this.statLabel.setForeground(JBColor.green);
        } else if (stat == TLE) {
            this.statLabel.setText("TimeLimitExceed");
            Notice.ShowBalloon("INFO", "Info: TimeLimitExceed");
            this.statLabel.setForeground(JBColor.black);
        } else if (stat == RE) {
            this.statLabel.setText("RuntimeError");
            Notice.ShowBalloon("INFO", "Info: RuntimeError");
            this.statLabel.setForeground(JBColor.red);
        } else if (stat == PD) {
            this.statLabel.setText("Pending...");
            this.statLabel.setForeground(JBColor.black);
        } else if (stat == RUN) {
            this.statLabel.setText("Running...");
            this.statLabel.setForeground(JBColor.blue);
        }
        this.updateUI();
    }

    private void Expand(ActionEvent e) {
        isExpanded = !isExpanded;
        set_Visible(isExpanded);
    }

    private void set_Visible(boolean visible) {
        for (Component c : this.getComponents()) {
            if (c != titleRow) {
                c.setVisible(visible);
            }
        }
    }
}
