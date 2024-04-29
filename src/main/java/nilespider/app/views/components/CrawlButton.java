package nilespider.app.views.components;

import nilespider.app.controller.*;
import nilespider.app.model.*;
import nilespider.app.views.components.interfaces.AtomicComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Custom JButton for Crawl actions with different states.
 */
public class CrawlButton extends JButton implements AtomicComponents {
    private final String[] buttonTexts = new String[]{"Crawl", "Stop", "Reset"};
    CrawlerController  textCrawlerController;
    EmailCrawlerController emailCrawlerController;
    GeographicCrawlerController geographicCrawlerController;
    ImageCrawlerController imageCrawlerController;
    InterestingFileCrawlerController interestingFileCrawlerController;
    OtherDocumentCrawlerController otherDocumentCrawlerController;
    PDFCrawlerController pdfCrawlerController;
    PhoneNumberCrawlerController phoneNumberCrawlerController;
    VideoCrawlerController videoCrawlerController;
    public int buttonStatusCode = 0;
    private final ActionListener actionListener;

    public CrawlButton() {
        this.setPreferredSize(new Dimension(100, 30));
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setText(buttonTexts[0]);
        this.actionListener = e -> performAction();
        this.addActionListener(actionListener);
    }


    public void setButtonText(int statusCode) {
        setButtonStatusCode(statusCode);
        this.setText(buttonTexts[statusCode]);
    }

    public void setButtonStatusCode(int value) {
        buttonStatusCode = value;
    }

    public void performAction() {
        if (buttonStatusCode == 0) {
            setButtonStatusCode(1);
            setButtonText(1);
            performCrawl();
        } else if (buttonStatusCode == 1) {
            setButtonStatusCode(2);
            setButtonText(2);
            performStop();
        } else {
            setButtonStatusCode(0);
            setButtonText(0);
            performReset();
        }
    }
    private void textCrawler(){
        textCrawlerController = new CrawlerController(URL_BAR.getText().toString(), QUERY_BOX.getText().toString());
        textCrawlerController.start();
    }
    private void pdfCrawler(){
        pdfCrawlerController = new PDFCrawlerController(URL_BAR.getText().toString());
        QUERY_BOX.enable(false);
        pdfCrawlerController.start();
    }
    private void performCrawl() {
        switch (SELECTOR_COMBO_BOX.getSelectedIndex()) {
            case 0:
                textCrawler();
            case 6:
                pdfCrawler();
            default:
                textCrawler();
        }
        if (QUERY_BOX.getText().toString() != null){
            historyView.addToHistory(URL_BAR.getText().toString()+ "\nQuery: " +QUERY_BOX.getText().toString());
        }
        URL_BAR.enable(false);
        QUERY_BOX.enable(false);
        CRAWLING_MESSAGE_BUNDLE.show();
        LOADING_BAR.show();
    }


    void stopTreads(){
        textCrawlerController.stopThread();
        pdfCrawlerController.stop();
    }

    private void performStop() {
        CRAWLING_MESSAGE_BUNDLE.hide();
        stopTreads();
        LOADING_BAR.hide();
    }

    private void performReset() {
        RESULT_LIST_MODEL.clear();
        URL_BAR.enable(true);
        QUERY_BOX.enable(true);
        CRAWLING_MESSAGE_BUNDLE.hide();
    }
}
