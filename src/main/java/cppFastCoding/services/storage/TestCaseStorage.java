package cppFastCoding.services.storage;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Service
@State(name = "CFCodingTestCases", storages = {@Storage("CppFastCoding_TestCase_1.0.2.xml")})
public final class TestCaseStorage implements PersistentStateComponent<TestCaseStorage> {
    private static TestCaseStorage instance;
    public Map<String, TestCaseData> data = new HashMap<>();

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

    public void saveData(String key, TestCaseData d) {
        data.put(key, d);
    }

    public TestCaseData getData(String key) {
        if (!data.containsKey(key)) data.put(key, new TestCaseData());
        return data.get(key);
    }
}
