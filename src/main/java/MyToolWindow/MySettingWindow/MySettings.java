package MyToolWindow.MySettingWindow;

import MyToolWindow.MyComp.MyPanel;
import MyToolWindow.MySettingWindow.SettingGroup.SettingTextArea;
import PluginServices.MyProperty.PropertyManager;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MySettings implements Configurable {
    PropertyManager properties;
    MyPanel bottom;
    public static boolean SettingModified = false;

    @Nullable
    @Override
    public JComponent createComponent() {
        properties = new PropertyManager();
        bottom = new MyPanel(BoxLayout.Y_AXIS);
        bottom.AddComp(new SettingTextArea("Compile Standard:", properties.get("CompileStandard")));
        return bottom;
    }

    @Override
    public boolean isModified() {
        return SettingModified;
    }

    @Override
    public void apply() {
        properties.save();
    }

    @Nullable
    @Override
    public String getDisplayName() {
        return "My Settings";
    }
}