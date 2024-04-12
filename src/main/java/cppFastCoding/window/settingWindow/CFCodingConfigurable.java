package cppFastCoding.window.settingWindow;

import cppFastCoding.window.settingWindow.settingComp.SettingPanel;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CFCodingConfigurable implements Configurable {
    public static boolean SettingModified = false;
    SettingPanel bottom;

    @Nullable
    @Override
    public JComponent createComponent() {
        bottom = new SettingPanel();
        return bottom;
    }

    @Override
    public boolean isModified() {
        return SettingModified;
    }

    @Override
    public void apply() {
        bottom.save();
        SettingModified = false;
    }

    @Override
    public void reset() {
        bottom.reset();
        SettingModified = false;
    }

    @Nullable
    @Override
    public String getDisplayName() {
        return "CppFastCoding Settings";
    }
}