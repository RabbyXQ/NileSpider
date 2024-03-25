package nilespider.app;


import nilespider.app.views.main.CrawlerActions;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main extends CrawlerActions {


    public Main() {
        super();
        initComponents();
    }

    private void initComponents() {
        crawlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crawlingMessage.setForeground(new Color(3, 119, 32));
                query = queryText.getText().toString().toLowerCase();
                queryText.setEnabled(false);
                urlBar.setEnabled(false);
                crawlBtn.setText("Reset");
                if (!listModel.isEmpty()){
                    listModel.clear();
                }
                if (loadingBarVisibility)
                {
                    loadingBar.setValue(0);
                }
                if (!loadingBarVisibility)
                {
                    loadingBarVisibility = true;
                    loadingBar.show();
                }
                if (!actionBtnVisibility)
                {
                    visualizeBtn.show();
                    pauseBtn.show();
                    stopBtn.show();
                }
                if (isCrawlingRunning)
                {
                    if (optionSelectorComboBox.getSelectedIndex() == 0)
                    {
                        crawlingThread.stop();
                    } else if (optionSelectorComboBox.getSelectedIndex() == 2) {

                        emailCrawlerThread.stop();
                    } else if (optionSelectorComboBox.getSelectedIndex() == 1) {

                        phoneNumberCrawlerController.stop();
                    } else if (optionSelectorComboBox.getSelectedIndex() == 3) {
                        geographicCrawlerController.stop();
                    } else if (optionSelectorComboBox.getSelectedIndex() == 4) {
                        imageCrawlerController.stop();
                    } else if (optionSelectorComboBox.getSelectedIndex() == 5) {
                        videoCrawlerController.stop();
                    } else if (optionSelectorComboBox.getSelectedIndex() == 6) {
                        pdfCrawlerController.stop();
                    }else if(optionSelectorComboBox.getSelectedIndex() == 7)
                    {
                        otherDocumentCrawlerController.stop();
                    }else if(optionSelectorComboBox.getSelectedIndex() == 8)
                    {
                        interestingFileCrawlerController.stop();
                    }
                    isCrawlingRunning = false;
                    dispose();
                    new Main().setVisible(true);
                }
                if (optionSelectorComboBox.getSelectedIndex() == 0)
                {
                    crawlingThread.start();
                } else if (optionSelectorComboBox.getSelectedIndex() == 1) {
                    phoneNumberCrawlerController.start();

                } else if(optionSelectorComboBox.getSelectedIndex() == 2 )
                {
                    emailCrawlerThread.start();
                } else if (optionSelectorComboBox.getSelectedIndex() == 3) {
                    geographicCrawlerController.start();
                } else if (optionSelectorComboBox.getSelectedIndex() == 4) {
                    imageCrawlerController.start();
                } else if (optionSelectorComboBox.getSelectedIndex() == 5) {
                    videoCrawlerController.start();
                } else if (optionSelectorComboBox.getSelectedIndex() == 6) {
                    pdfCrawlerController.start();
                }else if(optionSelectorComboBox.getSelectedIndex() == 7)
                {
                    otherDocumentCrawlerController.start();
                }else if(optionSelectorComboBox.getSelectedIndex() == 8)
                {
                    interestingFileCrawlerController.start();
                }
                isCrawlingRunning = true;
            }
        });


        resultListMain.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && optionSelectorComboBox.getSelectedIndex() == 4 || optionSelectorComboBox.getSelectedIndex() == 6 || optionSelectorComboBox.getSelectedIndex() == 7 || optionSelectorComboBox.getSelectedIndex() == 8) { // Double click detected
                    int index = resultListMain.locationToIndex(e.getPoint());
                    if (index != -1) {
                        String selectedItem = listModel.getElementAt(index);
                        if (optionSelectorComboBox.getSelectedIndex() != 0)
                        {
                            String url = urlExtractor.extractURLs(selectedItem);
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

        resultListMain.setModel(listModel);
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
