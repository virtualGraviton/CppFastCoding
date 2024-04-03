package CFCoding.Base;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.components.JBLabel;

import java.awt.*;

public class MyLabel extends JBLabel {
    public String fontType;
    public int fontSize;

    public MyLabel(String text) {
        super(text);
        SetFont();
    }

    public void SetFont() {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        fontType = fontPreferences.getFontFamily();
        fontSize = fontPreferences.getSize(fontType);
        this.setFont(new Font(fontType, Font.PLAIN, fontSize));
    }
}
