package window.mainWindow.mainWindowComp;

import base.MyButton;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import listener.RunFileListener;
import util.Icons;
import util.Notice;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonPanel extends JPanel {
    private static VirtualFile curFile;
    private final MyButton runButton;
    private final Project project;

    public ButtonPanel(Project p) {
        MyButton newButton = new MyButton(Icons.Add.getIcon());
        runButton = new MyButton(Icons.Run.getIcon());
        project = p;
        MyButton openSettingButton = new MyButton(Icons.Setting.getIcon());
        add(newButton);
        add(runButton);
        add(openSettingButton);

        newButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (curFile == null || !"cpp".equals(curFile.getExtension())) {
                    Notice.showBalloon("Info", "No file selected.");
                    return;
                }
                TestCasePanel.getInstance().addTextCase();
            }
        });

        runButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!runButton.isEnabled()) {
                    Notice.showBalloon("Info", "Already running.");
                    return;
                }
                if (curFile == null || !"cpp".equals(curFile.getExtension())) {
                    Notice.showBalloon("Info", "No file selected.");
                    return;
                }
                RunFileListener publish = project.getMessageBus().syncPublisher(RunFileListener.RUN_FILE_TOPIC);
                publish.runStartAction(new RunFileListener.RunFileEvent(project, curFile, TestCasePanel.getInstance().exportData(), runButton));
            }
        });
        openSettingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ShowSettingsUtil.getInstance().showSettingsDialog(null, "CppFastCoding Settings");
            }
        });

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().addComponent(newButton).addComponent(runButton).addComponent(openSettingButton)));
        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(newButton).addComponent(runButton).addComponent(openSettingButton));
    }

    public static void setCurFile(VirtualFile curFile) {
        ButtonPanel.curFile = curFile;
    }
}
