package base;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBTextArea;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;
import storage.SettingStorage;
import util.RoundedBorder;
import util.TextUtil;

import javax.swing.border.CompoundBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MyTextArea extends JBTextArea {
    private final SettingStorage settingStorage = SettingStorage.getInstance();
    private int minWidth = 200;
    private TextUtil textUtil;

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

                int w = (int) (Math.ceil(textUtil.getWidth(s)) + Math.ceil(textUtil.getWidth("a"))), h = (int) textUtil.getHeight(s) + 18;
                setPreferredSize(new Dimension(Math.max(w, minWidth), h));
                setMaximumSize(new Dimension(Math.max(w, minWidth), h));
            }
        });
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setBorder(new CompoundBorder(JBUI.Borders.empty(1), new CompoundBorder(new RoundedBorder(4, 1, JBColor.blue), JBUI.Borders.empty(7, 7, 10, 7))));
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBorder(new CompoundBorder(JBUI.Borders.empty(1), new CompoundBorder(new RoundedBorder(4, 1, JBColor.LIGHT_GRAY), JBUI.Borders.empty(7, 7, 10, 7))));
            }
        });
    }

    private void init() {
        FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
        String fontType = fontPreferences.getFontFamily();
        int fontSize = fontPreferences.getSize(fontType);

        setFont(new Font(fontType, Font.PLAIN, fontSize));
        textUtil = new TextUtil(getFontMetrics(getFont()));

        minWidth = Integer.parseInt(settingStorage.getValue("DefaultDataTextAreaWidth"));
        setPreferredSize(new Dimension(minWidth, (int) textUtil.getHeight("a") + 18));
        setMaximumSize(new Dimension(minWidth, (int) textUtil.getHeight("a") + 18));
        setBorder(new CompoundBorder(JBUI.Borders.empty(1), new CompoundBorder(new RoundedBorder(4, 1, JBColor.LIGHT_GRAY), JBUI.Borders.empty(7, 7, 10, 7))));
    }

    public void setMinWidth(int width) {
        minWidth = width;
        settingStorage.setKeyValue("DefaultDataTextAreaWidth", String.valueOf(width));
        setMinimumSize(new Dimension(minWidth, (int) textUtil.getHeight("a")));
    }
}
