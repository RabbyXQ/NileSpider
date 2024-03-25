package nilespider.app.views.main;

import nilespider.app.ui.pages.History;
import nilespider.app.ui.pages.HistoryView;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainActions extends StylizeLayout{
    public MainActions(){
        super();
        initActions();
    }

    private void initActions(){
        historyActions();
        historyActions();
        historyBtnAction();
        downloadButtonAction();
    }


    protected void stopCrawling() {
        if (isCrawlingRunning){
            CRAWLER_THREADS[optionSelectorComboBox.getSelectedIndex()].stop();
        }
        loadingBar.setValue(0);
        loadingBar.hide();
        visualizeBtn.hide();
        pauseBtn.hide();
        stopBtn.hide();
        crawlingMessage.setText("Crawling Process stoped");
    }



    private void historyActions(){
        History.loadHistory("history.dat");
        if (new File("history.dat").exists()) {
            History.loadHistory("history.dat");
        } else {
            History.saveHistory("history.dat");
        }
        History.loadHistory("history.dat");
        History.addHistory("URL: " + urlBar.getText().toString() + "Query: " + query);
        History.saveHistory("history.dat");
    }

    private void historyBtnAction(){
        historyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                HistoryView historyView = new HistoryView();
                historyView.getFrame().setVisible(true);
            }
        });
    }

    private void downloadButtonAction(){
        downloadsMenuItem.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                downloads.getFrame().setVisible(true);
            }
            public void menuDeselected(MenuEvent e) {}
            public void menuCanceled(MenuEvent e) {}
        });
    }

}
