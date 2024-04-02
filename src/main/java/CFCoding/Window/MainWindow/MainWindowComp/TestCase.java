package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyButton;
import CFCoding.Base.MyLabel;
import CFCoding.Base.MyPanel;
import CFCoding.Base.MyTextArea;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.JBColor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TestCase extends MyPanel {
    public static int AC = 0;
    public static int TLE = 1;
    public static int RE = 2;
    public static int RUN = -1;
    public static int PD = -2;
    public JLabel statLabel = new JLabel("Pending...");
    public MyTextArea inputField = new MyTextArea();
    public MyTextArea outputField = new MyTextArea();
    public boolean isExpanded = true;
    MyButton deleteButton;
    MyButton expandButton = new MyButton("-");
    MyPanel titleRow = new MyPanel(BoxLayout.X_AXIS, 10);
    String fontType;
    int fontSize;
    private JLabel title;

    TestCase(int testCaseNum) {
        super(BoxLayout.Y_AXIS, 7);
        baseInit(testCaseNum);
    }

    public TestCase(int testCaseNum, String init) {
        super(BoxLayout.Y_AXIS, 7);
        baseInit(testCaseNum);
        inputField.setText(init);
    }

    private void baseInit(int num) {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        fontType = fontPreferences.getFontFamily();
        fontSize = fontPreferences.getSize(fontType);

        title = new JLabel("TestCase #%d ".formatted(num));
        title.setFont(new Font(fontType, Font.BOLD, fontSize + 2));
        titleRow.AddComp(title);

        statLabel.setFont(new Font(fontType, Font.BOLD, fontSize + 2));
        titleRow.AddComp(statLabel);

        ImageIcon icon = new ImageIcon("C:\\Users\\ASUS\\Downloads\\delete.png");
        deleteButton = new MyButton(icon);
        deleteButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Delete();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        expandButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Expand(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        titleRow.AddComp(deleteButton);
        titleRow.AddComp(expandButton);
        this.AddComp(titleRow);


        this.AddComp(new MyLabel("Input:"));
        this.AddComp(inputField);
        this.AddComp(new MyLabel("Output:"));
        this.AddComp(outputField);

        this.setBorder(new LineBorder(JBColor.lightGray, 5));
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

    private void Delete() {
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
            this.statLabel.setText("AC");
            this.statLabel.setForeground(JBColor.green);
        } else if (stat == TLE) {
            this.statLabel.setText("TLE");
            this.statLabel.setForeground(JBColor.black);
        } else if (stat == RE) {
            this.statLabel.setText("RE");
            this.statLabel.setForeground(JBColor.red);
        } else if (stat == PD) {
            this.statLabel.setText("Pending");
            this.statLabel.setForeground(JBColor.black);
        } else if (stat == RUN) {
            this.statLabel.setText("Running...");
            this.statLabel.setForeground(JBColor.blue);
        }
        this.updateUI();
    }

    private void Expand(MouseEvent e) {
        isExpanded = !isExpanded;
        set_Visible(isExpanded);
        MyButton b = (MyButton) e.getSource();
        b.SetText(isExpanded ? "-" : "+");
    }

    private void set_Visible(boolean visible) {
        boolean flag = false;
        for (Component c : this.getComponents()) {
            if (c != titleRow) {
                if (flag) c.setVisible(visible);
                flag = true;
            }
        }
    }
}
