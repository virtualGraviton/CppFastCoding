package CFCoding.Base;

import CFCoding.Services.Manager.TextManager;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JLabel {
    private final TextManager textManager;
    private String fontType;
    private int fontSize;

    public MyButton() {
        initFont();
        textManager = new TextManager(fontType, Font.PLAIN, fontSize);
        addPropertyChangeListener(evt -> {
            if (!"text".equals(evt.getPropertyName())) return;
            String s = (String) evt.getNewValue();
            int w = textManager.getWidth(s) + 20, h = textManager.getHeight(s) + 10;
            setPreferredSize(new Dimension(w, h));
            setMaximumSize(new Dimension(w, h));
        });
    }

    public MyButton(String text) {
        this();
        setText(text);
    }

    private void initFont() {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        fontType = fontPreferences.getFontFamily();
        fontSize = fontPreferences.getSize(fontType);
        setFont(new Font(fontType, Font.PLAIN, fontSize));
    }
}
