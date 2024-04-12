package cppFastCoding.window.settingWindow.settingComp;

import com.intellij.openapi.application.ApplicationManager;
import cppFastCoding.base.MyPanel;
import cppFastCoding.services.storage.SettingStorage;
import cppFastCoding.window.settingWindow.SettingBase;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends MyPanel implements SettingBase {
    private static SettingStorage setting;

    public SettingPanel() {
        super(BoxLayout.Y_AXIS, 7);
        setting = ApplicationManager.getApplication().getService(SettingStorage.class);
        addSetting("CompileStandard", "Compile Standard:");
        addSetting("MaxWaitTime", "Max Wait Time(ms):");
    }

    public void addSetting(String key, String title) {
        addComp(new SettingTextArea(key, title, setting.getValue(key)));
    }

    public void save() {
        for (Component comp : getComponents()) {
            if (comp instanceof SettingBase) {
                ((SettingBase) comp).save();
            }
        }
    }

    public void reset() {
        for (Component comp : getComponents()) {
            if (comp instanceof SettingBase) {
                ((SettingBase) comp).reset();
            }
        }
    }
}
