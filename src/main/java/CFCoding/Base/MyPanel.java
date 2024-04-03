package CFCoding.Base;

import javax.swing.*;

public class MyPanel extends JPanel {
    public int Axis;
    public int Gap;

    public MyPanel(int axis, int gap) {
        Axis = axis;
        this.setLayout(new BoxLayout(this, axis));
        Gap = gap;
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
