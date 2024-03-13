package MyToolWindow.MyComp.MyComp;

import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    int Axis;

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new JBColor(JBColor.gray, JBColor.gray));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
