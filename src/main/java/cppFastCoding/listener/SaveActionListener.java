package cppFastCoding.listener;

import cppFastCoding.services.storage.TestCaseStorage;
import cppFastCoding.util.TestCaseData;

public class SaveActionListener implements SaveActionNotifier {
    @Override
    public void beforeAction() {
    }

    @Override
    public void afterAction(SaveTestCaseContext context) {
        TestCaseStorage.getInstance().saveData(context.filePath(), new TestCaseData(context.testCasePanel()));
    }
}