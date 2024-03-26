package nilespider.app.utils.views.components;

import java.awt.*;

public class BottomPanel extends Panel implements AtomicComponents{
    public BottomPanel(){
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(getWidth(), 40));
        this.setBackground(new Color(243, 248, 255, 147)); // Set background color
    }
}
