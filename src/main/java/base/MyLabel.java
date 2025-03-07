package base;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.JBUI;

import java.awt.*;

public class MyLabel extends JBLabel {
    private final String fontType;
    private int fontSize;

    public MyLabel() {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        fontType = fontPreferences.getFontFamily();
        fontSize = fontPreferences.getSize(fontType);
        setFont(new Font(fontType, Font.PLAIN, fontSize));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setBorder(JBUI.Borders.empty(3));
    }

    public MyLabel(String text) {
        super(text);
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        fontType = fontPreferences.getFontFamily();
        fontSize = fontPreferences.getSize(fontType);
        setFont(new Font(fontType, Font.PLAIN, fontSize));
        setBorder(JBUI.Borders.empty(3));
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        setFont(new Font(fontType, Font.PLAIN, fontSize));
    }

    public String getFontType() {
        return fontType;
    }
}
