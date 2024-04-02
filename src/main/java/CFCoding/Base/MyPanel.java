package CFCoding.Base;

import javax.swing.*;

public class MyPanel extends JPanel {
    protected int Axis;

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
