package cppFastCoding.util;

import com.intellij.ui.JBColor;

public enum BaseStat {
    AC("Accepted", JBColor.green),
    WA("Wrong Answer", JBColor.red),
    TLE("TLE", JBColor.yellow),
    RE("RE", JBColor.yellow),
    RUN("Running...", JBColor.blue),
    PD("Pending", JBColor.black),
    CPN("Compiling", JBColor.black),
    CE("Compile Error", JBColor.red),
    CPD("Compiled", JBColor.black);

    private final String statString;
    private final JBColor statColor;

    BaseStat(String string, JBColor color) {
        statString = string;
        statColor = color;
    }

    public String getStatString() {
        return statString;
    }

    public JBColor getStatColor() {
        return statColor;
    }
}
