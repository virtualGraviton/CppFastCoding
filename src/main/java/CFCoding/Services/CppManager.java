package CFCoding.Services;

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

public class CppManager {
    private static final Logger logger = LoggerFactory.getLogger(CppManager.class);
    String cppFilePath;
    String exeFilePath;
    public int CompileStat;
    TestCasePanel tot;

    public CppManager(Project project, TestCasePanel testCasePanel) {
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

    public void RunAllTest() {
        CompilePrepare();
        if (CompileStat == Stat.CompileFailed) {
            return;
        }
        AsyncCompile();
        for (Component comp : tot.getComponents()) {
            TestCase now = (TestCase) comp;
            now.SetStat(TestCase.RUN);
            AsyncRun(now);
        }
    }

    //进行编译之前的必要准备
    public void CompilePrepare() {
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
                Notice.ShowBalloon("ERROR", "Failed to delete file.");
            }
        }
    }

    private int Compile() {
        try {
            Process process = Runtime.getRuntime().exec("g++ -std=c++20 " + cppFilePath + " -o " + exeFilePath);
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

    public RunResult Run(@NotNull TestCase nowTestCase) {
        String input = nowTestCase.inputField.getText();
        StringBuilder output = new StringBuilder();
        int verdict;
        try {
            Process process = new ProcessBuilder(exeFilePath).start();
            process.getOutputStream().write(input.getBytes());
            process.getOutputStream().close();

            int maxWaitTime = 5000;

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
            Notice.ShowBalloon("ERROR", "Info:" + exception);
            logger.error("FatalError", exception);
        }
        return new RunResult(output.toString(), verdict);
    }

    public void AsyncCompile() {
        SwingWorker<Integer, Void> Async = new SwingWorker<>() {
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
                    });
                } catch (Exception e) {
                    logger.error("FatalError", e);
                }
            }
        };
        Async.execute();
    }

    public void AsyncRun(TestCase now) {
        SwingWorker<RunResult, Void> AsyncRun = new SwingWorker<>() {
            @Override
            protected RunResult doInBackground() throws Exception {
                return Run(now);
            }

            @Override
            protected void done() {
                try {
                    RunResult result = get();
                    SwingUtilities.invokeLater(() -> {
                        now.SetStat(result.verdict);
                        now.outputField.setText(result.output);
                    });
                } catch (Exception e) {
                    logger.error("FatalError", e);
                }
            }
        };
    }

    public static class Stat {
        public static int CompileSucceed = 10;
        public static int CompileFailed = 11;
        public static int UnCompiled = 12;
        public static int RunSucceed = TestCase.AC;
        public static int RunTLE = TestCase.TLE;
        public static int RunRE = TestCase.RE;
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
