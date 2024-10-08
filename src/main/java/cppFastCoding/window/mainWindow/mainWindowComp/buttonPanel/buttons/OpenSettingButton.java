package cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.buttons;

import com.intellij.openapi.options.ShowSettingsUtil;
import cppFastCoding.base.MyButton;
import cppFastCoding.util.Icons;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OpenSettingButton extends MyButton {
    public OpenSettingButton() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ShowSettingsUtil.getInstance().showSettingsDialog(null, "CppFastCoding Settings");
            }
        });
        setIcon(Icons.Setting.getIcon());
    }
}
