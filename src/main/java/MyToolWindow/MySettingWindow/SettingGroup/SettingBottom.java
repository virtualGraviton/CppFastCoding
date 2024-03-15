package MyToolWindow.MySettingWindow.SettingGroup;

import MyToolWindow.MyComp.MyPanel;

import javax.swing.*;
import java.awt.*;

import static MyToolWindow.MySettingWindow.MySettings.properties;

public class SettingBottom extends MyPanel implements Setting {
    public SettingBottom() {
        super(BoxLayout.Y_AXIS);
        AddComp(new SettingTextArea("CompileStandard", "Compile Standard:", properties.get("CompileStandard")));
    }

    public void save() {
        for (Component comp : this.getComponents()) {
            if (comp instanceof Setting) {
                ((Setting) comp).save();
            }
        }
    }

    public void reset() {
        for (Component comp : this.getComponents()) {
            if (comp instanceof Setting) {
                ((Setting) comp).reset();
            }
        }
    }
}
