/**
 * The Class after Layout that adds some necessary Components to The existing UI
 * **/

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
        initSomeText();
        initThreshold();
        initMenu();
    }

    private void initSomeText(){
        visualizeBtn.setText("Visualize");
        historyBtn.setText("History");
        jLabel3.setText("Query");
        crawlingMessage.setForeground(new Color(51, 204, 0));
        pauseBtn.setText("Pause");
        stopBtn.setText("Stop");
    }

    private void initMenu(){
        jMenu1.add(saveMenuItem);
        jMenu1.setText("File");
        loadMenuItem.setText("Load");
        jMenu1.add(loadMenuItem);
        saveMenuItem.setText("Save");
        saveAsMenuItem.setText("Save as");
        saveAsMenuItem.setActionCommand("Save_as");
        jMenu1.add(saveAsMenuItem);
        jMenuBar1.add(jMenu1);
        downloadsMenuItem.setText("Downloads");

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
