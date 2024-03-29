package MyToolWindow.MainWindow.SpecComp;

import javax.swing.*;

public class TestCasePanel extends JPanel {
    int testCaseNum;

    TestCasePanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        testCaseNum = 0;
    }

    public void addTextCase() {
        testCaseNum++;
        TestCase testCase = new TestCase(testCaseNum);
        this.add(testCase);
        this.revalidate();
        this.repaint();
    }

    public void titleUdt() {
        int count = this.getComponentCount();
        for (int i = 0; i < count; i++) {
            TestCase a = (TestCase) this.getComponent(i);
            a.changeTitle(i + 1);
        }
    }

    public TestCase getTestCase(int i) {
        return (TestCase) this.getComponent(i);
    }
}
