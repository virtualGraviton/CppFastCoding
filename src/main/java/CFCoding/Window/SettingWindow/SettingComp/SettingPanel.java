package CFCoding.Window.SettingWindow.SettingComp;

import CFCoding.Base.MyPanel;
import CFCoding.Services.Storage.SettingStorage;
import CFCoding.Window.SettingWindow.Interface.SettingBase;
import com.intellij.openapi.application.ApplicationManager;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends MyPanel implements SettingBase {
    private static SettingStorage setting;

    public SettingPanel() {
        super(BoxLayout.Y_AXIS);
        setting = ApplicationManager.getApplication().getService(SettingStorage.class);
        addSetting("CompileStandard", "Compile Standard:");
        addSetting("MaxWaitTime", "Max Wait Time(ms):");
    }

    public void addSetting(String key, String title) {
        AddComp(new SettingTextArea(key, title, setting.getValue(key)));
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
