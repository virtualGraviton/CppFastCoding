package CFCodingWindow.SettingWindow.SettingGroup;

import CFCodingServices.CFCodingSettings;
import CFCodingBase.MyPanel;
import com.intellij.openapi.application.ApplicationManager;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends MyPanel implements Setting {
    public SettingPanel() {
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
