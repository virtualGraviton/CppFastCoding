package cppFastCoding.services.manager;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import cppFastCoding.listener.RunFinishNotifier;
import cppFastCoding.services.Notice;
import cppFastCoding.services.storage.SettingStorage;
import cppFastCoding.util.ObjUtil;
import cppFastCoding.util.StringUtil;
import cppFastCoding.util.stat.Result;
import cppFastCoding.util.stat.Stat;
import cppFastCoding.window.mainWindow.mainWindowComp.TestCasePanel;
import cppFastCoding.window.mainWindow.mainWindowComp.testCase.TestCase;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class CppFileManager {
    private static final Logger logger = LoggerFactory.getLogger(CppFileManager.class);
    private final TestCasePanel tcp;
    private final int taskCount;
    private String cppFilePath;
    private String exeFilePath;
    private int finishedTaskCount;

    public CppFileManager() {
        tcp = TestCasePanel.getInstance();
        taskCount = tcp.getTestCaseCount();
        finishedTaskCount = 0;
        Project project = ProjectManager.getInstance().getOpenProjects()[0];
        FileEditorManager editorManager = FileEditorManager.getInstance(project);
        FileEditor fileEditor = editorManager.getSelectedEditor();
        if (fileEditor == null) {
            exeFilePath = null;
            return;
        }
        VirtualFile focusedFile = fileEditor.getFile();
        if (focusedFile != null && "cpp".equals(focusedFile.getExtension())) {
            cppFilePath = focusedFile.getPath();
            int len = focusedFile.getName().length();
            exeFilePath = project.getBasePath() + "/.CppCompile/" + focusedFile.getName().substring(0, len - 4) + ".exe";
        }

        File directory = new File(project.getBasePath() + "/.CppCompile");
        if (!directory.mkdirs()) {
            System.err.println("Failed to create directory " + directory.getAbsolutePath()
                    + ",directory already exists.");
        }
    }

    private Stat compile() {
        //prepare
        if (exeFilePath == null) {
            Notice.showBalloon("ERROR", "No file selected.");
            return Stat.CE;
        }
        File file = new File(exeFilePath);
        if (file.exists() && !file.delete()) {
            return Stat.CE;
        }
        //compile
        SwingUtilities.invokeLater(() -> {
            for (Component comp : tcp.getComponents()) {
                if (comp instanceof TestCase now) now.setStat(Stat.CPN);
            }
        });
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

            int exitCode = process.waitFor();
            if (exitCode == 0)
                return Stat.CPD;
        } catch (IOException | InterruptedException ignored) {
        }
        SwingUtilities.invokeLater(() -> {
            for (Component comp : tcp.getComponents()) {
                if (comp instanceof TestCase now) now.setStat(Stat.CE);
            }
        });
        return Stat.CE;
    }

    private Result runTestCase(@NotNull TestCase nowTestCase) {
        String input = nowTestCase.getInput();
        StringBuilder output = new StringBuilder();
        Stat verdict;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(exeFilePath);
            Process process = processBuilder.start();
            process.getOutputStream().write(input.getBytes());
            process.getOutputStream().close();

            long maxWaitTime = Integer.parseInt(SettingStorage.getInstance().getValue("MaxWaitTime"));
            if (!process.waitFor(maxWaitTime, java.util.concurrent.TimeUnit.MILLISECONDS)) {
                process.destroy();
                verdict = Stat.TLE;
                return new Result(output.toString(), verdict);
            }

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = process.getInputStream().read(buffer)) != -1) {
                output.append(new String(buffer, 0, bytesRead));
            }

            if (process.exitValue() == 0) {
                if (StringUtil.isEqual(output.toString(), nowTestCase.getExpectOutput())) {
                    verdict = Stat.AC;
                } else {
                    verdict = Stat.WA;
                }
            } else {
                verdict = Stat.RE;
            }
        } catch (Exception exception) {
            verdict = Stat.RE;
            logger.error("FatalError", exception);
        }
        return new Result(output.toString(), verdict);
    }

    private void asyncRun(TestCase now) {
        SwingWorker<Result, Void> AsyncRun = new SwingWorker<>() {
            @Override
            protected Result doInBackground() {
                return runTestCase(now);
            }

            @Override
            protected void done() {
                try {
                    Result result = get();
                    SwingUtilities.invokeLater(() -> {
                        now.setStat(result.verdict());
                        now.setOutput(result.output());
                        now.getDeleteButton().setEnabled(true);
                        finishedTaskCount += 1;
                        if (finishedTaskCount == taskCount) {
                            Project project = ObjUtil.getProject();
                            RunFinishNotifier publish = project.getMessageBus()
                                    .syncPublisher(RunFinishNotifier.RUN_FINISH_TOPIC);
                            publish.afterAction();
                        }
                    });
                } catch (Exception e) {
                    logger.error("FatalError", e);
                }
            }
        };
        AsyncRun.execute();
    }

    public void asyncRunAll() {
        SwingWorker<Integer, Void> swingWorker = new SwingWorker<>() {
            @Override
            protected Integer doInBackground() {
                if (Objects.equals(compile(), Stat.CE)) {
                    Project project = ObjUtil.getProject();
                    RunFinishNotifier publish = project.getMessageBus()
                            .syncPublisher(RunFinishNotifier.RUN_FINISH_TOPIC);
                    publish.afterAction();
                    for (Component comp : tcp.getComponents()) {
                        if (comp instanceof TestCase now) {
                            now.getDeleteButton().setEnabled(true);
                        }
                    }
                    return null;
                }
                for (Component comp : tcp.getComponents()) {
                    if (comp instanceof TestCase now) {
                        SwingUtilities.invokeLater(() -> now.setStat(Stat.RUN));
                        asyncRun(now);
                    }
                }
                return null;
            }
        };
        swingWorker.execute();
    }
}
