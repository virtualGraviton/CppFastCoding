package cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.buttons;

import cppFastCoding.base.MyButton;
import com.intellij.openapi.options.ShowSettingsUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OpenSettingButton extends MyButton {
    public OpenSettingButton() {
        super("Setting");
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ShowSettingsUtil.getInstance().showSettingsDialog(null, "CppFastCoding Settings");
            }
        });
    }
}
