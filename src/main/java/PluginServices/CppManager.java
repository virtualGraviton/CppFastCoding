package PluginServices;

import MyToolWindow.MainWindow.SpecComp.TestCase;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class CppManager {
    private static final Logger logger = LoggerFactory.getLogger(CppManager.class);
    public int CompileSucceed = 0;
    public int CompileFailed = 1;
    String cppFilePath;
    String exeFilePath;

    public CppManager(Project project) {
        FileEditorManager editorManager = FileEditorManager.getInstance(project);
        VirtualFile focusedFile = Objects.requireNonNull(editorManager.getSelectedEditor()).getFile();
        if (focusedFile != null && "cpp".equals(focusedFile.getExtension())) {
            cppFilePath = focusedFile.getPath();
            int len = focusedFile.getName().length();
            exeFilePath = project.getBasePath() + "/_cppCompile/" + focusedFile.getName().substring(0, len - 4) + ".exe";
        }

        File directory = new File(project.getBasePath() + "/_cppCompile");
        if (directory.mkdirs()) {
            System.out.println("Directory created successfully!");
        } else {
            System.out.println("Failed to create directory!");
        }
    }

    public int cppCompile() {
        File file = new File(exeFilePath);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                System.out.println("File deleted successfully.");
            } else {
                MyNotice.ShowBalloon(this.getClass().toString(), "Error:Failed to delete file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
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
                System.out.println("Compilation successful. Output file: " + exeFilePath);
                return CompileSucceed;
            } else {
                MyNotice.ShowBalloon(this.getClass().toString(), "Error:Compilation failed.");
                return CompileFailed;
            }
        } catch (IOException | InterruptedException exception) {
            logger.error("CompileFailed", exception);
            MyNotice.ShowBalloon(this.getClass().toString(), "Error:Compilation failed.");
            return CompileFailed;
        }
    }

    public void cppRun(TestCase nowTestCase) {
        String input = nowTestCase.inputField.getText();
        nowTestCase.SetStat(TestCase.RUN);
        try {
            Process process = new ProcessBuilder(exeFilePath).start();
            process.getOutputStream().write(input.getBytes());
            process.getOutputStream().close();

            int maxWaitTime = 5000;
            boolean timeLimitExceed = false;

            if (!process.waitFor(maxWaitTime, java.util.concurrent.TimeUnit.MILLISECONDS)) {
                process.destroy();
                timeLimitExceed = true;
            }

            StringBuilder output = new StringBuilder();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = process.getInputStream().read(buffer)) != -1) {
                output.append(new String(buffer, 0, bytesRead));
            }
            nowTestCase.outputField.setText(output.toString());
            int exitCode = process.exitValue();
//            System.out.println("Program exit code: " + exitCode);

            if (exitCode == 0) {
                nowTestCase.SetStat(TestCase.AC);
            } else if (timeLimitExceed) {
                nowTestCase.SetStat(TestCase.TLE);
            } else if (exitCode == -1073741819) {
                nowTestCase.SetStat(TestCase.RE);
            }
        } catch (Exception exception) {
            logger.error("FatalError", exception);
        }
    }
}
