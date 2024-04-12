package cppFastCoding.window.mainWindow.mainWindowComp;

import cppFastCoding.base.MyLabel;
import cppFastCoding.services.manager.TextManager;
import cppFastCoding.services.ResultStat;

import java.awt.*;

public class StatLabel extends MyLabel {

    public StatLabel(String text) {
        super(text);
        setFont(new Font(getFontType(), Font.BOLD, getFontSize() + 2));
        TextManager textManager = new TextManager(getFont());
        int minWidth = ResultStat.getWidth(textManager);
        int minHeight = ResultStat.getHeight(textManager);
        setMinimumSize(new Dimension(minWidth, minHeight));
        setPreferredSize(new Dimension(minWidth, minHeight));
    }

    public void setStat(int stat) {
        setText(ResultStat.getStatString(stat));
        setForeground(ResultStat.getStatColor(stat));
    }

}
