package MyToolWindow.MySettingWindow;

import MyToolWindow.MyComp.MyPanel;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MySettings implements Configurable {
    MyPanel bottom = new MyPanel(BoxLayout.Y_AXIS);

    @Nullable
    @Override
    public JComponent createComponent() {
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