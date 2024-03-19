package CFCoding.Services;

import java.util.HashMap;
import java.util.Map;

public class InitialSetting {
    private static final Map<String, String> init = new HashMap<>();
    private static boolean isInit = false;

    private static void initialize() {
        init.put("CompileStandard", "-std=c++20");
        init.put("MaxWaitTime", "5000");
    }

    public static String get(String key) {
        if (!isInit) {
            isInit = true;
            initialize();
        }
        return init.get(key);
    }
}
