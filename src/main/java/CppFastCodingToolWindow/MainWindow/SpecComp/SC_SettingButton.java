package CppFastCodingToolWindow.MainWindow.SpecComp;

import CppFastCodingToolWindow.MyComp.MyButton;
import com.intellij.openapi.options.ShowSettingsUtil;

public class SC_SettingButton extends MyButton {
    SC_SettingButton() {
        super("Setting");
        this.addActionListener(e -> ShowSettingsUtil.getInstance().showSettingsDialog(null, "CppFastCoding Settings"));
    }
}
