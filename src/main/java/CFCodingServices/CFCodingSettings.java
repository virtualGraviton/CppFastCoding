package CFCodingServices;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

@Service
@State(
        name = "CFCodingSettings",
        storages = {
                @Storage("CppFastCoding_Settings.xml")
        }
)
public final class CFCodingSettings implements PersistentStateComponent<CFCodingSettings> {

    public String CompileStandard = "-std=c++20";

    @Override
    @NotNull
    public CFCodingSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull CFCodingSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}
