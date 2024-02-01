/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nilespider.app;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import nilespider.app.utils.Crawler;

/**
 *
 * @author macbook
 */
public class Main extends javax.swing.JFrame {
    public ArrayList<String> result;
    private String url;
    private String query;
    public DefaultListModel<String> listModel;
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        loadMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        downloadsMenuItem = new javax.swing.JMenu();
        listModel = new DefaultListModel<>();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        urlBar.setFont(new java.awt.Font("Helvetica", 0, 13)); // NOI18N
        urlBar.setText("https://");
        urlBar.setActionCommand("<Not Set>");
        urlBar.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        crawlBtn.setFont(new java.awt.Font("Helvetica", 0, 13)); // NOI18N
        crawlBtn.setText("Crawl");
        crawlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                url = urlBar.getText().toString().toLowerCase();
                query = queryText.getText().toString().toLowerCase();
                if (!listModel.isEmpty()){
                    listModel.clear();
                }
                Crawler crawler = new Crawler(query);
                crawler.crawl(url);
                ArrayList<String> searchResultUrls = crawler.getFoundUrls();
                for (String data: searchResultUrls)
                {
                    listModel.addElement(data);
                }
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

        queryText.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        queryText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryTextActionPerformed(evt);
            }
        });

        jLabel3.setText("Query");

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(optionSelectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel1)))
                                .addGap(78, 78, 78))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(urlBar, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(visualizeBtn)
                                    .addComponent(historyBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(thresholdPercent))
                                    .addComponent(thresholdSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 4, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(queryText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(crawlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addComponent(loadingBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(thresholdPercent)
                        .addComponent(visualizeBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(thresholdSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(historyBtn)
                        .addComponent(optionSelectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
        );

        setBounds(0, 0, 753, 587);
    }// </editor-fold>//GEN-END:initComponents

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void optionSelectorComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionSelectorComboBoxActionPerformed
        // TODO add your handling code here:
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
    public javax.swing.JMenu downloadsMenuItem;
    public javax.swing.JButton historyBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JMenuItem loadMenuItem;
    public javax.swing.JProgressBar loadingBar;
    public javax.swing.JComboBox<String> optionSelectorComboBox;
    public javax.swing.JTextField queryText;
    public javax.swing.JList<String> resultListMain;
    public javax.swing.JMenuItem saveAsMenuItem;
    public javax.swing.JMenuItem saveMenuItem;
    public javax.swing.JLabel thresholdPercent;
    public javax.swing.JSlider thresholdSlider;
    public javax.swing.JTextField urlBar;
    public javax.swing.JButton visualizeBtn;
    // End of variables declaration//GEN-END:variables
    
 

}
