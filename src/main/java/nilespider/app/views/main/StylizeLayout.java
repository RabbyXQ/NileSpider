package nilespider.app.views.main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StylizeLayout extends Layout{
    public StylizeLayout(){
        super();
        initializeComponents();
    }

    private void initializeComponents() {
        stylizeUrlBar();
        hideGroupButton();
        initCrawlBtn();
        initUrlBar();
        initComboBox();
        initThreshold();
    }

    private void stylizeUrlBar(){
        urlBar.setFont(new Font("Helvetica", 0, 13));
        urlBar.setText("https://");
        urlBar.setActionCommand("<Not Set>");
        urlBar.setBorder(new LineBorder(new Color(0, 102, 102), 3, true));
    }
    private void hideGroupButton(){
        visualizeBtn.hide();
        pauseBtn.hide();
        stopBtn.hide();
    }

    private void initCrawlBtn()
    {
        crawlBtn.setFont(new Font("Helvetica", 0, 13)); // NOI18N
        crawlBtn.setText("Crawl");
        loadingBar.hide();
    }
    private void initUrlBar(){
        crawlingMessage.setForeground(new Color(3, 119, 32));
        query = queryText.getText().toString().toLowerCase();
    }

    private void initComboBox(){
        optionSelectorComboBox.setModel(new DefaultComboBoxModel<>(COMBO_BOX_MENU_ITEMS));
        optionSelectorComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                optionSelectorComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new Color(51, 153, 0));
        jLabel1.setText("Select Data Type");
    }

    private void initThreshold (){
        jLabel2.setForeground(new Color(102, 153, 0));
        jLabel2.setText("Threshold");

        thresholdPercent.setForeground(new Color(51, 153, 0));
        thresholdPercent.setText("50%");
    }
    private void optionSelectorComboBoxActionPerformed(ActionEvent evt) {
    }
}
