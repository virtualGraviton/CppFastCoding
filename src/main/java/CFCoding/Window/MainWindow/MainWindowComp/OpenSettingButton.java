package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyButton;
import com.intellij.openapi.options.ShowSettingsUtil;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OpenSettingButton extends MyButton {
    OpenSettingButton() {
        super("Setting");
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ShowSettingsUtil.getInstance().showSettingsDialog(null, "CppFastCoding Settings");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
