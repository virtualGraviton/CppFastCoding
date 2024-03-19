package CFCodingSetting.SettingGroup;

import CFCodingServices.CFCodingSettings;
import CFCodingToolWindow.MyComp.MyPanel;
import com.intellij.openapi.application.ApplicationManager;

import javax.swing.*;
import java.awt.*;

public class SettingBottom extends MyPanel implements Setting {
    public SettingBottom() {
        super(BoxLayout.Y_AXIS);
        CFCodingSettings setting = ApplicationManager.getApplication().getService(CFCodingSettings.class);
        AddComp(new SettingTextArea("Compile Standard:", setting.getState().CompileStandard));
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
