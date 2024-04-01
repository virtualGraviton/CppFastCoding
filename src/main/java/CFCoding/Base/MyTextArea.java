package CFCoding.Base;


import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBTextArea;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.font.FontRenderContext;

public class MyTextArea extends JBTextArea {
    public int rowHeight;
    String fontType;
    int fontSize;
    MyTextArea self;

    public MyTextArea() {
        SetFont();
        Init();
        addListener();
    }

    protected void addListener() {
        self = this;
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String s = self.getText();
                int w = getWidth(s), h = getHeight(s);
                self.setPreferredSize(new Dimension(w, h));
                self.setMaximumSize(new Dimension(w, h));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String s = self.getText();
                int w = getWidth(s), h = getHeight(s);
                self.setPreferredSize(new Dimension(w, h));
                self.setMaximumSize(new Dimension(w, h));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    public int getHeight(String text) {
        int row_cnt = 1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                row_cnt++;
            }
        }
        return rowHeight * row_cnt;
    }

    public int getWidth(String text) {
        int col_width = 0;
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                col_width = Math.max(_getWidth(a.toString()), col_width);
                a = new StringBuilder();
            }
            a.append(text.charAt(i));
        }
        col_width = Math.max(_getWidth(a.toString()), col_width);
        col_width = Math.max(col_width + 5, 200);
        return col_width;
    }

    private int _getWidth(String text) {
        Font font = new Font(fontType, Font.PLAIN, fontSize);
        FontRenderContext frc = new FontRenderContext(null, false, false);
        return (int) font.getStringBounds(text, frc).getWidth();
    }

    private void SetFont() {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        fontType = fontPreferences.getFontFamily();
        fontSize = fontPreferences.getSize(fontType);
        rowHeight = (int) (1.5 * fontSize) - 1;
        this.setFont(new Font(fontType, Font.PLAIN, fontSize));
    }

    private void Init() {
        this.setPreferredSize(new Dimension(200, rowHeight));
        this.setMaximumSize(new Dimension(200, rowHeight));
        this.setBackground(JBColor.lightGray);
    }
}
