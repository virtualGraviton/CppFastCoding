package cppFastCoding.base;

import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import cppFastCoding.util.RoundedBorder;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButton extends JLabel {
    public MyButton() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(new CompoundBorder(JBUI.Borders.empty(1), new RoundedBorder(16, 1, JBColor.black)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(JBUI.Borders.empty(2));
            }
        });
        setBorder(JBUI.Borders.empty(2));
        setAlignmentX(LEFT_ALIGNMENT);
    }

    public MyButton(Icon icon) {
        this();
        setIcon(icon);
        setDisabledIcon(icon);
    }
}
