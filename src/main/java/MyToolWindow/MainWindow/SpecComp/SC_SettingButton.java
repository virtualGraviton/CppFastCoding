package MyToolWindow.MainWindow.SpecComp;

import MyToolWindow.MyComp.MyButton;
import com.intellij.openapi.options.ShowSettingsUtil;

public class SC_SettingButton extends MyButton {
    SC_SettingButton() {
        super("Setting");
        this.addActionListener(e -> ShowSettingsUtil.getInstance().showSettingsDialog(null, "MySettings"));
    }
}
