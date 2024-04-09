package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyButton;
import CFCoding.Base.MyLabel;
import CFCoding.Base.MyPanel;
import CFCoding.Base.MyTextArea;
import CFCoding.Services.ResultStat;
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
    private final StatLabel statLabel = new StatLabel("Pending...");
    private final MyButton deleteButton = new MyButton("Del");
    private final MyButton expandButton = new MyButton("-");
    private final MyPanel titleRow = new MyPanel(BoxLayout.X_AXIS, 10);
    private final MyTextArea inputField = new MyTextArea();
    private final MyTextArea outputField = new MyTextArea();
    private boolean isExpanded = true;
    private int idx;
    private JLabel title;

    public TestCase(int testCaseNum) {
        super(BoxLayout.Y_AXIS, 7);
        init(testCaseNum);
    }

    public TestCase(int testCaseNum, String init) {
        super(BoxLayout.Y_AXIS, 7);
        init(testCaseNum);
        inputField.setText(init);
    }

    private void init(int num) {
        idx = num;
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        String fontType = fontPreferences.getFontFamily();
        int fontSize = fontPreferences.getSize(fontType);

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
        addComp(titleRow);
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

        addComp(new MyLabel("Input:"));
        addComp(inputField);
        addComp(new MyLabel("Output:"));
        addComp(outputField);

        setBorder(new LineBorder(JBColor.lightGray, 5));
    }

    public void addComp(JComponent c) {
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(c);
        add(Box.createVerticalStrut(7));
    }

    public void changeTitle(int i) {
        idx = i;
        title.setText("TestCase #%d".formatted(i));
    }

    public void ClearText() {
        outputField.setText("");
        setStat(ResultStat.PD);
    }

    private void Delete() {
        MainPanel.getTestCasePanel().removeTextCase(idx);
    }

    public void setStat(int stat) {
        statLabel.setStat(stat);
        updateUI();
    }

    private void Expand(MouseEvent e) {
        isExpanded = !isExpanded;
        setVis(isExpanded);
        MyButton b = (MyButton) e.getSource();
        b.setText(isExpanded ? "-" : "+");
    }

    private void setVis(boolean visible) {
        boolean flag = false;
        for (Component c : getComponents()) {
            if (c != titleRow) {
                if (flag) c.setVisible(visible);
                flag = true;
            }
        }
    }

    public String getInput() {
        return inputField.getText();
    }

    public void setOutput(String output) {
        outputField.setText(output);
    }
}
