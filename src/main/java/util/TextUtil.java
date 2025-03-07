package util;

import java.awt.*;

public class TextUtil {
    private final FontMetrics fontMetrics;

    public TextUtil(FontMetrics _fontMetrics) {
        fontMetrics = _fontMetrics;
    }

    public double getWidth(String text) {
        double col_width = 0;
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                col_width = Math.max(fontMetrics.stringWidth(a.toString()), col_width);
                a = new StringBuilder();
            }
            a.append(text.charAt(i));
        }
        col_width = Math.max(fontMetrics.stringWidth(a.toString()), col_width);
        return col_width;
    }

    public double getHeight(String text) {
        double col_height = 0;
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                col_height += fontMetrics.getHeight();
                a = new StringBuilder();
            }
            a.append(text.charAt(i));
        }
        col_height += fontMetrics.getHeight();
        return col_height;
    }
}
