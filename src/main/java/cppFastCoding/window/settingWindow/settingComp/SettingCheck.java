package cppFastCoding.window.settingWindow.settingComp;

import com.intellij.ui.components.JBCheckBox;
import cppFastCoding.base.MyPanel;
import cppFastCoding.services.storage.SettingStorage;
import cppFastCoding.window.settingWindow.CFCodingConfigurable;
import cppFastCoding.window.settingWindow.SettingBase;
import cppFastCoding.window.settingWindow.SettingComponent;

import javax.swing.*;
import java.awt.*;

public class SettingCheck extends MyPanel implements SettingBase, SettingComponent {
    private final SettingCheckBox checkBox;
    private final String SettingKey;
    private Boolean initSetting;

    public SettingCheck(String key, String title, String init) {
        super(BoxLayout.X_AXIS, 7);
        SettingKey = key;
        initSetting = Boolean.parseBoolean(init);
        checkBox = new SettingCheckBox(title, initSetting);
        setMaximumSize(new Dimension(5000, 30));
        addComp(checkBox);
    }

    public void addComp(JComponent comp) {
        add(comp);
        if (getAxis() == BoxLayout.X_AXIS) {
            add(Box.createHorizontalStrut(7));
        } else {
            comp.setAlignmentX(LEFT_ALIGNMENT);
            add(Box.createVerticalStrut(7));
        }
    }

    @Override
    public void reset() {
        checkBox.setSelected(initSetting);
    }

    @Override
    public void save() {
        initSetting = checkBox.isSelected();
        SettingStorage setting = SettingStorage.getInstance();
        setting.setKeyValue(SettingKey, initSetting.toString());
    }

    public static class SettingCheckBox extends JBCheckBox {
        public SettingCheckBox(String text, boolean selected) {
            super(text, selected);
            addActionListener(e -> CFCodingConfigurable.SettingModified = true);
        }
    }
}
