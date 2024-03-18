package CFCodingSetting.SettingGroup;

import CFCodingServices.MyNotice;
import CFCodingServices.SettingStorage;
import CFCodingSetting.CFCodingConfigurable;
import CFCodingToolWindow.MyComp.MyLabel;
import CFCodingToolWindow.MyComp.MyPanel;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.FontPreferences;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBTextArea;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SettingTextArea extends MyPanel implements Setting {
    private String initSetting;
    private _TextArea textArea;
    private String KEY;

    public SettingTextArea(String key, String title, String init) {
        super(BoxLayout.Y_AXIS);
        this.AddComp(new MyLabel(title));
        initSetting = init;
        textArea = new _TextArea(init);
        textArea.setBorder(new LineBorder(JBColor.black, 3, true));
        this.AddComp(textArea);
        this.setMaximumSize(new Dimension(1000, 60));
        KEY = key;
    }

    public SettingTextArea(String key, String name) {
        super(BoxLayout.Y_AXIS);
        this.AddComp(new MyLabel(name));
        this.AddComp(new _TextArea());
        this.setBackground(JBColor.red);
        this.setMaximumSize(new Dimension(1000, 20));
        KEY = key;
    }

    public void AddComp(JComponent comp) {
        this.add(comp);
        if (Axis == BoxLayout.X_AXIS) {
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
        new SettingStorage().getState().CompileStandard = initSetting;
    }

    public static class _TextArea extends JBTextArea {
        String fontType;
        int fontSize;
        int oldHeight = -1;

        _TextArea(String text) {
            super(text);
            SetFont();
            addListener();
            init();
        }

        _TextArea() {
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
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    _TextArea self = (_TextArea) e.getComponent();
                    SettingTextArea panel = (SettingTextArea) self.getParent();
                    if (oldHeight != -1) {
                        panel.resize(self.getHeight() - self.oldHeight);
                    }
                    oldHeight = self.getHeight();
                }
            });
            this.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        MyNotice.ShowBalloon("Waring:", "请勿输入回车.");
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