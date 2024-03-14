package MyToolWindow.MySettingWindow.SettingGroup;

import MyToolWindow.MyComp.MyPanel;
import org.jvnet.winp.Main;

import javax.swing.*;

public class MainSettingPane extends MyPanel {
    public MainSettingPane(){
        super(BoxLayout.Y_AXIS);
        this.AddComp(new MySettingGroup("Compile Settings"));
    }
}
