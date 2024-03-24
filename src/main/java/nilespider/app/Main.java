package nilespider.app;


import nilespider.app.ui.pages.History;
import nilespider.app.ui.pages.HistoryView;
import nilespider.app.views.main.MainActions;
import nilespider.app.views.main.SecondaryActions;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class Main extends SecondaryActions {


    public Main() {
        super();
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        loadingBar.hide();

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
        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello From Stop Button");
                if (isCrawlingRunning){
                    crawlingThread.stop();
                }
                {
                    loadingBar.setValue(0);
                    loadingBar.hide();
                    visualizeBtn.hide();
                    pauseBtn.hide();
                    stopBtn.hide();
                }
                crawlingMessage.setText("Crawling Process stoped");
                crawlingMessage.setForeground(new Color(180, 0, 0));
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
        thresholdSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int sliderValue = thresholdSlider.getValue();
                thresholdPercent.setText(sliderValue+"%");
            }
        });

        resultListMain.setModel(listModel);
        jScrollPane1.setViewportView(resultListMain);

        visualizeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                visualizeBtnActionPerformed(evt);
            }
        });

        historyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                historyBtnActionPerformed(evt);
            }
        });

        queryText.setBorder(new LineBorder(new Color(0, 153, 153), 3, true));


        pauseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

            }
        });


        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });

        downloadsMenuItem.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                downloads.getFrame().setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        jMenuBar1.add(downloadsMenuItem);

        setJMenuBar(jMenuBar1);

    }


    private void saveMenuItemActionPerformed(ActionEvent evt) {

    }

    private void optionSelectorComboBoxActionPerformed(ActionEvent evt) {
        if (optionSelectorComboBox.getSelectedIndex() > 0) {
            queryText.setEnabled(false);
        }else {
            queryText.setEnabled(true);
        }
    }

    private void visualizeBtnActionPerformed(ActionEvent evt) {

    }

    private void historyBtnActionPerformed(ActionEvent evt) {
        HistoryView historyView = new HistoryView();
        historyView.getFrame().setVisible(true);
    }


    public static void main(String args[]) {
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }

}
