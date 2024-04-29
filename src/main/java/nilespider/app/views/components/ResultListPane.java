package nilespider.app.views.components;

import nilespider.app.model.URLExtractor;
import nilespider.app.ui.pages.Downloads;
import nilespider.app.views.components.interfaces.AtomicComponents;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ResultListPane extends JPanel implements AtomicComponents {
    private JScrollPane scrollPane;
    Downloads downloads;
    public ResultListPane() {
        scrollPane = new JScrollPane(RESULT_LIST);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setLayout(new BorderLayout());
        downloads = new Downloads();
        add(scrollPane, BorderLayout.CENTER);
        RESULT_LIST.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double click detected
                    int index = RESULT_LIST.locationToIndex(e.getPoint());
                    if (index != -1) {
                        String selectedItem = RESULT_LIST_MODEL.getElementAt(index);
                        if (SELECTOR_COMBO_BOX.getSelectedIndex() != 0)
                        {

                            downloads.addToDownloads(selectedItem.toString(), "downloads");
                            System.out.println(selectedItem+": "+index);
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    void onClickListItem(){
        RESULT_LIST.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double click detected
                    int index = RESULT_LIST.locationToIndex(e.getPoint());
                    if (index != -1) {
                        String selectedItem = RESULT_LIST_MODEL.getElementAt(index);
                        if (SELECTOR_COMBO_BOX.getSelectedIndex() != 0)
                        {

                            downloads.addToDownloads(selectedItem.toString(), "downloads");
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }


    public void addResult(String result) {
        RESULT_LIST_MODEL.addElement(result);
    }

    public void clearResults() {
        RESULT_LIST_MODEL.clear();
    }

}

