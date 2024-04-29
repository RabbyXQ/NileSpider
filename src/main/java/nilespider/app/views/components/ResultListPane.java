package nilespider.app.views.components;

import nilespider.app.ui.pages.Downloads;
import nilespider.app.views.components.interfaces.AtomicComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ResultListPane extends JPanel implements AtomicComponents {
    private final JScrollPane scrollPane;

    public ResultListPane() {
        scrollPane = new JScrollPane(RESULT_LIST);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        RESULT_LIST.addMouseListener(createMouseListener());
    }

    private MouseListener createMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    handleDoubleClick(e);
                }
            }
        };
    }

    private void handleDoubleClick(MouseEvent e) {
        int index = RESULT_LIST.locationToIndex(e.getPoint());
        if (index != -1 && SELECTOR_COMBO_BOX.getSelectedIndex() != 0) {
            String selectedItem = RESULT_LIST_MODEL.getElementAt(index);
            downloads.addToDownloads(selectedItem, "downloads");
        }
    }

    public void addResult(String result) {
        RESULT_LIST_MODEL.addElement(result);
    }

    public void clearResults() {
        RESULT_LIST_MODEL.clear();
    }
}
