package CFCoding.Base;

import CFCoding.Services.Manager.TextManager;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButton extends JLabel {
    private final TextManager textManager;
    private String fontType;
    private int fontSize;
    private final int borderSize = 3;
    public MyButton() {
        initFont();
        textManager = new TextManager(fontType, Font.PLAIN, fontSize);
        addPropertyChangeListener(evt -> {
            if (!"text".equals(evt.getPropertyName())) return;
            String s = (String) evt.getNewValue();
            int w = textManager.getWidth(s) + 20, h = textManager.getHeight(s)+10;
            setPreferredSize(new Dimension(w, h));
            setMaximumSize(new Dimension(w, h));
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(JBColor.black, borderSize, true));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(BorderFactory.createEmptyBorder(borderSize,borderSize,borderSize,borderSize));
            }
        });
        setBorder(BorderFactory.createEmptyBorder(borderSize,borderSize,borderSize,borderSize));
    }

    public MyButton(String text) {
        this();
        setText(text);
        setHorizontalAlignment(CENTER);
    }

    private void initFont() {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        fontType = fontPreferences.getFontFamily();
        fontSize = fontPreferences.getSize(fontType);
        setFont(new Font(fontType, Font.PLAIN, fontSize));
    }
}
