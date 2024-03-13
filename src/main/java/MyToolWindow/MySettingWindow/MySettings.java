package MyToolWindow.MySettingWindow;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MySettings implements Configurable {

    private JPanel panel;
    private JTextField textField;

    @Nullable
    @Override
    public JComponent createComponent() {
        panel = new JPanel();
        textField = new JTextField();
        panel.add(textField);
        return panel;
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