package cppFastCoding.window.mainWindow.mainWindowComp.testCase;

import cppFastCoding.base.MyLabel;
import cppFastCoding.services.manager.TextManager;
import cppFastCoding.util.BaseStat;

import java.awt.*;

public class StatLabel extends MyLabel {
    public StatLabel(String text) {
        super(text);
        setFont(new Font(getFontType(), Font.BOLD, getFontSize() + 2));
        TextManager textManager = new TextManager(getFont());
        int minWidth = 0;
        int minHeight = 0;
        for (BaseStat stat : BaseStat.values()) {
            minWidth = Math.max(minWidth, textManager.getWidth(stat.getStatString()));
            minHeight = Math.max(minHeight, textManager.getHeight(stat.getStatString()));
        }
        setMinimumSize(new Dimension(minWidth, minHeight));
        setPreferredSize(new Dimension(minWidth, minHeight));
    }

    public void setStat(BaseStat stat) {
        setText(stat.getStatString());
        setForeground(stat.getStatColor());
    }
}
