package CFCoding.Services.Manager;

import java.awt.*;
import java.awt.font.FontRenderContext;

public class TextManager {
    private final Font _font;
    private int rowHeight;

    public TextManager(String fontType, int fontStyle, int fontSize) {
        _font = new Font(fontType, fontStyle, fontSize);
        rowHeight = (int) (1.5 * fontSize) - 1;
    }

    public TextManager(Font font) {
        _font = font;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public int getHeight(String text) {
        int row_cnt = 1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                row_cnt++;
            }
        }
        return row_cnt * rowHeight;
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

    private int _getWidth(String text) {
        FontRenderContext frc = new FontRenderContext(null, false, false);
        return (int) _font.getStringBounds(text, frc).getWidth();
    }
}
