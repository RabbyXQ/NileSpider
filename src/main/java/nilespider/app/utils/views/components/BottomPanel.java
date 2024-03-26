package nilespider.app.utils.views.components;

import nilespider.app.views.main.Layout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BottomPanel extends Panel
        implements AtomicComponents{
    public BottomPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(getWidth(), 50));
        this.add(CRAWLING_MESSAGE_BUNDLE);
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.setBackground(new Color(243, 248, 255, 147));
    }

}
