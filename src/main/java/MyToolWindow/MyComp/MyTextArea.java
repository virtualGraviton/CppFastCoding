package MyToolWindow.MyComp;


import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.components.JBTextArea;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MyTextArea extends JBTextArea {
    int rowHeight;
    String fontType;
    int fontSize;

    public MyTextArea() {
        SetFont();
        Init();
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
                self.setPreferredSize(new Dimension(300, res * rowHeight));
                self.setMaximumSize(new Dimension(300, res * rowHeight));
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
                self.setPreferredSize(new Dimension(300, res * rowHeight));
                self.setMaximumSize(new Dimension(300, res * rowHeight));
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
        this.setFont(new Font(fontType, Font.PLAIN, fontSize));
    }

    private void Init() {
        this.setPreferredSize(new Dimension(300, rowHeight));
        this.setMaximumSize(new Dimension(300, rowHeight));
    }
}
