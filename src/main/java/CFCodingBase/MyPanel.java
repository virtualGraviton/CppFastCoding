package CFCodingBase;

import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    protected int Axis;

    public MyPanel(int axis, JBColor color) {
        Axis = axis;
        this.setLayout(new BoxLayout(this, axis));
        if (color != null) {
            this.setBackground(color);
        }
    }

    public MyPanel(int axis) {
        Axis = axis;
        this.setLayout(new BoxLayout(this, axis));
    }

    public void AddComp(JComponent comp) {
        this.add(comp);
        if (Axis == BoxLayout.X_AXIS) {
            this.add(Box.createHorizontalStrut(7));
        } else {
            this.add(Box.createVerticalStrut(7));
        }
    }

    public void resize(int pad) {
        if (Axis == BoxLayout.X_AXIS) {
            this.setPreferredSize(new Dimension(this.getWidth() + pad, this.getHeight()));
            this.setMaximumSize(new Dimension(this.getWidth() + pad, this.getHeight()));
        } else {
            this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() + pad));
            this.setMaximumSize(new Dimension(this.getWidth(), this.getHeight() + pad));
        }
        this.updateUI();
    }
}
