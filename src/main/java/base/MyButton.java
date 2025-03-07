package base;

import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import util.RoundedBorder;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButton extends JLabel {
    public MyButton() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                setBorder(new CompoundBorder(new RoundedBorder(8, 1, JBColor.blue), JBUI.Borders.empty(2, 1)));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBorder(new CompoundBorder(new RoundedBorder(8, 1, JBColor.lightGray), JBUI.Borders.empty(2, 1)));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(new CompoundBorder(new RoundedBorder(8, 1, JBColor.lightGray), JBUI.Borders.empty(2, 1)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(JBUI.Borders.empty(3, 2));
            }
        });
        setBorder(JBUI.Borders.empty(3, 2));
        setAlignmentX(LEFT_ALIGNMENT);
    }

    public MyButton(Icon icon) {
        this();
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setIcon(icon);
        setDisabledIcon(icon);
        setPreferredSize(new Dimension(icon.getIconWidth() + 8, icon.getIconHeight() + 6));
        setMaximumSize(new Dimension(icon.getIconWidth() + 8, icon.getIconHeight() + 6));
    }
}
