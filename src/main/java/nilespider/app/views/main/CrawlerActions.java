package nilespider.app.views.main;

import nilespider.app.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrawlerActions extends SecondaryActions{
    public CrawlerActions(){
        super();
    }
    private void initComp(){
        startCrawlBtn();
    }
    private void startCrawlBtn(){

    }
    private void updateUIBeforeCrawling() {
        crawlingMessage.setForeground(new Color(3, 119, 32));
        query = queryText.getText().toString().toLowerCase();
        queryText.setEnabled(false);
        urlBar.setEnabled(false);
        crawlBtn.setText("Reset");
        clearListModelIfNeeded();
        adjustLoadingBarVisibility();
        showActionButtonsIfNeeded();
    }

    private void clearListModelIfNeeded() {
        if (!listModel.isEmpty()) {
            listModel.clear();
        }
    }

    private void adjustLoadingBarVisibility() {
        if (loadingBarVisibility) {
            loadingBar.setValue(0);
        } else {
            loadingBarVisibility = true;
            loadingBar.show();
        }
    }

    private void showActionButtonsIfNeeded() {
        if (!actionBtnVisibility) {
            visualizeBtn.show();
            pauseBtn.show();
            stopBtn.show();
        }
    }

    private void stopPreviousCrawling() {
        if (isCrawlingRunning) {
            stopCurrentCrawling();
            resetUIAfterCrawlingStopped();
        }
    }

    private void stopCurrentCrawling() {
        switch (optionSelectorComboBox.getSelectedIndex()) {
            case 0:
                crawlingThread.stop();
                break;
            case 1:
                phoneNumberCrawlerController.stop();
                break;
            case 2:
                emailCrawlerThread.stop();
                break;
            case 3:
                geographicCrawlerController.stop();
                break;
            case 4:
                imageCrawlerController.stop();
                break;
            case 5:
                videoCrawlerController.stop();
                break;
            case 6:
                pdfCrawlerController.stop();
                break;
            case 7:
                otherDocumentCrawlerController.stop();
                break;
            case 8:
                interestingFileCrawlerController.stop();
                break;
        }
        isCrawlingRunning = false;
    }

    private void resetUIAfterCrawlingStopped() {
        dispose();
        new Main().setVisible(true);
    }

    private void startCrawling() {
        switch (optionSelectorComboBox.getSelectedIndex()) {
            case 0:
                crawlingThread.start();
                break;
            case 1:
                phoneNumberCrawlerController.start();
                break;
            case 2:
                emailCrawlerThread.start();
                break;
            case 3:
                geographicCrawlerController.start();
                break;
            case 4:
                imageCrawlerController.start();
                break;
            case 5:
                videoCrawlerController.start();
                break;
            case 6:
                pdfCrawlerController.start();
                break;
            case 7:
                otherDocumentCrawlerController.start();
                break;
            case 8:
                interestingFileCrawlerController.start();
                break;
        }
        isCrawlingRunning = true;
    }

}
