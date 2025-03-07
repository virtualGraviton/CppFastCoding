package util;

import com.intellij.util.ui.JBUI;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedBorder implements Border {
    private final int radius;
    private final float thickness;
    private final Color color;

    public RoundedBorder(int radius, float thickness, Color color) {
        this.radius = radius;
        this.thickness = thickness * 2;
        this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.draw(new RoundRectangle2D.Float(x + thickness / 2, y + thickness / 2, width - thickness, height - thickness, radius, radius));
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return JBUI.insets((int) (thickness / 2), (int) (thickness / 2), (int) (thickness / 2), (int) (thickness / 2));
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}