package cppFastCoding.util;

import cppFastCoding.services.storage.SettingStorage;
import cppFastCoding.window.settingWindow.SettingComponent;
import cppFastCoding.window.settingWindow.settingComp.SettingCheck;
import cppFastCoding.window.settingWindow.settingComp.SettingText;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public enum SettingInit {
    CompileStd("CompileStandard", "-std=c++20", "Compile Standard:", SettingText.class),
    MaxWaitTime("MaxWaitTime", "5000", "Max Wait Time(ms):", SettingText.class),
    DefaultDataTextAreaWidth("DefaultDataTextAreaWidth", "200", "Default Data TextArea Width(px):", SettingText.class),
    StrictMod("StrictJudgeMod", "false", "Strict Judge Mod", SettingCheck.class),
    UnknownKey("UnknownKey", null, "Unknown Key:", null);
    private final String SettingKey;
    private final String SettingTitle;
    private final Class<? extends SettingComponent> SettingClass;
    private String SettingValue;

    SettingInit(String key, String initValue, String title, Class<? extends SettingComponent> componentClass) {
        SettingKey = key;
        SettingValue = initValue;
        SettingTitle = title;
        SettingClass = componentClass;
    }

    public static @NotNull SettingInit getSettingInit(String key) {
        for (SettingInit settingInit : values()) {
            if (settingInit.getSettingKey().equals(key)) {
                return settingInit;
            }
        }
        return UnknownKey;
    }

    public @NotNull String getSettingKey() {
        return SettingKey;
    }

    public @NotNull String getSettingValue() {
        return SettingValue;
    }

    public @NotNull SettingComponent getSettingComponent() {
        try {
            SettingValue = SettingStorage.getInstance().getValue(SettingKey);
            return SettingClass.getConstructor(String.class, String.class, String.class).newInstance(SettingKey, SettingTitle, SettingValue);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
