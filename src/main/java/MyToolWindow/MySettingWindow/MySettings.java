package MyToolWindow.MySettingWindow;

import MyToolWindow.MySettingWindow.SettingGroup.SettingBottom;
import PluginServices.MyProperty.PropertyManager;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MySettings implements Configurable {
    static public PropertyManager properties;
    public static boolean SettingModified = false;
    SettingBottom bottom;

    @Nullable
    @Override
    public JComponent createComponent() {
        properties = new PropertyManager();
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