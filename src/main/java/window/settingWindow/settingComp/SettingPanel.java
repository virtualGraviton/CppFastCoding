package window.settingWindow.settingComp;

import base.MyPanel;
import window.settingWindow.SettingInit;
import window.settingWindow.SettingBase;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends MyPanel implements SettingBase {

    public SettingPanel() {
        super(BoxLayout.Y_AXIS, 7);
        for (SettingInit settingInit : SettingInit.values()) {
            if (settingInit != SettingInit.UnknownKey) {
                addComp((JComponent) settingInit.getSettingComponent());
            }
        }
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
