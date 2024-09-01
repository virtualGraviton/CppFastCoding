package cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel;

import cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.buttons.NewButton;
import cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.buttons.OpenSettingButton;
import cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.buttons.RunButton;

import javax.swing.*;

public class ButtonPanel extends JPanel {
    public ButtonPanel() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        NewButton newButton = new NewButton();
        RunButton runButton = new RunButton();
        OpenSettingButton openSettingButton = new OpenSettingButton();
        add(newButton);
        add(runButton);
        add(openSettingButton);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(newButton)
                        .addComponent(runButton)
                        .addComponent(openSettingButton)
                ));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(newButton)
                .addComponent(runButton)
                .addComponent(openSettingButton)
        );
    }
}
