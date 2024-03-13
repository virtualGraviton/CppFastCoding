package MyToolWindow.MainWindow.MyComp;

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
        this.setBackground(JBColor.gray);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
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

            }

            @Override
            public void mouseExited(MouseEvent e) {

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
