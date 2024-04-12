package cppFastCoding.services;

import java.util.HashMap;
import java.util.Map;

public class InitialSetting {
    private static final Map<String, String> init = new HashMap<>() {{
        put("CompileStandard", "-std=c++20");
        put("MaxWaitTime", "5000");
    }};

    public static String get(String key) {
        return init.get(key);
    }
}
