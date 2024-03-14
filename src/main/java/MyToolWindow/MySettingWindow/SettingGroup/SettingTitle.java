package MyToolWindow.MySettingWindow.SettingGroup;

import MyToolWindow.MyComp.MyLabel;
import MyToolWindow.MyComp.MyPanel;
import MyToolWindow.MyComp.MySeparator;

import javax.swing.*;

public class SettingTitle extends MyPanel {
    SettingTitle(String groupName) {
        super(BoxLayout.Y_AXIS);
        this.AddComp(new MyLabel(groupName));
        this.AddComp(new MySeparator());
    }

    public void AddComp(JComponent comp) {
        this.add(comp);
        this.add(Box.createVerticalStrut(10));
    }
}
