package cppFastCoding.base;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBTextArea;
import cppFastCoding.services.manager.TextManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class MyTextArea extends JBTextArea {
    private int minWidth = 200;
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

                int w = (int) textManager.getWidth(s) + 10, h = (int) textManager.getHeight(s);
                setPreferredSize(new Dimension(Math.max(w, minWidth), h));
                setMaximumSize(new Dimension(Math.max(w, minWidth), h));
            }
        });
    }

    private void init() {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        String fontType = fontPreferences.getFontFamily();
        int fontSize = fontPreferences.getSize(fontType);

        setFont(new Font(fontType, Font.PLAIN, fontSize));
        textManager = new TextManager(getFont());
        setPreferredSize(new Dimension(minWidth, (int) textManager.getRowHeight()));
        setMaximumSize(new Dimension(minWidth, (int) textManager.getRowHeight()));
        setBackground(JBColor.lightGray);
    }

    public void setMinWidth(int width) {
        minWidth = width;
        setMinimumSize(new Dimension(minWidth, (int) textManager.getRowHeight()));
    }
}
