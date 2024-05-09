package cppFastCoding.services.manager;

import java.awt.*;
import java.awt.font.FontRenderContext;

public class TextManager {
    private final Font font;
    private final double rowHeight;

    public TextManager(Font _font) {
        font = _font;
        rowHeight = (1.5 * font.getSize()) - 1;
    }

    public double getRowHeight() {
        return rowHeight;
    }

    public double getHeight(String text) {
        int row_cnt = 1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                row_cnt++;
            }
        }
        return row_cnt * rowHeight;
    }

    public double getWidth(String text) {
        double col_width = 0;
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

    private double _getWidth(String text) {
        FontRenderContext frc = new FontRenderContext(null, false, false);
        return font.getStringBounds(text, frc).getWidth();
    }
}
