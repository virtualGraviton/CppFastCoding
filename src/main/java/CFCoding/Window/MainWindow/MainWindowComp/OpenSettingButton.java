package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyButton;
import com.intellij.openapi.options.ShowSettingsUtil;

public class OpenSettingButton extends MyButton {
    OpenSettingButton() {
        super("Setting");
        this.addActionListener(e -> ShowSettingsUtil.getInstance().showSettingsDialog(null, "CppFastCoding Settings"));
    }
}
