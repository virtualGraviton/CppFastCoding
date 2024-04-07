package CFCoding.Window.SettingWindow.SettingComp;

import CFCoding.Base.MyLabel;
import CFCoding.Base.MyPanel;
import CFCoding.Services.Storage.SettingStorage;
import CFCoding.Window.SettingWindow.CFCodingConfigurable;
import CFCoding.Window.SettingWindow.Interface.SettingBase;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBTextArea;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SettingTextArea extends MyPanel implements SettingBase {
    private final _TextArea textArea;
    private final String SettingKey;
    private String initSetting;

    public SettingTextArea(String key, String title, String init) {
        super(BoxLayout.X_AXIS, 7);
        this.addComp(new MyLabel(title));
        initSetting = init;
        textArea = new _TextArea(init);
        textArea.setBorder(new LineBorder(JBColor.black, 3, true));
        this.addComp(textArea);
        this.setMaximumSize(new Dimension(1000, 30));

        SettingKey = key;
    }

    public void addComp(JComponent comp) {
        this.add(comp);
        if (getAxis() == BoxLayout.X_AXIS) {
            this.add(Box.createHorizontalStrut(7));
        } else {
            comp.setAlignmentX(LEFT_ALIGNMENT);
            this.add(Box.createVerticalStrut(7));
        }
    }

    public void reset() {
        textArea.setText(initSetting);
    }

    public void save() {
        initSetting = textArea.getText();
        SettingStorage setting = ApplicationManager.getApplication().getService(SettingStorage.class);
        setting.setKeyValue(SettingKey, initSetting);
    }

    public static class _TextArea extends JBTextArea {
        String fontType;
        int fontSize;

        _TextArea(String text) {
            super(text);
            SetFont();
            addListener();
            init();
        }

        private void init() {
            this.setLineWrap(true);
        }

        private void SetFont() {
            FontPreferences fontPreferences = EditorColorsManager.getInstance().getGlobalScheme().getFontPreferences();
            fontType = fontPreferences.getFontFamily();
            fontSize = fontPreferences.getSize(fontType);
            this.setFont(new Font(fontType, Font.PLAIN, fontSize));
        }

        private void addListener() {
            this.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    CFCodingConfigurable.SettingModified = true;
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    CFCodingConfigurable.SettingModified = true;
                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }
            });
            this.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        e.consume();
                    }
                }

                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }
    }
}
