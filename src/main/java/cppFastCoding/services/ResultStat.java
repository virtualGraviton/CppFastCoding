package cppFastCoding.services;

import com.intellij.ui.JBColor;
import cppFastCoding.services.manager.TextManager;

import java.util.HashMap;
import java.util.Map;

public class ResultStat {
    public static Integer AC = 0;
    public static Integer TLE = 1;
    public static Integer RE = 2;
    public static Integer RUN = 3;
    public static Integer PD = 4;
    public static Integer CPN = 5;
    public static Integer CE = 6;
    public static Integer CPD = 7;
    private static final Map<Integer, String> statString = new HashMap<>() {{
        put(AC, "Accepted");
        put(TLE, "TLE");
        put(RE, "RE");
        put(RUN, "Running...");
        put(PD, "Pending");
        put(CPN, "Compiling");
        put(CE, "Compile Error");
        put(CPD, "Compiled");
    }};
    private static final Map<Integer, JBColor> statColor = new HashMap<>() {{
        put(AC, JBColor.green);
        put(TLE, JBColor.blue);
        put(RE, JBColor.red);
        put(RUN, JBColor.blue);
        put(PD, JBColor.black);
        put(CPN, JBColor.black);
        put(CE, JBColor.red);
        put(CPD, JBColor.black);
    }};

    public static JBColor getStatColor(int stat) {
        return statColor.get(stat);
    }

    public static String getStatString(int stat) {
        return statString.get(stat);
    }

    public static int getWidth(TextManager textManager) {
        int width = 0;
        for (Map.Entry<Integer, String> entry : ResultStat.statString.entrySet()) {
            width = Math.max(width, textManager.getWidth(entry.getValue()));
        }
        return width;
    }

    public static int getHeight(TextManager textManager) {
        int height = 0;
        for (Map.Entry<Integer, String> entry : ResultStat.statString.entrySet()) {
            height = Math.max(height, textManager.getHeight(entry.getValue()));
        }
        return height;
    }
}
