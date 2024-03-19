package CFCodingBase;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyButton extends JButton {
    public MyButton(String Text) {
        super(Text);
        SetFont();
        this.setBackground(JBColor.white);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                MyButton b = (MyButton) e.getSource();
                b.setContentAreaFilled(true);
                b.setBackground(new JBColor(JBColor.white, JBColor.white));
                b.setFocusPainted(false);
                b.updateUI();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MyButton b = (MyButton) e.getSource();
                b.setContentAreaFilled(false);
                b.setBackground(new JBColor(JBColor.white, JBColor.white));
                b.setFocusPainted(false);
                b.updateUI();
            }
        });
    }

    public void SetFont() {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        String fontType = fontPreferences.getFontFamily();
        int fontSize = fontPreferences.getSize(fontType);
        this.setFont(new Font(fontType, Font.PLAIN, fontSize));
    }
}
