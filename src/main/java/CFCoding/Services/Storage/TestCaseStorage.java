package CFCoding.Services.Storage;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@State(
        name = "CFCodingSettings",
        storages = {
                @Storage("CppFastCoding_Settings.xml")
        }
)
public final class TestCaseStorage implements PersistentStateComponent<TestCaseStorage> {
    private static TestCaseStorage instance;
    public final Map<String, ArrayList<String>> data = new HashMap<>();

    public static TestCaseStorage getInstance() {
        if (instance == null) instance = ApplicationManager.getApplication().getService(TestCaseStorage.class);
        return instance;
    }

    @Override
    public @NotNull TestCaseStorage getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull TestCaseStorage state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public void saveTestCase(String key, ArrayList<String> value) {
        data.put(key, value);
    }

    public ArrayList<String> getTestCase(String key) {
        ArrayList<String> res = data.get(key);
        if (res == null) saveTestCase(key, new ArrayList<>());
        return data.get(key);
    }
}
