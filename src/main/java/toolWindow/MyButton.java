package toolWindow;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {

    MyButton(String name) {
        super(name);
        this.setBackground(JBColor.gray);
        this.setContentAreaFilled(false);
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        String fontType = fontPreferences.getFontFamily();
        int fontSize = fontPreferences.getSize(fontType);
        this.setFont(new Font(fontType, Font.BOLD, fontSize));
    }
}
