package CFCoding.Window.SettingWindow.SettingComp;

import CFCoding.Base.MyPanel;
import CFCoding.Services.CFCodingSettings;
import CFCoding.Window.SettingWindow.Interface.SettingBase;
import com.intellij.openapi.application.ApplicationManager;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends MyPanel implements SettingBase {
    public SettingPanel() {
        super(BoxLayout.Y_AXIS);
        CFCodingSettings setting = ApplicationManager.getApplication().getService(CFCodingSettings.class);
        AddComp(new SettingTextArea("Compile Standard:", setting.getState().CompileStandard));
    }

    public void save() {
        for (Component comp : this.getComponents()) {
            if (comp instanceof SettingBase) {
                ((SettingBase) comp).save();
            }
        }
    }

    public void reset() {
        for (Component comp : this.getComponents()) {
            if (comp instanceof SettingBase) {
                ((SettingBase) comp).reset();
            }
        }
    }
}
