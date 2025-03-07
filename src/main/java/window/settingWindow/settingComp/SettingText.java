package window.settingWindow.settingComp;

import base.MyLabel;
import base.MyPanel;
import base.MyTextArea;
import com.intellij.ui.DocumentAdapter;
import org.jetbrains.annotations.NotNull;
import storage.SettingStorage;
import window.settingWindow.CFCodingConfigurable;
import window.settingWindow.SettingBase;
import window.settingWindow.SettingComponent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SettingText extends MyPanel implements SettingBase, SettingComponent {
    private final SettingTextArea textArea;
    private final String SettingKey;
    private String initSetting;

    public SettingText(String key, String title, String init) {
        super(BoxLayout.X_AXIS, 8);
        addComp(new MyLabel(title));
        initSetting = init;
        textArea = new SettingTextArea(initSetting);
        addComp(textArea);
        setMaximumSize(new Dimension(5000, 30));
        SettingKey = key;
    }

    public void addComp(JComponent comp) {
        add(comp);
        if (getAxis() == BoxLayout.X_AXIS) {
            add(Box.createHorizontalStrut(7));
        } else {
            comp.setAlignmentX(LEFT_ALIGNMENT);
            add(Box.createVerticalStrut(7));
        }
    }

    public void reset() {
        textArea.setText(initSetting);
    }

    public void save() {
        initSetting = textArea.getText();
        SettingStorage setting = SettingStorage.getInstance();
        setting.setKeyValue(SettingKey, initSetting);
    }

    public static class SettingTextArea extends MyTextArea {
        public SettingTextArea() {
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) e.consume();
                }
            });
            getDocument().addDocumentListener(new DocumentAdapter() {
                @Override
                protected void textChanged(@NotNull DocumentEvent e) {
                    CFCodingConfigurable.SettingModified = true;
                }
            });
        }

        public SettingTextArea(String text) {
            this();
            setText(text);
        }
    }
}
