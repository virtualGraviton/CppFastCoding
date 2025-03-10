package base;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    private final int Axis;
    private final int Gap;

    public MyPanel(int axis, int gap) {
        Axis = axis;
        Gap = gap;
        setLayout(new BoxLayout(this, axis));
    }

    public int getAxis() {
        return Axis;
    }

    public void addComp(JComponent comp) {
        add(comp);
        if (Axis == BoxLayout.X_AXIS) {
            add(Box.createRigidArea(new Dimension(Gap, 0)));
        } else {
            add(Box.createRigidArea(new Dimension(0, Gap)));
        }
    }
}
