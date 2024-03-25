package nilespider.app.views.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CrawlerActions extends SecondaryActions{
    public CrawlerActions(){
        super();
    }
    private void initComp(){
        initResultList();
    }


    protected void initResultList(){
        resultListMain.setModel(listModel);
        resultListMain.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && optionSelectorComboBox.getSelectedIndex() == 4 || optionSelectorComboBox.getSelectedIndex() == 6 || optionSelectorComboBox.getSelectedIndex() == 7 || optionSelectorComboBox.getSelectedIndex() == 8) { // Double click detected
                    int index = resultListMain.locationToIndex(e.getPoint());
                    if (index != -1) {
                        if (optionSelectorComboBox.getSelectedIndex() != 0) {
                            String url = urlExtractor.extractURLs(listModel.getElementAt(index));
                            downloads.addToDownloads(url, "/Users/macbook/Downloads");
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




}
