package MyToolWindow.MySettingWindow.SettingGroup;

import MyToolWindow.MyComp.MyLabel;
import MyToolWindow.MyComp.MyPanel;
import MyToolWindow.MyComp.MySeparator;
import MyToolWindow.MyComp.MyTextArea;
import com.intellij.ui.JBColor;

import javax.swing.*;

public class MySettingGroup extends MyPanel {
    public MySettingGroup(String groupName) {
        super(BoxLayout.Y_AXIS);
        this.AddComp( new MyLabel(groupName));
        this.AddComp(new MyTextArea());
        this.setBackground(JBColor.green);
    }
}
