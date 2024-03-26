package nilespider.app.utils.views.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CrawlingMessage extends Panel
        implements AtomicComponents{
    public JLabel CRAWLING_MESSAGE;
    public JLabel CRAWLING_FOUND_STATUS;
    public JLabel FOUND_DATA_NAME;
    CrawlingMessage()
    {
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(getWidth(), 50));
        initUI();
        this.add(CRAWLING_MESSAGE);
        this.add(CRAWLING_FOUND_STATUS);
        this.add(FOUND_DATA_NAME);
        this.hide();
    }

    private void initUI(){
        CRAWLING_MESSAGE = new JLabel("Crawling: ");
        CRAWLING_FOUND_STATUS = new JLabel("FOUND");
        FOUND_DATA_NAME = new JLabel("FILE NAME");
        CRAWLING_MESSAGE.setBorder(new EmptyBorder(0, 10, 0, 0));
        FOUND_DATA_NAME.setBorder(new EmptyBorder(0, 10, 0, 0));
        CRAWLING_FOUND_STATUS.setBorder(new EmptyBorder(0, 10, 0, 0));
    }

}
