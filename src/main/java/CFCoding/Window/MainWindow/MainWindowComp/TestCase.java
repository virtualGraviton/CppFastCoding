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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestCase extends MyPanel {
    public MyTextArea inputField = new MyTextArea();
    public MyTextArea outputField = new MyTextArea();
    public boolean isExpanded = true;
    StatLabel statLabel = new StatLabel("Pending...");
    MyButton deleteButton = new MyButton("Del");
    MyButton expandButton = new MyButton("-");
    MyPanel titleRow = new MyPanel(BoxLayout.X_AXIS, 10);
    String fontType;
    int fontSize;
    private int idx;
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
        idx = num;
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        fontType = fontPreferences.getFontFamily();
        fontSize = fontPreferences.getSize(fontType);

        title = new JLabel("TestCase #%d".formatted(num));
        title.setFont(new Font(fontType, Font.BOLD, fontSize + 2));
        titleRow.addComp(title);

        titleRow.addComp(statLabel);

        deleteButton.setForeground(JBColor.red);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Delete();
            }
        });
        expandButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Expand(e);
            }
        });

        titleRow.addComp(deleteButton);
        titleRow.addComp(expandButton);
        this.addComp(titleRow);
        titleRow.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                MyPanel p = (MyPanel) e.getComponent();
                int w = 0, h = 0;
                if (p.getAxis() == BoxLayout.X_AXIS) w = p.getWidth();
                else h = p.getHeight();
                for (Component c : p.getComponents()) {
                    if (c instanceof Box.Filler) continue;
                    if (p.getAxis() == BoxLayout.X_AXIS) h = Math.max(h, c.getHeight());
                    else w = Math.max(w, c.getWidth());
                }
                p.setPreferredSize(new Dimension(w, h));
                p.setMaximumSize(new Dimension(w, h));
                p.revalidate();
                p.repaint();
            }
        });


        this.addComp(new MyLabel("Input:"));
        this.addComp(inputField);
        this.addComp(new MyLabel("Output:"));
        this.addComp(outputField);

        this.setBorder(new LineBorder(JBColor.lightGray, 5));
    }

    public void addComp(JComponent c) {
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(c);
        this.add(Box.createVerticalStrut(7));
    }

    public void changeTitle(int i) {
        idx = i;
        title.setText("TestCase #%d".formatted(i));
    }

    public void ClearText() {
        this.outputField.setText("");
        this.setStat(StatLabel.ResultStat.PD);
    }

    private void Delete() {
        MyPanel buttonR = (MyPanel) deleteButton.getParent();
        TestCase testCase = (TestCase) buttonR.getParent();
        TestCasePanel gPanel = (TestCasePanel) testCase.getParent();
        Box.Filler filler = (Box.Filler) gPanel.getComponent(idx * 2 - 1);
        gPanel.remove(testCase);
        gPanel.remove(filler);
        gPanel.revalidate();
        gPanel.repaint();
        gPanel.titleUdt();
        gPanel.testCaseNum--;
    }

    public void setStat(int stat) {
        statLabel.setStat(stat);
        this.updateUI();
    }

    private void Expand(MouseEvent e) {
        isExpanded = !isExpanded;
        set_Visible(isExpanded);
        MyButton b = (MyButton) e.getSource();
        b.setText(isExpanded ? "-" : "+");
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
