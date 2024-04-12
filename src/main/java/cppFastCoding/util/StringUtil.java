package cppFastCoding.util;

import java.util.ArrayList;

public class StringUtil {
    public static boolean isEqual(String s1, String s2) {
        ArrayList<String> l1 = split(s1);
        ArrayList<String> l2 = split(s2);
        if (l1.size() != l2.size()) return false;
        for (int i = 0; i < l1.size(); i++) {
            if (!l1.get(i).equals(l2.get(i))) return false;
        }
        return true;
    }

    public static ArrayList<String> split(String a) {
        ArrayList<String> r = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (char c : a.toCharArray()) {
            if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
                if (!builder.isEmpty()) r.add(builder.toString());
                builder = new StringBuilder();
                continue;
            }
            builder.append(c);
        }
        if (!builder.isEmpty()) r.add(builder.toString());
        return r;
    }
}
