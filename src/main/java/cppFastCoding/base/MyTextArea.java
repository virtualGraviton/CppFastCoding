package cppFastCoding.base;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBTextArea;
import cppFastCoding.services.manager.TextManager;
import cppFastCoding.services.storage.SettingStorage;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class MyTextArea extends JBTextArea {
    private final SettingStorage settingStorage = SettingStorage.getInstance();
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

                int w = (int) (Math.ceil(textManager.getWidth(s)) + Math.ceil(textManager.getWidth("a"))),
                        h = (int) textManager.getHeight(s);
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
        textManager = new TextManager(getFontMetrics(getFont()));

        minWidth = Integer.parseInt(settingStorage.getValue("DefaultDataTextAreaWidth"));
        setPreferredSize(new Dimension(minWidth, (int) textManager.getHeight("a")));
        setMaximumSize(new Dimension(minWidth, (int) textManager.getHeight("a")));
        setBackground(JBColor.lightGray);
    }

    public void setMinWidth(int width) {
        minWidth = width;
        settingStorage.setKeyValue("DefaultDataTextAreaWidth", String.valueOf(width));
        setMinimumSize(new Dimension(minWidth, (int) textManager.getHeight("a")));
    }
}
