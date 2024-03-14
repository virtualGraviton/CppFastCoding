package MyToolWindow.MySettingWindow;

import MyToolWindow.MySettingWindow.SettingGroup.MainSettingPane;
import PluginServices.MyProperty.PropertyManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.ui.components.JBScrollPane;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MySettings implements Configurable {
    PropertyManager properties;
    JBScrollPane bottom;

    @Nullable
    @Override
    public JComponent createComponent() {
        properties = new PropertyManager();
        bottom = new JBScrollPane(new MainSettingPane());
        bottom.setBorder(null);
        return bottom;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() {
    }

    @Override
    public void reset() {
    }

    @Override
    public void disposeUIResources() {
    }

    @Nullable
    @Override
    public String getDisplayName() {
        return "My Settings";
    }
}