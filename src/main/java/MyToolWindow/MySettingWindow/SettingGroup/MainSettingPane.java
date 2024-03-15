package MyToolWindow.MySettingWindow.SettingGroup;

import MyToolWindow.MyComp.MyPanel;

import javax.swing.*;

public class MainSettingPane extends MyPanel {
    MySettingGroup compileGroup;

    public MainSettingPane() {
        super(BoxLayout.Y_AXIS);
        compileGroup = new MySettingGroup("Compile Settings");
//        this.AddComp(new SettingTextArea("Compile Standard"));
        this.AddComp(compileGroup);
    }
}
