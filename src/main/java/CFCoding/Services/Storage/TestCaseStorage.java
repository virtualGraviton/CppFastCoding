package CFCoding.Services.Storage;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;

@Service
@State(
        name = "CFCodingSettings",
        storages = {
                @Storage("CppFastCoding_Settings.xml")
        }
)
public final class TestCaseStorage implements PersistentStateComponent<TestCaseStorage> {
    @Override
    public @NotNull TestCaseStorage getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull TestCaseStorage state) {

    }
}
