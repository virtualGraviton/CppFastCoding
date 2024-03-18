package CFCodingServices;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;

@State(
        name = "CppFastCodingSettings",
        storages = {
                @Storage("CppFastCoding-Settings.xml")
        }
)
public class SettingStorage implements PersistentStateComponent<SettingStorage.State> {
    private State SettingState = new State();

    @Override
    @NotNull
    public SettingStorage.State getState() {
        return SettingState;
    }

    @Override
    public void loadState(@NotNull State state) {
        SettingState = state;
    }

    public static class State {
        public String CompileStandard = "-std=c++20";
    }
}
