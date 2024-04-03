package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyLabel;
import CFCoding.Services.Manager.TextManager;
import com.intellij.ui.JBColor;

import java.awt.*;
import java.util.ArrayList;

public class StatLabel extends MyLabel {
    TextManager textManager;

    public StatLabel(String text) {
        super(text);
        setFont(new Font(fontType, Font.BOLD, fontSize + 2));
        textManager = new TextManager(this.getFont());
        int minWidth = 0;
        int minHeight = 0;
        for (String s : ResultStat.statString) {
            minWidth = Math.max(minWidth, textManager.getWidth(s));
            minHeight = Math.max(minHeight, textManager.getHeight(s));
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
        private static final ArrayList<String> statString = new ArrayList<>() {{
            add("Accepted");
            add("TLE");
            add("RE");
            add("Running...");
            add("Pending");
        }};
        public static int AC = 0;
        public static int TLE = 1;
        public static int RE = 2;
        public static int RUN = 3;
        public static int PD = 4;

        public static String getStatString(int stat) {
            return statString.get(stat);
        }
    }
}
