/**
 * Main Skeleton File for Index page Design of the software.
 * where all the necessary components of JFrame have been declared and initialized.
 * **/

package nilespider.app.views.main;

import nilespider.app.ui.pages.Downloads;
import nilespider.app.utils.others.URLExtractor;

import javax.swing.*;

public class Skeleton extends JFrame implements CrawlerTypeInterface{
    public JButton crawlBtn;
    public Downloads downloads;
    protected URLExtractor urlExtractor;
    public static JLabel crawlingMessage;
    public JMenu downloadsMenuItem;
    public JButton historyBtn;
    protected JLabel jLabel1;
    protected JLabel jLabel2;
    protected JLabel jLabel3;
    protected JMenu jMenu1;
    protected JMenuBar jMenuBar1;
    protected JScrollPane jScrollPane1;
    public JMenuItem loadMenuItem;
    public static JProgressBar loadingBar;
    public JComboBox<String> optionSelectorComboBox;
    public JToggleButton pauseBtn;
    public static JTextField queryText;
    public JList<String> resultListMain;
    public JMenuItem saveAsMenuItem;
    public JMenuItem saveMenuItem;
    public JButton stopBtn;
    public JLabel thresholdPercent;
    public JSlider thresholdSlider;
    public static JTextField urlBar;
    public JButton visualizeBtn;
    public static String query;
    public static DefaultListModel<String> listModel;
    protected boolean loadingBarVisibility = false;
    protected boolean actionBtnVisibility = false;
    protected boolean isCrawlingRunning = false;

    public Skeleton() {
        listModel = new DefaultListModel<>();
        urlBar = new JTextField();
        crawlBtn = new JButton();
        optionSelectorComboBox = new JComboBox<>();
        jLabel1 = new JLabel();
        thresholdSlider = new JSlider();
        jLabel2 = new JLabel();
        thresholdPercent = new JLabel();
        jScrollPane1 = new JScrollPane();
        resultListMain = new JList<>();
        visualizeBtn = new JButton();
        historyBtn = new JButton();
        loadingBar = new JProgressBar();
        queryText = new JTextField();
        jLabel3 = new JLabel();
        crawlingMessage = new JLabel();
        pauseBtn = new JToggleButton();
        stopBtn = new JButton();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        loadMenuItem = new JMenuItem();
        saveMenuItem = new JMenuItem();
        saveAsMenuItem = new JMenuItem();
        downloadsMenuItem = new JMenu();
        downloads = new Downloads();
        downloads.getFrame().dispose();
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(initMainHorizontalGroup(layout));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(initVerticalGroup(layout))
        );
        setBounds(0, 0, 878, 605);
    }

    protected GroupLayout.Group initVerticalGroup(GroupLayout layout) {
        return null;
    }

    protected GroupLayout.Group initMainHorizontalGroup(GroupLayout layout) {
        return null;
    }
}

