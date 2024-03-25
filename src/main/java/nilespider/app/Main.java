package nilespider.app;


import nilespider.app.views.main.CrawlerActions;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends CrawlerActions {


    public Main() {
        super();
        initComponents();
    }

    private void initComponents() {
        crawlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetQueryBox();
                initResultList();
                clearListModel();
                updateLoadingBar();
                updateButtonsVisibility();
                stopRunningCrawlerIfNeeded();
                startCrawler();
            }

            private void resetQueryBox() {
                initQueryBoxAfterClick();
            }

            private void clearListModel() {
                if (!listModel.isEmpty()) {
                    listModel.clear();
                }
            }

            private void updateLoadingBar() {
                if (loadingBarVisibility) {
                    loadingBar.setValue(0);
                }
                if (!loadingBarVisibility) {
                    loadingBarVisibility = true;
                    loadingBar.show();
                }
            }

            private void updateButtonsVisibility() {
                if (!actionBtnVisibility) {
                    visualizeBtn.show();
                    pauseBtn.show();
                    stopBtn.show();
                }
            }

            private void stopRunningCrawlerIfNeeded() {
                if (isCrawlingRunning) {
                    CRAWLER_THREADS[optionSelectorComboBox.getSelectedIndex()].stop();
                    isCrawlingRunning = false;
                    dispose();
                    new Main().setVisible(true);
                }
            }

            private void startCrawler() {
                CRAWLER_THREADS[optionSelectorComboBox.getSelectedIndex()].start();
                isCrawlingRunning = true;
            }
            
            public void initQueryBoxAfterClick(){
                crawlingMessage.setForeground(new Color(3, 119, 32));
                query = queryText.getText().toString().toLowerCase();
                queryText.setEnabled(false);
                urlBar.setEnabled(false);
                crawlBtn.setText("Reset");
            }
        });

        justifyView();
    }

    private void justifyView(){
        jScrollPane1.setViewportView(resultListMain);
        queryText.setBorder(new LineBorder(new Color(0, 153, 153), 3, true));
        jMenuBar1.add(downloadsMenuItem);
        setJMenuBar(jMenuBar1);
    }
    public static void main(String args[]) {
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }

}
