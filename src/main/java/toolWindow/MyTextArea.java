package toolWindow;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MyTextArea extends JTextArea {
    int rowHeight;

    MyTextArea() {
        rowHeight = 18;
        this.setPreferredSize(new Dimension(200, rowHeight + 3));
        this.setMaximumSize(new Dimension(200, rowHeight + 3));
        MyTextArea self = this;
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String s = self.getText();
                int l = s.length();
                int res = 1;
                for (int i = 0; i < l; i++) {
                    if (s.charAt(i) == '\n') {
                        res++;
                    }
                }
                self.setPreferredSize(new Dimension(200, res * rowHeight + 3));
                self.setMaximumSize(new Dimension(200, res * rowHeight + 3));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String s = self.getText();
                int l = s.length();
                int res = 1;
                for (int i = 0; i < l; i++) {
                    if (s.charAt(i) == '\n') {
                        res++;
                    }
                }
                self.setPreferredSize(new Dimension(200, res * rowHeight + 3));
                self.setMaximumSize(new Dimension(200, res * rowHeight + 3));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }
}
