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
        setForeground(ResultStat.getStatColor(stat));
    }

    public static class ResultStat {
        public static Integer AC = 0;
        public static Integer TLE = 1;
        public static Integer RE = 2;
        public static Integer RUN = 3;
        public static Integer PD = 4;
        private static final Map<Integer, String> statString = new HashMap<>() {{
            put(AC, "Accepted");
            put(TLE, "TLE");
            put(RE, "RE");
            put(RUN, "Running...");
            put(PD, "Pending");
        }};
        private static final Map<Integer, JBColor> statColor = new HashMap<>() {{
            put(AC, JBColor.green);
            put(TLE, JBColor.blue);
            put(RE, JBColor.red);
            put(RUN, JBColor.black);
            put(PD, JBColor.black);
        }};

        public static JBColor getStatColor(int stat) {
            return statColor.get(stat);
        }

        public static String getStatString(int stat) {
            return statString.get(stat);
        }
    }
}
