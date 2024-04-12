package cppFastCoding.services.storage;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import cppFastCoding.util.InitialSetting;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Service
@State(name = "CFCodingSettings", storages = {@Storage("CppFastCoding_Settings.xml")})
public final class SettingStorage implements PersistentStateComponent<SettingStorage> {
    private static SettingStorage instance;
    public final Map<String, String> BasicSettings = new HashMap<>();

    public static SettingStorage getInstance() {
        if (instance == null) instance = ApplicationManager.getApplication().getService(SettingStorage.class);
        return instance;
    }

    @Override
    @NotNull
    public SettingStorage getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull SettingStorage state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public void setKeyValue(String key, String value) {
        BasicSettings.put(key, value);
    }

    public String getValue(String key) {
        String res = BasicSettings.get(key);
        if (res == null) setKeyValue(key, InitialSetting.get(key));
        return BasicSettings.get(key);
    }
}
