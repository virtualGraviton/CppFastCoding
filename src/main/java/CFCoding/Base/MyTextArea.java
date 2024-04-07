package CFCoding.Base;


import CFCoding.Services.Manager.TextManager;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBTextArea;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MyTextArea extends JBTextArea {
    public int rowHeight;
    String fontType;
    int fontSize;
    MyTextArea self;
    TextManager textManager;

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
                int w = textManager.getWidth(s), h = textManager.getHeight(s);
                self.setPreferredSize(new Dimension(Math.max(w, 200), h));
                self.setMaximumSize(new Dimension(Math.max(w, 200), h));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String s = self.getText();
                int w = textManager.getWidth(s), h = textManager.getHeight(s);
                self.setPreferredSize(new Dimension(Math.max(w, 200), h));
                self.setMaximumSize(new Dimension(Math.max(w, 200), h));
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
        textManager = new TextManager(fontType, Font.PLAIN, fontSize);
    }

    private void Init() {
        this.setPreferredSize(new Dimension(200, rowHeight));
        this.setMaximumSize(new Dimension(200, rowHeight));
        this.setBackground(JBColor.lightGray);
    }
}
