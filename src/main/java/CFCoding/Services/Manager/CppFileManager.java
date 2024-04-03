package CFCoding.Services.Manager;

import CFCoding.Services.Notice;
import CFCoding.Services.Storage.SettingStorage;
import CFCoding.Window.MainWindow.MainWindowComp.TestCase;
import CFCoding.Window.MainWindow.MainWindowComp.TestCasePanel;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import static CFCoding.Window.MainWindow.MainWindowComp.StatLabel.ResultStat;

public class CppFileManager {
    private static final Logger logger = LoggerFactory.getLogger(CppFileManager.class);
    String cppFilePath;
    String exeFilePath;
    TestCasePanel tot;
    private int CompileStat;

    public CppFileManager(Project project, TestCasePanel testCasePanel) {
        tot = testCasePanel;

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
            System.out.println("Failed to create directory!");
        }
        CompileStat = Stat.UnCompiled;
    }

    private void CompilePrepare() {
        if (exeFilePath == null) {
            CompileStat = Stat.CompileFailed;
            Notice.ShowBalloon("ERROR", "No file selected.");
            return;
        }
        File file = new File(exeFilePath);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                CompileStat = Stat.CompileFailed;
            }
        }
    }

    private int Compile() {
        try {
            Process process = Runtime.getRuntime().exec("g++ %s %s -o %s".formatted(SettingStorage.getInstance().getValue("CompileStandard"), cppFilePath, exeFilePath));
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return Stat.CompileSucceed;
            } else {
                return Stat.CompileFailed;
            }
        } catch (IOException | InterruptedException exception) {
            logger.error("CompileFailed", exception);
            return Stat.CompileFailed;
        }
    }

    private RunResult Run(@NotNull TestCase nowTestCase) {
        String input = nowTestCase.inputField.getText();
        StringBuilder output = new StringBuilder();
        int verdict;
        try {
            Process process = new ProcessBuilder(exeFilePath).start();
            process.getOutputStream().write(input.getBytes());
            process.getOutputStream().close();

            int maxWaitTime = Integer.parseInt(SettingStorage.getInstance().getValue("MaxWaitTime"));

            if (!process.waitFor(maxWaitTime, java.util.concurrent.TimeUnit.MILLISECONDS)) {
                process.destroy();
                verdict = Stat.RunTLE;
                return new RunResult(output.toString(), verdict);
            }

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = process.getInputStream().read(buffer)) != -1) {
                output.append(new String(buffer, 0, bytesRead));
            }

            if (process.exitValue() == 0) {
                verdict = Stat.RunSucceed;
            } else {
                verdict = Stat.RunRE;
            }
        } catch (Exception exception) {
            verdict = Stat.RunRE;
            logger.error("FatalError", exception);
        }
        return new RunResult(output.toString(), verdict);
    }

    public void AsyncRunAll() {
        CompilePrepare();
        if (CompileStat == Stat.CompileFailed) return;
        SwingWorker<Integer, Void> AsyncCompile = new SwingWorker<>() {
            @Override
            protected Integer doInBackground() {
                return Compile();
            }

            @Override
            protected void done() {
                try {
                    Integer result = get();
                    SwingUtilities.invokeLater(() -> {
                        if (result == Stat.CompileFailed) {
                            Notice.ShowBalloon("ERROR", "Compilation failed.");
                            return;
                        }
                        for (Component comp : tot.getComponents()) {
                            if (comp instanceof TestCase now) {
                                now.setStat(ResultStat.RUN);
                                AsyncRun(now);
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

    private void AsyncRun(TestCase now) {
        SwingWorker<RunResult, Void> AsyncRun = new SwingWorker<>() {
            @Override
            protected RunResult doInBackground() {
                return Run(now);
            }

            @Override
            protected void done() {
                try {
                    RunResult result = get();
                    SwingUtilities.invokeLater(() -> {
                        now.setStat(result.verdict);
                        now.outputField.setText(result.output);
                    });
                } catch (Exception e) {
                    logger.error("FatalError", e);
                }
            }
        };
        AsyncRun.execute();
    }

    public static class Stat {
        public static int CompileSucceed = 10;
        public static int CompileFailed = 11;
        public static int UnCompiled = 12;
        public static int RunSucceed = ResultStat.AC;
        public static int RunTLE = ResultStat.TLE;
        public static int RunRE = ResultStat.RE;
    }

    public static class RunResult {
        public String output;
        public int verdict;

        RunResult(String o, int v) {
            output = o;
            verdict = v;
        }
    }
}
