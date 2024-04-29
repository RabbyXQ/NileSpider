package nilespider.app.views.components.interfaces;

import nilespider.app.model.*;
import nilespider.app.ui.pages.Downloads;
import nilespider.app.ui.pages.HistoryView;
import nilespider.app.views.components.CrawlButton;
import nilespider.app.views.components.CrawlingMessage;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public interface AtomicComponents {

    /**
     * Declaring Usable Constant Components for the Main User Interface.
     * **/
    public JTextField URL_BAR = new JTextField();
    public JTextField QUERY_BOX = new JTextField();

    public CrawlButton CRAWL_BUTTON = new CrawlButton();

    public JLabel THRESHOLD_TITLE  = new JLabel("Threshold: ");
    public  JLabel THRESHOLD_PERCENT =  new JLabel();
    public JSlider THRESHOLD_SLIDER = new JSlider();
    public JLabel SELECT_TYPE_TITLE = new JLabel("Select Type: ");

    public String[] CRAWLER_TYPE = new String[]{"Text", "Phone", "Email", "Geographic/Map", "Images", "Videos", "PDFs", "Other Docs", "Interesting Files"};
    public JComboBox<String> SELECTOR_COMBO_BOX = new JComboBox<>();
    public ComboBoxModel<String> SELECTOR_COMBOBOX_MODEL = new DefaultComboBoxModel<>(CRAWLER_TYPE);
    public DefaultListModel<String> RESULT_LIST_MODEL = new DefaultListModel<>();
    public JList<String> RESULT_LIST = new JList<>(RESULT_LIST_MODEL);
    public JProgressBar LOADING_BAR = new JProgressBar();

    public CrawlingMessage CRAWLING_MESSAGE_BUNDLE = new CrawlingMessage();


    HistoryView historyView = new HistoryView();
    Downloads downloads = new Downloads();
}
