package MyToolWindow.MainWindow.MyComp;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    public MyButton(String Text) {
        super(Text);
        SetFont();
        this.setBackground(JBColor.gray);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
    }

    public void SetFont() {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        String fontType = fontPreferences.getFontFamily();
        int fontSize = fontPreferences.getSize(fontType);
        this.setFont(new Font(fontType, Font.PLAIN, fontSize));

    }
}
