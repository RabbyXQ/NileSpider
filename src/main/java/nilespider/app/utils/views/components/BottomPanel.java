package nilespider.app.utils.views.components;

import nilespider.app.views.main.Layout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BottomPanel extends Panel implements AtomicComponents{
    public BottomPanel(){
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(getWidth(), 50));
        this.add(CRAWLING_MESSAGE);
        this.add(FOUND_DATA_NAME);
        this.add(CRAWLING_FOUND_STATUS);
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        initUI();
    }

    private void initUI(){
        this.setBackground(new Color(243, 248, 255, 147)); // Set background color
        CRAWLING_MESSAGE.setBorder(new EmptyBorder(0, 10, 0, 0));
        FOUND_DATA_NAME.setBorder(new EmptyBorder(0, 10, 0, 0));
        CRAWLING_FOUND_STATUS.setBorder(new EmptyBorder(0, 10, 0, 0));
    }


}
