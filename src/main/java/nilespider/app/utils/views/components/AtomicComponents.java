package nilespider.app.utils.views.components;

import javax.swing.*;
import java.awt.*;

public interface AtomicComponents {

    /**
     * Declaring Usable Constant Components for the Main User Interface.
     * **/
    public JTextField URL_BAR = new JTextField();
    public JTextField QUERY_BOX = new JTextField();

    public Button SIDEBAR_SAVE_BUTTON = new Button("Save");
    public Button SIDEBAR_LOAD_BUTTON = new Button("Load");
    public Button SIDEBAR_DOWNLOADS_BUTTON = new Button("Downlads");
    public Button SIDE_BAR_HISTORY_BUTTON = new Button("History");
    public Button SIDE_BAR_VISUALIZE_BUTTON = new Button("Visualize");

    public Button CRAWL_BUTTON = new Button("Crawl");
    public Button STOP_BUTTON = new Button("Stop");

    public JLabel THRESHOLD_TITLE  = new JLabel("Threshold: ");
    public  JLabel THRESHOLD_PERCENT =  new JLabel("50");
    public JProgressBar LOADING_BAR = new JProgressBar();

}
