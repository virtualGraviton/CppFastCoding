package cppFastCoding.window.mainWindow.mainWindowComp.testCase;

import com.intellij.util.ui.JBUI;
import cppFastCoding.base.MyLabel;
import cppFastCoding.services.manager.TextManager;
import cppFastCoding.util.stat.Stat;

import java.awt.*;

public class StatLabel extends MyLabel {
    public StatLabel(String text) {
        super(text);
        setFont(new Font(getFontType(), Font.BOLD, getFontSize() + 2));
        TextManager textManager = new TextManager(getFontMetrics(getFont()));
        int minWidth = 0;
        int minHeight = 0;
        for (Stat stat : Stat.values()) {
            minWidth = Math.max(minWidth, (int) textManager.getWidth(stat.getStatString()));
            minHeight = Math.max(minHeight, (int) textManager.getHeight(stat.getStatString()));
        }
        setMinimumSize(new Dimension(minWidth, minHeight));
        setPreferredSize(new Dimension(minWidth, minHeight));
        setMaximumSize(new Dimension(minWidth, minHeight));
        setBorder(JBUI.Borders.empty(3));
    }

    public void setStat(Stat stat) {
        setText(stat.getStatString());
        setForeground(stat.getStatColor());
    }
}
