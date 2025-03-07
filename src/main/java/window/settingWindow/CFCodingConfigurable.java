package window.settingWindow;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nullable;
import window.settingWindow.settingComp.SettingPanel;

import javax.swing.*;

public class CFCodingConfigurable implements Configurable {
    public static boolean SettingModified = false;
    private SettingPanel bottom;

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