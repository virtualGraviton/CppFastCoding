package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyLabel;
import CFCoding.Services.Manager.TextManager;
import com.intellij.ui.JBColor;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StatLabel extends MyLabel {
    TextManager textManager;

    public StatLabel(String text) {
        super(text);
        setFont(new Font(getFontType(), Font.BOLD, getFontSize() + 2));
        textManager = new TextManager(this.getFont());
        int minWidth = 0;
        int minHeight = 0;
        for (Map.Entry<Integer, String> entry : ResultStat.statString.entrySet()) {
            minWidth = Math.max(minWidth, textManager.getWidth(entry.getValue()));
            minHeight = Math.max(minHeight, textManager.getHeight(entry.getValue()));
        }
        System.out.println(minWidth);
        setMinimumSize(new Dimension(minWidth, minHeight));
        setPreferredSize(new Dimension(minWidth, minHeight));
    }

    public void setStat(int stat) {
        this.setText(ResultStat.getStatString(stat));
        if (stat == ResultStat.AC) {
            this.setForeground(JBColor.green);
        } else if (stat == ResultStat.TLE) {
            this.setForeground(JBColor.black);
        } else if (stat == ResultStat.RE) {
            this.setForeground(JBColor.red);
        } else if (stat == ResultStat.PD) {
            this.setForeground(JBColor.black);
        } else if (stat == ResultStat.RUN) {
            this.setForeground(JBColor.blue);
        }
    }

    public static class ResultStat {
        private static final Map<Integer, String> statString = new HashMap<>() {
            {
                put(AC, "Accepted");
                put(TLE, "TLE");
                put(RE, "RE");
                put(RUN, "Running...");
                put(PD, "Pending");
            }
        };
        public static Integer AC = 0;
        public static Integer TLE = 1;
        public static Integer RE = 2;
        public static Integer RUN = 3;
        public static Integer PD = 4;

        public static String getStatString(int stat) {
            return statString.get(stat);
        }
    }
}
