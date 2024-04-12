package cppFastCoding.services.manager;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import cppFastCoding.services.Notice;
import cppFastCoding.services.Result;
import cppFastCoding.services.ResultStat;
import cppFastCoding.services.storage.SettingStorage;
import cppFastCoding.window.mainWindow.mainWindowComp.MainPanel;
import cppFastCoding.window.mainWindow.mainWindowComp.TestCase;
import cppFastCoding.window.mainWindow.mainWindowComp.TestCasePanel;
import cppFastCoding.window.mainWindow.mainWindowComp.buttonPanel.buttons.RunButton;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class CppFileManager {
    private static final Logger logger = LoggerFactory.getLogger(CppFileManager.class);
    private final TestCasePanel tot;
    private final int taskCount;
    private final RunButton runButton;
    private String cppFilePath;
    private String exeFilePath;
    private int stat;
    private int finishedTaskCount;

    public CppFileManager(RunButton run) {
        runButton = run;
        tot = MainPanel.getTestCasePanel();
        taskCount = tot.getTestCaseCount();
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
            exeFilePath = project.getBasePath() + "/_cppCompile/" + focusedFile.getName().substring(0, len - 4) + ".exe";
        }

        File directory = new File(project.getBasePath() + "/_cppCompile");
        if (!directory.mkdirs()) {
            System.out.println("Directory already existed, skipping...");
        }
        stat = ResultStat.PD;
    }

    private void compilePrepare() {
        if (exeFilePath == null) {
            stat = ResultStat.CE;
            Notice.showBalloon("ERROR", "No file selected.");
            return;
        }
        File file = new File(exeFilePath);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                stat = ResultStat.CE;
            }
        }
    }

    private Integer compile() {
        for (Component comp : tot.getComponents()) {
            if (comp instanceof TestCase now) now.setStat(ResultStat.CPN);
        }
        try {
            Process process = Runtime.getRuntime().exec("g++ %s %s -o %s".formatted(SettingStorage.getInstance().getValue("CompileStandard"), cppFilePath, exeFilePath));
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            reader.close();

            int exitCode = process.waitFor();
            if (exitCode == 0)
                return ResultStat.CPD;
        } catch (IOException | InterruptedException exception) {
            logger.error("CompileFailed", exception);
        }
        for (Component comp : tot.getComponents()) {
            if (comp instanceof TestCase now) now.setStat(ResultStat.CE);
        }
        return ResultStat.CE;
    }

    private Result runTestCase(@NotNull TestCase nowTestCase) {
        String input = nowTestCase.getInput();
        StringBuilder output = new StringBuilder();
        int verdict;
        try {
            Process process = new ProcessBuilder(exeFilePath).start();
            process.getOutputStream().write(input.getBytes());
            process.getOutputStream().close();

            int maxWaitTime = Integer.parseInt(SettingStorage.getInstance().getValue("MaxWaitTime"));

            if (!process.waitFor(maxWaitTime, java.util.concurrent.TimeUnit.MILLISECONDS)) {
                process.destroy();
                verdict = ResultStat.TLE;
                return new Result(output.toString(), verdict);
            }

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = process.getInputStream().read(buffer)) != -1) {
                output.append(new String(buffer, 0, bytesRead));
            }

            if (process.exitValue() == 0) {
                verdict = ResultStat.AC;
            } else {
                verdict = ResultStat.RE;
            }
        } catch (Exception exception) {
            verdict = ResultStat.RE;
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
                        finishedTaskCount += 1;
                        if (finishedTaskCount == taskCount) runButton.setEnabled(true);
                    });
                } catch (Exception e) {
                    logger.error("FatalError", e);
                }
            }
        };
        AsyncRun.execute();
    }

    public void asyncRunAll() {
        compilePrepare();
        if (stat == ResultStat.CE) return;
        SwingWorker<Integer, Void> AsyncCompile = new SwingWorker<>() {
            @Override
            protected Integer doInBackground() {
                return compile();
            }

            @Override
            protected void done() {
                try {
                    Integer result = get();
                    SwingUtilities.invokeLater(() -> {
                        if (Objects.equals(result, ResultStat.CE)) {
                            return;
                        }
                        for (Component comp : tot.getComponents()) {
                            if (comp instanceof TestCase now) {
                                now.setStat(ResultStat.RUN);
                                asyncRun(now);
                            }
                        }
                    });
                } catch (Exception e) {
                    logger.error("FatalError", e);
                }
            }
        };
        AsyncCompile.execute();
    }
}
