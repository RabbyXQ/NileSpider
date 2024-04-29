package nilespider.app.views.components;

import nilespider.app.controller.*;
import nilespider.app.ui.pages.HistoryView;
import nilespider.app.views.components.interfaces.AtomicComponents;

import javax.swing.*;
import java.awt.*;

public class CrawlButton extends JButton implements AtomicComponents {
    private final Thread[] crawlerThreads = new Thread[9];
    private int buttonStatusCode = 0;

    public CrawlButton() {
        setPreferredSize(new Dimension(100, 30));
        setFont(new Font("Arial", Font.BOLD, 14));
        setText("Crawl");
        addActionListener(e -> performAction());
    }

    public void performAction() {
        switch (buttonStatusCode) {
            case 0 -> { buttonStatusCode = 1; setText("Stop"); performCrawl(); }
            case 1 -> { buttonStatusCode = 2; setText("Reset"); stopCrawlerThreads(); }
            default -> { buttonStatusCode = 1; setText("Crawl"); performReset(); }
        }
    }

    private void performCrawl() {
        stopCrawlerThreads();
        int selectedIndex = SELECTOR_COMBO_BOX.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < crawlerThreads.length) {
            startCrawlerThread(selectedIndex);
        }
        handleHistory();
        handleUIState(true);
    }

    private void stopCrawlerThreads() {
        for (Thread thread : crawlerThreads) {
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        }
    }

    private void startCrawlerThread(int index) {
        if (crawlerThreads[index] == null || !crawlerThreads[index].isAlive()) {
            crawlerThreads[index] = switch (index) {
                case 0 -> new TextCrawlerController(URL_BAR.getText(), QUERY_BOX.getText());
                case 1 -> new PhoneNumberCrawlerController(URL_BAR.getText());
                case 2 -> new EmailCrawlerController(URL_BAR.getText());
                case 3 -> new GeographicCrawlerController(URL_BAR.getText());
                case 4 -> new ImageCrawlerController(URL_BAR.getText());
                case 5 -> new VideoCrawlerController(URL_BAR.getText());
                case 6 -> new PDFCrawlerController(URL_BAR.getText());
                case 7 -> new OtherDocumentCrawlerController(URL_BAR.getText());
                case 8 -> new InterestingFileCrawlerController(URL_BAR.getText());
                default -> null;
            };
            if (crawlerThreads[index] != null) {
                crawlerThreads[index].start();
            }
        }
    }

    private void handleHistory() {
        if (QUERY_BOX.getText() != null && !QUERY_BOX.getText().isEmpty()) {
            historyView.addToHistory(URL_BAR.getText() + "\nQuery: " + QUERY_BOX.getText());
        }
    }

    private void handleUIState(boolean enabled) {
        URL_BAR.setEnabled(enabled);
        QUERY_BOX.setEnabled(enabled);
        CRAWLING_MESSAGE_BUNDLE.setVisible(!enabled);
        LOADING_BAR.setVisible(!enabled);
    }

    private void performReset() {
        handleUIState(true);
        RESULT_LIST_MODEL.clear();
    }
}
