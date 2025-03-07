package window.mainWindow.mainWindowComp;

import base.MyLabel;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;
import com.intellij.util.xmlb.annotations.XCollection;
import listener.SaveTestCaseListener;
import storage.TestCaseStorage;
import window.mainWindow.mainWindowComp.testCase.TestCase;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TestCasePanel extends JPanel {
    private static TestCasePanel instance;
    private final Project project;
    private int testCaseCount;

    public TestCasePanel(Project p) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        testCaseCount = 0;
        setBorder(BorderFactory.createLineBorder(JBColor.RED, 2));
        instance = this;
        project = p;
        FileEditorManager editorManager = FileEditorManager.getInstance(project);
        FileEditor fileEditor = editorManager.getSelectedEditor();
        if (fileEditor == null) {
            return;
        }
        VirtualFile file = fileEditor.getFile();
        loadData(TestCaseStorage.getInstance().getData(file.getPath()));
    }

    public static TestCasePanel getInstance() {
        return instance;
    }

    public TestCase getTestCase(int idx) {
        return (TestCase) getComponent(idx);
    }

    public void addTextCase(boolean save) {
        TestCase testCase = new TestCase(++testCaseCount, project);
        add(testCase);
        updateUI();
        if (!save) return;
        SaveTestCaseListener publish = project.getMessageBus()
                .syncPublisher(SaveTestCaseListener.SAVE_ACTION_TOPIC);
        publish.saveAction(SaveTestCaseListener.SaveTestCaseEvent.create(this));
    }

    public void addTextCase() {
        addTextCase(true);
    }

    public void removeTextCase(int idx) {
        testCaseCount--;
        remove(idx);
        titleUpdate();
        updateUI();
        SaveTestCaseListener publish = project.getMessageBus()
                .syncPublisher(SaveTestCaseListener.SAVE_ACTION_TOPIC);
        publish.saveAction(SaveTestCaseListener.SaveTestCaseEvent.create(this));
    }

    private void titleUpdate() {
        int count = 0;
        for (Component c : getComponents()) {
            if (c instanceof TestCase t) {
                t.changeTitle(++count);
            }
        }
    }

    public void clear() {
        testCaseCount = 0;
        for (Component c : getComponents()) remove(c);
    }

    public TestCaseDataList exportData() {
        return TestCaseDataList.packageData(this);
    }

    public void loadData(TestCaseDataList dataList) {
        clear();
        if (dataList == null) {
            instance.add(new MyLabel("Please select a cpp file."));
            return;
        }
        for (int i = 0; i < dataList.getTestCaseCount(); i++) {
            addTextCase(false);
            getTestCase(i).loadData(dataList.getData().get(i));
        }
    }

    public static class TestCaseDataList {
        @XCollection
        private int testCaseCount;
        @XCollection
        private ArrayList<TestCase.TestCaseData> data;

        public TestCaseDataList() {
        }

        public static TestCaseDataList packageData(TestCasePanel panel) {
            TestCaseDataList list = new TestCaseDataList();
            list.testCaseCount = panel.testCaseCount;
            list.data = new ArrayList<>();
            for (int i = 0; i < list.testCaseCount; i++) list.data.add(panel.getTestCase(i).exportData());
            return list;
        }

        public int getTestCaseCount() {
            return testCaseCount;
        }

        public ArrayList<TestCase.TestCaseData> getData() {
            return data;
        }
    }
}
