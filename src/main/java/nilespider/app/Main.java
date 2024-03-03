/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nilespider.app;

import nilespider.app.utils.controllers.BasicCrawlerController;
import nilespider.app.utils.controllers.EmailCrawlerController;
import nilespider.app.utils.models.BasicCrawler;
import nilespider.app.utils.models.EmailCrawler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


/**
 *
 * @author macbook
 */
public class Main extends javax.swing.JFrame {
    public static String query;
    public static DefaultListModel<String> listModel;
    private Set<String> visitedUrls;
    private String searchString;
    private String baseUrl;
    private boolean loadingBarVisibility = false;
    private boolean actionBtnVisibility = false;
    private boolean isCrawlingRunning = false;
    private Thread crawlingThread;
    private Thread emailCrawlerThread;
    private Runnable crawlingRunnable;
    private static ArrayList<String> foundUrls = new ArrayList<>();

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    private void initComponents() {
        this.visitedUrls = new HashSet<>();
        this.searchString = query;
        listModel = new DefaultListModel<>();
        urlBar = new javax.swing.JTextField();
        crawlBtn = new javax.swing.JButton();
        optionSelectorComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        thresholdSlider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        thresholdPercent = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultListMain = new javax.swing.JList<>();
        visualizeBtn = new javax.swing.JButton();
        historyBtn = new javax.swing.JButton();
        loadingBar = new javax.swing.JProgressBar();
        queryText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        crawlingMessage = new javax.swing.JLabel();
        pauseBtn = new javax.swing.JToggleButton();
        stopBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        loadMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        downloadsMenuItem = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        urlBar.setFont(new java.awt.Font("Helvetica", 0, 13)); // NOI18N
        urlBar.setText("https://");
        urlBar.setActionCommand("<Not Set>");
        urlBar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 3, true));
        {
            visualizeBtn.hide();
            pauseBtn.hide();
            stopBtn.hide();
        }
        crawlBtn.setFont(new java.awt.Font("Helvetica", 0, 13)); // NOI18N
        crawlBtn.setText("Crawl");
        loadingBar.hide();
        crawlingThread = new BasicCrawlerController();
        emailCrawlerThread = new EmailCrawlerController();
        crawlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crawlingMessage.setForeground(new Color(3, 119, 32));
                baseUrl = urlBar.getText().toString().toLowerCase();
                query = queryText.getText().toString().toLowerCase();
                queryText.setEnabled(false);
                urlBar.setEnabled(false);
                crawlBtn.setText("Reset");
                searchString = query;
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
                    }
                    isCrawlingRunning = false;
                    dispose();
                    new Main().setVisible(true);
                }
                if (optionSelectorComboBox.getSelectedIndex() == 0)
                {
                    crawlingThread.start();
                }
                else if(optionSelectorComboBox.getSelectedIndex() == 2 )
                {
                    emailCrawlerThread.start();
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

        optionSelectorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Text", "Phone", "Email", "Geographic Information", "Images", "Videos", "PDFs", "Other Docs", "Interesting Files" }));
        optionSelectorComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionSelectorComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(51, 153, 0));
        jLabel1.setText("Select Data Type");

        jLabel2.setForeground(new java.awt.Color(102, 153, 0));
        jLabel2.setText("Threshold");

        thresholdPercent.setForeground(new java.awt.Color(51, 153, 0));
        thresholdPercent.setText("50%");

        thresholdSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // This method will be called whenever the value of the JSlider changes
                int sliderValue = thresholdSlider.getValue();
                thresholdPercent.setText(sliderValue+"%");

                // Perform any action you want based on the slider value
                // For example, update a label, perform a calculation, etc.
            }
        });

        resultListMain.setModel(listModel);
        jScrollPane1.setViewportView(resultListMain);

        visualizeBtn.setText("Visualize");
        visualizeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizeBtnActionPerformed(evt);
            }
        });

        historyBtn.setText("History");
        historyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyBtnActionPerformed(evt);
            }
        });

        queryText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true));
        queryText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryTextActionPerformed(evt);
            }
        });

        jLabel3.setText("Query");

        crawlingMessage.setForeground(new java.awt.Color(51, 204, 0));

        pauseBtn.setText("Pause");
        pauseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseBtnActionPerformed(evt);
            }
        });

        stopBtn.setText("Stop");

        jMenu1.setText("File");

        loadMenuItem.setText("Load");
        jMenu1.add(loadMenuItem);

        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(saveMenuItem);

        saveAsMenuItem.setText("Save as");
        saveAsMenuItem.setActionCommand("Save_as");
        jMenu1.add(saveAsMenuItem);

        jMenuBar1.add(jMenu1);

        downloadsMenuItem.setText("Downloads");
        jMenuBar1.add(downloadsMenuItem);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(urlBar, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel3))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(optionSelectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(queryText)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(crawlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(106, 106, 106)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel2)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(thresholdPercent))
                                                                        .addComponent(thresholdSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1)
                                                .addGap(369, 369, 369)
                                                .addComponent(pauseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(stopBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(visualizeBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(historyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                        .addComponent(loadingBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(crawlingMessage)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loadingBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(crawlBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                                .addComponent(urlBar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(queryText))
                                        .addComponent(jLabel3))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(jLabel1)
                                                .addGap(4, 4, 4))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(pauseBtn)
                                                        .addComponent(visualizeBtn)
                                                        .addComponent(stopBtn)
                                                        .addComponent(historyBtn))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(thresholdPercent))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(thresholdSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(optionSelectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(crawlingMessage)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
        );

        setBounds(0, 0, 878, 605);
    }// </editor-fold>//GEN-END:initComponents


    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void optionSelectorComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionSelectorComboBoxActionPerformed
        // TODO add your handling code here:
        switch (optionSelectorComboBox.getSelectedIndex()){
            case 0:
                queryText.setEnabled(true);
                System.out.println("Text Selected");
                break;
            case 1:
                queryText.setEnabled(false);
                System.out.println("Phone Selected");
                break;
            case 2:
                queryText.setEnabled(false);
                System.out.println("Email Selected");
                break;
            case 3:
                queryText.setEnabled(false);
                System.out.println("Geographic Information");
                break;
            case 4:
                queryText.setEnabled(false);
                System.out.println("Images");
                break;
            case 5:
                queryText.setEnabled(false);
                System.out.println("Videos");
                break;
            case 6:
                queryText.setEnabled(true);
                System.out.println("Pdfs");
                break;
            case 7:
                queryText.setEnabled(true);
                System.out.println("Other Files");
                break;
            case 8:
                queryText.setEnabled(false);
                System.out.println("Interesting Files");
                break;
            default:
                queryText.setEnabled(true);
                System.out.println("G");
                break;
        }
    }//GEN-LAST:event_optionSelectorComboBoxActionPerformed

    private void visualizeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizeBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_visualizeBtnActionPerformed

    private void historyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_historyBtnActionPerformed

    private void queryTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_queryTextActionPerformed

    private void pauseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pauseBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton crawlBtn;
    public static javax.swing.JLabel crawlingMessage;
    public javax.swing.JMenu downloadsMenuItem;
    public javax.swing.JButton historyBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JMenuItem loadMenuItem;
    public static javax.swing.JProgressBar loadingBar;
    public javax.swing.JComboBox<String> optionSelectorComboBox;
    public javax.swing.JToggleButton pauseBtn;
    public static javax.swing.JTextField queryText;
    public javax.swing.JList<String> resultListMain;
    public javax.swing.JMenuItem saveAsMenuItem;
    public javax.swing.JMenuItem saveMenuItem;
    public javax.swing.JButton stopBtn;
    public javax.swing.JLabel thresholdPercent;
    public javax.swing.JSlider thresholdSlider;
    public static javax.swing.JTextField urlBar;
    public javax.swing.JButton visualizeBtn;
    // End of variables declaration//GEN-END:variables

}
