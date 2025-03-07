package listener;

import storage.TestCaseStorage;

public class SaveTestCaseListenerImpl implements SaveTestCaseListener {
    @Override
    public void saveAction(SaveTestCaseEvent event) {
        TestCaseStorage.getInstance().saveData(event.filePath(), event.testCasePanel().exportData());
    }
}