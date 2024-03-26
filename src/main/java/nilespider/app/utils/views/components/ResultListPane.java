package nilespider.app.utils.views.components;

import javax.swing.*;
import java.awt.*;

public class ResultListPane extends JPanel implements AtomicComponents{
    private JScrollPane scrollPane;

    public ResultListPane() {
        scrollPane = new JScrollPane(RESULT_LIST);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addResult(String result) {
        RESULT_LIST_MODEL.addElement(result);
    }

    public void clearResults() {
        RESULT_LIST_MODEL.clear();
    }

}

