package storage;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import window.mainWindow.mainWindowComp.TestCasePanel;

import java.util.HashMap;
import java.util.Map;

@Service
@State(name = "CFCodingTestCases", storages = {@Storage("CppFastCoding_TestCase.xml")})
public final class TestCaseStorage implements PersistentStateComponent<TestCaseStorage> {
    private static TestCaseStorage instance;
    public Map<String, TestCasePanel.TestCaseDataList> data = new HashMap<>();

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

    public void saveData(String filePath, TestCasePanel.TestCaseDataList data) {
        this.data.put(filePath, data);
    }

    public TestCasePanel.TestCaseDataList getData(String filePath) {
        if (!data.containsKey(filePath)) data.put(filePath, new TestCasePanel.TestCaseDataList());
        return data.get(filePath);
    }
}
