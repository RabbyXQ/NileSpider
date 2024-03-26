package nilespider.app.utils.views.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class URLBarBundle extends Panel
        implements AtomicComponents{
    public URLBarBundle(){
        initUrlBar();
        initQueryBox();
        initCrawlBtn();
        initUrlBarBundle();
    }

    private void initUrlBarBundle(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        addComponentsWithGridBag(this, gbc, URL_BAR, 1.0, GridBagConstraints.HORIZONTAL);
        addComponentsWithGridBag(this, gbc, QUERY_BOX, 1.0, GridBagConstraints.HORIZONTAL);
        addComponentsWithGridBag(this, gbc, CRAWL_BUTTON, 0.0, GridBagConstraints.NONE);
    }
    private void addComponentsWithGridBag(Panel panel, GridBagConstraints gbc, Component component, double weightx, int fill) {
        gbc.fill = fill;
        gbc.weightx = weightx;
        panel.add(component, gbc);
        gbc.gridx++;
    }

    private void initUrlBar() {
        URL_BAR.setText("Enter URL");
        URL_BAR.setPreferredSize(new Dimension(200, 30)); // Adjusted size
        URL_BAR.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font
        URL_BAR.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10)); // Add padding
        URL_BAR.setBackground(Color.WHITE); // Set background color
        URL_BAR.setForeground(Color.GRAY); // Set placeholder text color
        URL_BAR.setCaretColor(Color.BLACK); // Set caret color

        // Add focus listener to show/hide placeholder text
        URL_BAR.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (URL_BAR.getText().equals("Enter URL")) {
                    URL_BAR.setText("https://");
                    URL_BAR.setForeground(Color.BLACK); // Change text color when focused
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (URL_BAR.getText().equals("https://")) {
                    URL_BAR.setText("Enter URL");
                    URL_BAR.setForeground(Color.GRAY); // Change text color when not focused
                }
            }
        });
    }

    private void initQueryBox() {
        QUERY_BOX.setPreferredSize(new Dimension(200, 30)); // Adjusted size
        QUERY_BOX.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font
        QUERY_BOX.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10)); // Add padding
        QUERY_BOX.setBackground(Color.WHITE); // Set background color
        QUERY_BOX.setForeground(Color.GRAY); // Set placeholder text color
        QUERY_BOX.setCaretColor(Color.BLACK); // Set caret color
        QUERY_BOX.setText("Enter query");
        // Add focus listener to show/hide placeholder text
        QUERY_BOX.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (QUERY_BOX.getText().equals("Enter query")) {
                    QUERY_BOX.setText("");
                    QUERY_BOX.setForeground(Color.BLACK); // Change text color when focused
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (QUERY_BOX.getText().isEmpty()) {
                    QUERY_BOX.setText("Enter query");
                    QUERY_BOX.setForeground(Color.GRAY); // Change text color when not focused
                }
            }
        });
    }


    private void initCrawlBtn() {
        CRAWL_BUTTON.setPreferredSize(new Dimension(100, 30)); // Adjusted size
        CRAWL_BUTTON.setFont(new Font("Arial", Font.BOLD, 14)); // Set font
    }


}
