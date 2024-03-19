package CFCodingWindow.MainToolWindow.MainWindowComp;

import CFCodingBase.MyButton;
import com.intellij.openapi.options.ShowSettingsUtil;

public class OpenSettingButton extends MyButton {
    OpenSettingButton() {
        super("Setting");
        this.addActionListener(e -> ShowSettingsUtil.getInstance().showSettingsDialog(null, "CppFastCoding Settings"));
    }
}
