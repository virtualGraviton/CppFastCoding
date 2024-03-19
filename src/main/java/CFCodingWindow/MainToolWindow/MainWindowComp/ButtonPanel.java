package CFCodingWindow.MainToolWindow.MainWindowComp;

import CFCodingBase.MyPanel;
import com.intellij.openapi.project.Project;
import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends MyPanel {
    ButtonPanel(Project project, TestCasePanel testCasePanel) {
        super(BoxLayout.Y_AXIS);
        this.AddComp(new NewButton(testCasePanel));
        this.AddComp(new RunButton(project, testCasePanel));
        this.AddComp(new ClrButton(testCasePanel));
        this.AddComp(new OpenSettingButton());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new JBColor(JBColor.gray, JBColor.gray));
        g.fillRect(0, 0, getWidth(), getHeight());
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
