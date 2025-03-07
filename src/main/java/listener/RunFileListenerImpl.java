package listener;

import util.CppFileRunner;
import window.mainWindow.mainWindowComp.TestCasePanel;
import window.mainWindow.mainWindowComp.testCase.TestCase;

import javax.swing.*;

public class RunFileListenerImpl implements RunFileListener {
    @Override
    public void runStartAction(RunFileEvent event) {
        SwingUtilities.invokeLater(() -> {
            event.button().setEnabled(false);
            for (TestCase.TestCaseData data : event.dataList().getData())
                data.getTestCaseRef().getDeleteButton().setEnabled(false);
        });
        CppFileRunner runner = new CppFileRunner(event);
        SwingUtilities.invokeLater(() -> {
            for (TestCase.TestCaseData data : event.dataList().getData())
                data.getTestCaseRef().setStat(CppFileRunner.Stat.RUN);
        });
        runner.asyncRunAll();
    }

    @Override
    public void runFinishAction(RunFileEvent event) {
        SwingUtilities.invokeLater(() -> {
            event.button().setEnabled(true);
            for (TestCase.TestCaseData data : event.dataList().getData())
                data.getTestCaseRef().getDeleteButton().setEnabled(true);
            TestCasePanel.getInstance().loadData(event.dataList());
            TestCasePanel.getInstance().updateUI();
        });
    }
}
