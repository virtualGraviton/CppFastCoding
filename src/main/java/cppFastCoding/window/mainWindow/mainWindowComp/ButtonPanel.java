package cppFastCoding.window.mainWindow.mainWindowComp;

import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import cppFastCoding.base.MyButton;
import cppFastCoding.listener.RunFinishNotifier;
import cppFastCoding.listener.SaveFileNotifier;
import cppFastCoding.services.Notice;
import cppFastCoding.services.manager.CppFileManager;
import cppFastCoding.util.Icons;
import cppFastCoding.util.ObjUtil;
import cppFastCoding.util.stat.Stat;
import cppFastCoding.window.mainWindow.mainWindowComp.testCase.TestCase;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonPanel extends JPanel implements RunFinishNotifier {
    private final MyButton runButton;

    public ButtonPanel() {
        MyButton newButton = new MyButton(Icons.Add.getIcon());
        runButton = new MyButton(Icons.Run.getIcon());
        MyButton openSettingButton = new MyButton(Icons.Setting.getIcon());
        add(newButton);
        add(runButton);
        add(openSettingButton);
        newButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TestCasePanel.getInstance().addTextCase();
            }
        });

        runButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Project project = ObjUtil.getProject();
                SaveFileNotifier publish = project.getMessageBus().syncPublisher(SaveFileNotifier.SAVE_FILE_TOPIC);
                publish.beforeAction(runButton);
                if (!runButton.isEnabled()) {
                    Notice.showBalloon("WARN", "Do not click too fast.");
                    return;
                }
                setEnabled(false);
                TestCasePanel instance = TestCasePanel.getInstance();
                if (instance.getTestCaseCount() == 0) {
                    setEnabled(true);
                    Notice.showBalloon("ERROR", "No test case.");
                    return;
                }

                for (int i = 0; i < instance.getTestCaseCount(); i++) {
                    TestCase tc = instance.getTestCase(i);
                    tc.getDeleteButton().setEnabled(false);
                    tc.setOutput(null);
                    tc.setStat(Stat.PD);
                }

                CppFileManager cppFileManager = new CppFileManager();
                cppFileManager.asyncRunAll();
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

    @Override
    public void beforeAction() {

    }

    @Override
    public void afterAction() {
        runButton.setEnabled(true);
    }
}
