package cppFastCoding.base;

import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButton extends JLabel {
    private final int borderSize = 2;

    public MyButton() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(JBColor.black, borderSize, true));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
            }
        });
        setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
    }
}
