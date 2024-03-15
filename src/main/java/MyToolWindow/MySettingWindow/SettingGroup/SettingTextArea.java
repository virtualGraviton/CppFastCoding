package MyToolWindow.MySettingWindow.SettingGroup;

import MyToolWindow.MyComp.MyLabel;
import MyToolWindow.MyComp.MyPanel;
import MyToolWindow.MyComp.MyTextArea;
import com.intellij.ui.JBColor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SettingTextArea extends MyPanel {
    public SettingTextArea(String title, String init) {
        super(BoxLayout.Y_AXIS);
        this.AddComp(new MyLabel(title));
        MyTextArea textArea = new MyTextArea(init);
        textArea.setBorder(new LineBorder(JBColor.red, 3, true));
        this.AddComp(textArea);
        this.setMaximumSize(new Dimension(1000, 60));
        this.setBorder(new LineBorder(JBColor.red, 3, true));
    }

    public SettingTextArea(String name) {
        super(BoxLayout.X_AXIS);
        this.AddComp(new MyLabel(name));
        this.AddComp(new MyTextArea());
        this.setBackground(JBColor.red);
        this.setMaximumSize(new Dimension(1000, 20));
    }

    public void AddComp(JComponent comp) {
        this.add(comp);
        if (Axis == BoxLayout.X_AXIS) {
            this.add(Box.createHorizontalStrut(7));
        } else {
            comp.setAlignmentX(LEFT_ALIGNMENT);
            this.add(Box.createVerticalStrut(7));
        }
    }
}
