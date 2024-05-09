package cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel;

import cppFastCoding.base.MyPanel;
import cppFastCoding.util.Icons;
import cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.buttons.NewButton;
import cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.buttons.OpenSettingButton;
import cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.buttons.RunButton;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends MyPanel {
    private final NewButton newButton;
    private final RunButton runButton;
    private final OpenSettingButton openSettingButton;

    public ButtonPanel() {
        super(BoxLayout.Y_AXIS, 7);
        newButton = new NewButton();
        runButton = new RunButton();
        openSettingButton = new OpenSettingButton();
        addComp(newButton);
        addComp(runButton);
        addComp(openSettingButton);
        setMinimumSize(new Dimension(32, 32));
        setIcon();
    }

    public void setIcon() {
        newButton.setIcon(Icons.Add.getIcon(new Dimension(30, 30)));
        runButton.setIcon(Icons.Run.getIcon(new Dimension(30, 30)));
        openSettingButton.setIcon(Icons.Settings.getIcon(new Dimension(30, 30)));
    }

    public void addComp(JComponent comp) {
        comp.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(comp);
    }
}
