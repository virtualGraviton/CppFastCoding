package cppFastCoding.base;

import cppFastCoding.services.manager.TextManager;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBTextArea;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class MyTextArea extends JBTextArea {
    private TextManager textManager;

    public MyTextArea() {
        init();
        getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                String s;
                try {
                    s = e.getDocument().getText(0, e.getDocument().getLength());
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }

                int w = textManager.getWidth(s) + 10, h = textManager.getHeight(s);
                setPreferredSize(new Dimension(Math.max(w, 200), h));
                setMaximumSize(new Dimension(Math.max(w, 200), h));
            }
        });
    }

    private void init() {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        String fontType = fontPreferences.getFontFamily();
        int fontSize = fontPreferences.getSize(fontType);
        setFont(new Font(fontType, Font.PLAIN, fontSize));
        textManager = new TextManager(getFont());
        setPreferredSize(new Dimension(200, textManager.getRowHeight()));
        setMaximumSize(new Dimension(200, textManager.getRowHeight()));
        setBackground(JBColor.lightGray);
    }
}
