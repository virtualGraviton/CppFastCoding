package toolWindow;

import com.intellij.ui.JBColor;

import javax.swing.*;

public class CPanel extends JPanel {
    int Axis;

    CPanel(int axis) {
        Axis = axis;
        this.setLayout(new BoxLayout(this, axis));
        this.setBackground(JBColor.gray);
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
