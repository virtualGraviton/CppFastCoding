package CFCoding.Base;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;

public class MyButton extends JLabel {
    public int rowHeight;
    String fontType;
    int fontSize;

    public MyButton(String Text) {
        this.setBackground(JBColor.red);
        SetFont();
        SetText(Text);
    }

    public MyButton(ImageIcon icon) {
        super(icon);
        this.setBackground(JBColor.red);
        SetFont();
    }

    public void SetText(String text) {
        setText(text);
        int w = getWidth(text) + 20, h = getHeight(text) + 10;
        this.setPreferredSize(new Dimension(w, h));
        this.setMaximumSize(new Dimension(w, h));
    }

    private int getHeight(String text) {
        int row_cnt = 1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                row_cnt++;
            }
        }
        return rowHeight * row_cnt;
    }

    private int getWidth(String text) {
        int col_width = 0;
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                col_width = Math.max(_getWidth(a.toString()), col_width);
                a = new StringBuilder();
            }
            a.append(text.charAt(i));
        }
        col_width = Math.max(_getWidth(a.toString()), col_width);
        return col_width;
    }

    private int _getWidth(String text) {
        Font font = new Font(fontType, Font.PLAIN, fontSize);
        FontRenderContext frc = new FontRenderContext(null, false, false);
        return (int) font.getStringBounds(text, frc).getWidth();
    }

    private void SetFont() {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        fontType = fontPreferences.getFontFamily();
        fontSize = fontPreferences.getSize(fontType);
        rowHeight = (int) (1.5 * fontSize) - 1;
        this.setFont(new Font(fontType, Font.PLAIN, fontSize));
    }
}
