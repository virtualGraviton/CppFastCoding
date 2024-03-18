package CFCodingSetting.SettingGroup;

import CFCodingServices.SettingStorage;
import CFCodingToolWindow.MyComp.MyPanel;

import javax.swing.*;
import java.awt.*;

public class SettingBottom extends MyPanel implements Setting {
    public SettingBottom() {
        super(BoxLayout.Y_AXIS);
        AddComp(new SettingTextArea("CompileStandard", "Compile Standard:", new SettingStorage().getState().CompileStandard));
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
