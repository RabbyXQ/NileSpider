package nilespider.app.utils.views.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Custom JButton for Crawl actions with different states.
 */
public class CrawlButton extends JButton implements AtomicComponents{
    private final String[] buttonTexts = new String[]{"Crawl", "Stop", "Reset"};
    private int buttonStatusCode = 0;
    private final ActionListener actionListener;

    /**
     * Constructs a CrawlButton with default settings.
     */
    public CrawlButton() {
        this.setPreferredSize(new Dimension(100, 30));
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setText(buttonTexts[0]);
        this.actionListener = e -> performAction();
        this.addActionListener(actionListener);
    }

    /**
     * Sets the text of the button based on the status code.
     * @param statusCode The status code representing the state of the button.
     */
    public void setButtonText(int statusCode) {
        setButtonStatusCode(statusCode);
        this.setText(buttonTexts[statusCode]);
    }

    /**
     * Sets the status code of the button.
     * @param value The value to set as the status code.
     */
    private void setButtonStatusCode(int value) {
        buttonStatusCode = value;
    }

    /**
     * Performs an action based on the current status code.
     */
    private void performAction() {
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

    /**
     * Performs the crawl action.
     */
    private void performCrawl() {
        URL_BAR.enable(false);
        QUERY_BOX.enable(false);
        CRAWLING_MESSAGE_BUNDLE.show();
    }

    /**
     * Performs the stop action.
     */
    private void performStop() {
        // Implement stop action here
    }

    /**
     * Performs the reset action.
     */
    private void performReset() {
        // Implement reset action here
        URL_BAR.enable(true);
        QUERY_BOX.enable(true);
        CRAWLING_MESSAGE_BUNDLE.hide();
    }
}
