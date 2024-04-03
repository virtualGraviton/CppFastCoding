package CFCoding.Services.Manager;

import java.awt.*;
import java.awt.font.FontRenderContext;

public class TextManager {
    private final Font _font;

    public TextManager(String fontType, int fontStyle, int fontSize) {
        _font = new Font(fontType, fontStyle, fontSize);
    }

    public TextManager(Font font) {
        _font = font;
    }

    public int getHeight(String text) {
        int col_height = 0;
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                col_height = Math.max(_getHeight(a.toString()), col_height);
                a = new StringBuilder();
            }
            a.append(text.charAt(i));
        }
        col_height = Math.max(_getHeight(a.toString()), col_height);
        return col_height;
    }

    public int getWidth(String text) {
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

    private int _getHeight(String text) {
        FontRenderContext frc = new FontRenderContext(null, false, false);
        return (int) _font.getStringBounds(text, frc).getHeight();
    }

    private int _getWidth(String text) {
        FontRenderContext frc = new FontRenderContext(null, false, false);
        return (int) _font.getStringBounds(text, frc).getWidth();
    }
}
