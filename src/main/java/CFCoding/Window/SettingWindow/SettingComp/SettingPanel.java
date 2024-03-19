package CFCoding.Window.SettingWindow.SettingComp;

import CFCoding.Base.MyPanel;
import CFCoding.Services.SettingStorage;
import CFCoding.Window.SettingWindow.Interface.SettingBase;
import com.intellij.openapi.application.ApplicationManager;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends MyPanel implements SettingBase {
    public SettingPanel() {
        super(BoxLayout.Y_AXIS);
        SettingStorage setting = ApplicationManager.getApplication().getService(SettingStorage.class);
        AddComp(new SettingTextArea("CompileStandard", "Compile Standard:", setting.getValueByKey("CompileStandard")));
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
