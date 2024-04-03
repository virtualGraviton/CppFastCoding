package CFCoding.Window.MainWindow.MainWindowComp;

import CFCoding.Base.MyPanel;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends MyPanel {
    ButtonPanel(Project project, TestCasePanel testCasePanel) {
        super(BoxLayout.Y_AXIS, 7);
        this.AddComp(new NewButton(testCasePanel));
        this.AddComp(new RunButton(project, testCasePanel));
        this.AddComp(new OpenSettingButton());
    }

    public void AddComp(JComponent comp) {
        comp.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(comp);
        this.add(Box.createVerticalStrut(7));
    }

    public int getW() {
        int W = 0;
        for (int i = 0; i < this.getComponentCount(); i++) {
            W = Math.max(W, this.getComponent(i).getWidth());
        }
        return W;
    }
}
