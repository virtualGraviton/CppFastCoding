package CFCoding.Base;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.components.JBLabel;

import java.awt.*;

public class MyLabel extends JBLabel {
    private String fontType;
    private int fontSize;

    public MyLabel(String text) {
        super(text);
        initFont();
    }

    public void initFont() {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        fontType = fontPreferences.getFontFamily();
        fontSize = fontPreferences.getSize(fontType);
        setFont(new Font(fontType, Font.PLAIN, fontSize));
    }

    public String getFontType() {
        return fontType;
    }

    public int getFontSize() {
        return fontSize;
    }
}
