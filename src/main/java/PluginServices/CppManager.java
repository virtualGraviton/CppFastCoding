package PluginServices;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import MyToolWindow.MainWindow.SpecComp.TestCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class CppManager {
    String cppFilePath;
    String exeFilePath;
    public int CompileSucceed = 0;
    public int CompileFailed = 1;
    private static final Logger logger = LoggerFactory.getLogger(CppManager.class);
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
                System.out.println("Failed to delete file.");
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
                Messages.showMessageDialog("Compilation failed.", "Message", Messages.getInformationIcon());
                return CompileFailed;
            }
        } catch (IOException | InterruptedException exception) {
            logger.error("CompileFailed", exception);
            return CompileFailed;
        }
    }

    public void cppRun(TestCase nowTestCase) {
        String input = nowTestCase.inputField.getText();
        nowTestCase.statLabel.setText("Running...");
        nowTestCase.statLabel.setForeground(JBColor.blue);
        try {
            Process process = new ProcessBuilder(exeFilePath).start();
            process.getOutputStream().write(input.getBytes());
            process.getOutputStream().close();

            int maxWaitTime = 2000;
            boolean timeLimitExceed = false;

            if (process.waitFor(maxWaitTime, java.util.concurrent.TimeUnit.MILLISECONDS)) {
                System.out.println("External program completed successfully.");
            } else {
                System.out.println("External program execution time exceeded the maximum wait time. Forcing stop.");
                process.destroy();
                System.out.println("External program forcibly stopped.");
                timeLimitExceed = true;
            }

            StringBuilder output = new StringBuilder();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = process.getInputStream().read(buffer)) != -1) {
                output.append(new String(buffer, 0, bytesRead));
            }

            int exitCode = process.exitValue();
            System.out.println("Program exit code: " + exitCode);
            nowTestCase.outputField.setText(output.toString());
            if (exitCode == 0) {
                nowTestCase.statLabel.setText("Accepted");
                nowTestCase.statLabel.setForeground(JBColor.green);
            } else if (timeLimitExceed) {
                nowTestCase.statLabel.setText("TimeLimitExceed");
                nowTestCase.statLabel.setForeground(JBColor.black);
            } else if (exitCode == -1073741819) {
                nowTestCase.statLabel.setText("RuntimeError");
                nowTestCase.statLabel.setForeground(JBColor.red);
            }
        } catch (Exception exception) {
            logger.error("FatalError", exception);
        }
    }
}
