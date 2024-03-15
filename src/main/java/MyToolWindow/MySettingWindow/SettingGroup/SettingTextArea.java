package MyToolWindow.MySettingWindow.SettingGroup;

import MyToolWindow.MyComp.MyLabel;
import MyToolWindow.MyComp.MyPanel;
import MyToolWindow.MyComp.MyTextArea;
import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;

public class SettingTextArea extends MyPanel {
    SettingTextArea(String name) {
        super(BoxLayout.X_AXIS);
        this.AddComp(new MyLabel(name));
        this.AddComp(new MyTextArea());
        this.setBackground(JBColor.red);
        this.setMaximumSize(new Dimension(1000, 20));
    }
}
