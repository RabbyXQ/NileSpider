package nilespider.app.utils.views.components;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

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

    public CrawlButton CRAWL_BUTTON = new CrawlButton();
    public Button STOP_BUTTON = new Button("Stop");

    public JLabel THRESHOLD_TITLE  = new JLabel("Threshold: ");
    public  JLabel THRESHOLD_PERCENT =  new JLabel();
    public JSlider THRESHOLD_SLIDER = new JSlider();
    public JLabel SELECT_TYPE_TITLE = new JLabel("Select Type: ");

    public String[] CRAWLER_TYPE = new String[]{
            "Text",
            "Phone",
            "Email",
            "Geographic/Map",
            "Images",
            "Videos",
            "PDFs",
            "Other Docs",
            "Interesting Files"
    };
    public JComboBox<String> SELECTOR_COMBO_BOX = new JComboBox<>();
    public ComboBoxModel<String> SELECTOR_COMBOBOX_MODEL = new DefaultComboBoxModel<>(CRAWLER_TYPE);
    public DefaultListModel<String> RESULT_LIST_MODEL = new DefaultListModel<>();
    public JList<String> RESULT_LIST = new JList<>(RESULT_LIST_MODEL);
    public JProgressBar LOADING_BAR = new JProgressBar();

    public int[] CRAWL_BUTTON_STATES = new int[]{0,1,2};
    public String[] CRAWL_BUTTON_TEXT = new String[]{"CRAWL", "STOP", "RESET"};
    public CrawlingMessage CRAWLING_MESSAGE_BUNDLE = new CrawlingMessage();

}
