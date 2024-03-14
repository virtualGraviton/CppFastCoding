package MyToolWindow.MySettingWindow.SettingGroup;

import MyToolWindow.MyComp.MyPanel;
import com.intellij.ui.JBColor;

import javax.swing.*;

public class MySettingGroup extends MyPanel {

    public MySettingGroup(String groupName) {
        super(BoxLayout.Y_AXIS);
        this.AddComp(new SettingTitle(groupName));
    }
}
