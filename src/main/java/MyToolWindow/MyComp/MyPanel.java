package MyToolWindow.MyComp;

import com.intellij.ui.JBColor;

import javax.swing.*;

public class MyPanel extends JPanel {
    int Axis;

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
}
