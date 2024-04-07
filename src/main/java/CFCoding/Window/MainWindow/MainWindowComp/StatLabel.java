package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyLabel;
import CFCoding.Services.Manager.TextManager;
import com.intellij.ui.JBColor;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StatLabel extends MyLabel {

    public StatLabel(String text) {
        super(text);
        setFont(new Font(getFontType(), Font.BOLD, getFontSize() + 2));
        TextManager textManager = new TextManager(getFont());
        int minWidth = 0;
        int minHeight = 0;
        for (Map.Entry<Integer, String> entry : ResultStat.statString.entrySet()) {
            minWidth = Math.max(minWidth, textManager.getWidth(entry.getValue()));
            minHeight = Math.max(minHeight, textManager.getHeight(entry.getValue()));
        }
        setMinimumSize(new Dimension(minWidth, minHeight));
        setPreferredSize(new Dimension(minWidth, minHeight));
    }

    public void setStat(int stat) {
        setText(ResultStat.getStatString(stat));
        if (stat == ResultStat.AC) {
            setForeground(JBColor.green);
        } else if (stat == ResultStat.TLE) {
            setForeground(JBColor.black);
        } else if (stat == ResultStat.RE) {
            setForeground(JBColor.red);
        } else if (stat == ResultStat.PD) {
            setForeground(JBColor.black);
        } else if (stat == ResultStat.RUN) {
            setForeground(JBColor.blue);
        }
    }

    public static class ResultStat {
        public static Integer AC = 0;
        public static Integer TLE = 1;
        public static Integer RE = 2;
        public static Integer RUN = 3;
        public static Integer PD = 4;
        private static final Map<Integer, String> statString = new HashMap<>() {
            {
                put(AC, "Accepted");
                put(TLE, "TLE");
                put(RE, "RE");
                put(RUN, "Running...");
                put(PD, "Pending");
            }
        };

        public static String getStatString(int stat) {
            return statString.get(stat);
        }
    }
}
