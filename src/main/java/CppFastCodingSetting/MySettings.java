package CppFastCodingSetting;

import CppFastCodingSetting.SettingGroup.SettingBottom;
import CppFastCodingServices.SettingStorage;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MySettings implements Configurable {
    static public SettingStorage properties;
    public static boolean SettingModified = false;
    SettingBottom bottom;

    @Nullable
    @Override
    public JComponent createComponent() {
        properties = new SettingStorage();
        bottom = new SettingBottom();
        return bottom;
    }

    @Override
    public boolean isModified() {
        return SettingModified;
    }

    @Override
    public void apply() {
        bottom.save();
        properties.save();
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
        return "My Settings";
    }
}