package cppFastCoding.window.mainWindow.mainWindowComp;

import cppFastCoding.base.MyPanel;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends MyPanel {
    ButtonPanel() {
        super(BoxLayout.Y_AXIS, 7);
        addComp(new NewButton());
        addComp(new RunButton());
        addComp(new OpenSettingButton());
    }

    public void addComp(JComponent comp) {
        comp.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(comp);
        add(Box.createVerticalStrut(7));
    }

    public int getW() {
        int W = 0;
        for (int i = 0; i < getComponentCount(); i++) {
            W = Math.max(W, getComponent(i).getWidth());
        }
        return W;
    }
}
