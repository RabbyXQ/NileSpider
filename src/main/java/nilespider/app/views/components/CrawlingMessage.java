package nilespider.app.views.components;

import nilespider.app.views.components.interfaces.AtomicComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CrawlingMessage extends Panel
        implements AtomicComponents {
    public JLabel CRAWLING_MESSAGE;
    public JLabel CRAWLING_FOUND_STATUS;
    public JLabel FOUND_DATA_NAME;
    public CrawlingMessage()
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
        CRAWLING_FOUND_STATUS = new JLabel("Found");
        FOUND_DATA_NAME = new JLabel("FILE NAME");
        CRAWLING_MESSAGE.setBorder(new EmptyBorder(0, 10, 0, 0));
        FOUND_DATA_NAME.setBorder(new EmptyBorder(0, 10, 0, 0));
        CRAWLING_FOUND_STATUS.setBorder(new EmptyBorder(0, 10, 0, 0));
    }

    public void updateUI(boolean found, String url, String token){
        this.CRAWLING_MESSAGE.setText("Crawling: "+ url);
        if (found){
            if (!FOUND_DATA_NAME.isShowing() && !CRAWLING_FOUND_STATUS.isShowing()){
                RESULT_LIST_MODEL.addElement(url);
                FOUND_DATA_NAME.show();
                CRAWLING_FOUND_STATUS.show();
            }
            this.FOUND_DATA_NAME.setText(token);
        }else{
            FOUND_DATA_NAME.hide();
            CRAWLING_FOUND_STATUS.hide();
        }
    }

    public void updateUIError(String url){
        CRAWLING_MESSAGE.setText("Error occured on "+ url);
        FOUND_DATA_NAME.hide();
        CRAWLING_FOUND_STATUS.hide();
    }

    public void updateUiOnDone(){
        CRAWLING_MESSAGE.setText("Crawling Process Finished!");
        FOUND_DATA_NAME.hide();
        CRAWLING_FOUND_STATUS.hide();
    }

}
