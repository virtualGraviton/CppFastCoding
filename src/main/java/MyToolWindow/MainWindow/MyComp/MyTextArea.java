package MyToolWindow.MainWindow.MyComp;


import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MyTextArea extends JTextArea {
    int rowHeight;
    String fontType;
    int fontSize;

    public MyTextArea() {
        SetFont();

        this.setPreferredSize(new Dimension(200, rowHeight + 3));
        this.setFont(new Font(fontType, Font.PLAIN, fontSize));
        this.setMaximumSize(new Dimension(200, rowHeight + 3));
        MyTextArea self = this;
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String s = self.getText();
                int l = s.length();
                int res = 1;
                for (int i = 0; i < l; i++) {
                    if (s.charAt(i) == '\n') {
                        res++;
                    }
                }
                self.setPreferredSize(new Dimension(200, res * rowHeight + 3));
                self.setMaximumSize(new Dimension(200, res * rowHeight + 3));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String s = self.getText();
                int l = s.length();
                int res = 1;
                for (int i = 0; i < l; i++) {
                    if (s.charAt(i) == '\n') {
                        res++;
                    }
                }
                self.setPreferredSize(new Dimension(200, res * rowHeight + 3));
                self.setMaximumSize(new Dimension(200, res * rowHeight + 3));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void SetFont() {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        fontType = fontPreferences.getFontFamily();
        fontSize = fontPreferences.getSize(fontType);
        rowHeight = (int) (1.5 * fontSize) - 1;
    }
}
