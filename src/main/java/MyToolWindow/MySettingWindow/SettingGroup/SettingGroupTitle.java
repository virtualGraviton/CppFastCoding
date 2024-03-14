package MyToolWindow.MySettingWindow.SettingGroup;

import MyToolWindow.MyComp.MyLabel;
import MyToolWindow.MyComp.MyPanel;

import javax.swing.*;
import java.awt.*;

public class SettingGroupTitle extends MyPanel {
    SettingGroupTitle(String groupName) {
        super(BoxLayout.X_AXIS);

        this.AddComp(new MyLabel(groupName));
        JSeparator separator = new JSeparator();
        this.AddComp(separator);
        this.setMaximumSize(new Dimension(2000, 17));
    }

    public void AddComp(JComponent comp) {
        comp.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        this.add(comp);
        this.add(Box.createHorizontalStrut(20));
    }
}
