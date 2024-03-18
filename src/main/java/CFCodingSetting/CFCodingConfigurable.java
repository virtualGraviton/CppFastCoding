package CFCodingSetting;

import CFCodingServices.CFCodingSettings;
import CFCodingSetting.SettingGroup.SettingBottom;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CFCodingConfigurable implements Configurable {
    public static boolean SettingModified = false;
    SettingBottom bottom;

    @Nullable
    @Override
    public JComponent createComponent() {
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
        CFCodingSettings setting = new CFCodingSettings();
        setting.getState().CompileStandard = "-std=c++17";

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