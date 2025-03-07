package util;

import base.MyButton;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;
import listener.RunFileListener;
import org.jetbrains.annotations.NotNull;
import storage.SettingStorage;
import window.mainWindow.mainWindowComp.TestCasePanel;
import window.mainWindow.mainWindowComp.testCase.TestCase;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CppFileRunner {
    private final Project project;
    private final VirtualFile file;
    private final TestCasePanel.TestCaseDataList dataList;
    private final MyButton buttonRef;
    private final String cppFilePath;
    private final String exeFilePath;
    private final int tolTaskCount;
    private int finTaskCount = 0;

    public CppFileRunner(RunFileListener.RunFileEvent event) {
        project = event.project();
        file = event.file();
        dataList = event.dataList();
        buttonRef = event.button();
        cppFilePath = file.getPath();
        exeFilePath = event.project().getBasePath() + "/cmake-build-debug/" + file.getName().substring(0, file.getName().lastIndexOf('.')) + ".exe";
        tolTaskCount = event.dataList().getTestCaseCount();
    }

    public boolean compile() {
        //prepare
        File file = new File(exeFilePath);
        if (file.exists() && !file.delete()) {
            return true;
        }
        //compile
        ArrayList<String> commands = new ArrayList<>();
        commands.add("g++");
        commands.add(SettingStorage.getInstance().getValue("CompileStandard"));
        commands.add(cppFilePath);
        commands.add("-o");
        commands.add(exeFilePath);
        try {
            Process process = new ProcessBuilder(commands).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            reader.close();

            return process.waitFor() != 0;
        } catch (IOException | InterruptedException ignored) {
        }
        return true;
    }

    private TestCase.TestCaseData runSingle(@NotNull TestCase.TestCaseData data) {
        String input = data.getInput();
        StringBuilder output = new StringBuilder();
        Stat verdict;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(exeFilePath);
            Process process = processBuilder.start();
            process.getOutputStream().write(input.getBytes());
            process.getOutputStream().close();
            long maxWaitTime = Integer.parseInt(SettingStorage.getInstance().getValue("MaxWaitTime"));
            if (!process.waitFor(maxWaitTime, TimeUnit.MILLISECONDS)) {
                process.destroy();
                data.setStat(Stat.TLE);
            }
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = process.getInputStream().read(buffer)) != -1) {
                output.append(new String(buffer, 0, bytesRead));
            }
            if (process.exitValue() == 0) {
                if (StringUtil.isEqual(output.toString(), data.getExpectedOutput())) {
                    verdict = Stat.AC;
                } else {
                    verdict = Stat.WA;
                }
            } else {
                verdict = Stat.RE;
            }
        } catch (Exception exception) {
            verdict = Stat.RE;
        }
        data.setStat(verdict);
        if (Boolean.parseBoolean(SettingStorage.getInstance().getValue("ExpandWATestCase")))
            data.setExpanded(verdict != Stat.AC);
        data.setOutput(output.toString());
        return data;
    }

    public void asyncRunSingle(@NotNull TestCase.TestCaseData data) {
        SwingWorker<TestCase.TestCaseData, Void> worker = new SwingWorker<>() {
            @Override
            protected TestCase.TestCaseData doInBackground() {
                return runSingle(data);
            }

            @Override
            protected void done() {
                try {
                    TestCase.TestCaseData data = get();
                    dataList.getData().set(data.getTestCaseRef().getIdx() - 1, data);
                    finTaskCount++;
                    if (finTaskCount == tolTaskCount) {
                        RunFileListener publish = project.getMessageBus().syncPublisher(RunFileListener.RUN_FILE_TOPIC);
                        publish.runFinishAction(new RunFileListener.RunFileEvent(project, file, dataList, buttonRef));
                    }
                } catch (Exception ignored) {
                }
            }
        };
        worker.execute();
    }

    public void asyncRunAll() {
        SwingWorker<Void, Void> swing = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                if (compile()) {
                    for (TestCase.TestCaseData data : dataList.getData()) data.setStat(Stat.CE);
                    RunFileListener publish = project.getMessageBus().syncPublisher(RunFileListener.RUN_FILE_TOPIC);
                    publish.runFinishAction(new RunFileListener.RunFileEvent(project, file, dataList, buttonRef));
                    return null;
                }
                for (int i = 0; i < dataList.getTestCaseCount(); i++) {
                    asyncRunSingle(dataList.getData().get(i));
                }
                return null;
            }
        };

        swing.execute();
    }

    public enum Stat {
        AC("Accepted", JBColor.green),
        WA("Wrong Answer", JBColor.red),
        TLE("TLE", JBColor.yellow),
        RE("RE", JBColor.yellow),
        RUN("Running...", JBColor.blue),
        PD("Pending...", JBColor.black),
        CE("Compile Error", JBColor.red);

        private final String statString;
        private final JBColor statColor;

        Stat(String string, JBColor color) {
            statString = string;
            statColor = color;
        }

        public String getStatString() {
            return statString;
        }

        public JBColor getStatColor() {
            return statColor;
        }
    }
}
