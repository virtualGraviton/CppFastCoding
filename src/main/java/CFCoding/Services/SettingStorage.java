package CFCoding.Services;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Service
@State(
        name = "CFCodingSettings",
        storages = {
                @Storage("CppFastCoding_Settings.xml")
        }
)
public final class SettingStorage implements PersistentStateComponent<SettingStorage> {
    private final Map<String, String> map = new HashMap<>();

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
        map.put(key, value);
    }

    public String getValueByKey(String key) {
        return map.get(key);
    }
}
