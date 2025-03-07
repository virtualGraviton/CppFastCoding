package window.mainWindow.mainWindowComp.testCase;

import base.MyLabel;
import com.intellij.util.ui.JBUI;
import util.CppFileRunner;
import util.TextUtil;

import java.awt.*;

public class StatLabel extends MyLabel {
    public StatLabel(String text) {
        super(text);
        setFont(new Font(getFontType(), Font.BOLD, getFontSize() + 2));
        TextUtil textUtil = new TextUtil(getFontMetrics(getFont()));
        int minWidth = 0;
        int minHeight = 0;
        for (CppFileRunner.Stat stat : CppFileRunner.Stat.values()) {
            minWidth = Math.max(minWidth, (int) textUtil.getWidth(stat.getStatString()));
            minHeight = Math.max(minHeight, (int) textUtil.getHeight(stat.getStatString()));
        }
        setMinimumSize(new Dimension(minWidth, minHeight));
        setPreferredSize(new Dimension(minWidth, minHeight));
        setMaximumSize(new Dimension(minWidth, minHeight));
        setBorder(JBUI.Borders.empty(3));
    }

    public void setStat(CppFileRunner.Stat stat) {
        setText(stat.getStatString());
        setForeground(stat.getStatColor());
    }
}
