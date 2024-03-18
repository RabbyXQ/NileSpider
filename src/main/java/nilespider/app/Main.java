package nilespider.app;


import nilespider.app.ui.pages.Downloads;
import nilespider.app.ui.pages.History;
import nilespider.app.ui.pages.HistoryView;
import nilespider.app.utils.controllers.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends JFrame {
    public JButton crawlBtn;
    public Downloads downloads;
    public static JLabel crawlingMessage;
    public JMenu downloadsMenuItem;
    public JButton historyBtn;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JMenu jMenu1;
    private JMenuBar jMenuBar1;
    private JScrollPane jScrollPane1;
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
    private boolean loadingBarVisibility = false;
    private boolean actionBtnVisibility = false;
    private boolean isCrawlingRunning = false;
    private Thread crawlingThread;
    private Thread emailCrawlerThread;

    public Main() {
        initComponents();
    }

    private void initComponents() {
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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        urlBar.setFont(new Font("Helvetica", 0, 13));
        urlBar.setText("https://");
        urlBar.setActionCommand("<Not Set>");
        urlBar.setBorder(new LineBorder(new Color(0, 102, 102), 3, true));
        {
            visualizeBtn.hide();
            pauseBtn.hide();
            stopBtn.hide();
        }
        History.loadHistory("history.dat");
        if (new File("history.dat").exists()) {
            History.loadHistory("history.dat");
        } else {
            History.saveHistory("history.dat");
        }
        History.loadHistory("history.dat");
        History.addHistory("URL: " + urlBar.getText().toString() + "Query: " + query);
        History.saveHistory("history.dat");
        crawlBtn.setFont(new Font("Helvetica", 0, 13)); // NOI18N
        crawlBtn.setText("Crawl");
        loadingBar.hide();
        crawlingThread = new BasicCrawlerController();
        emailCrawlerThread = new EmailCrawlerController();
        PhoneNumberCrawlerController phoneNumberCrawlerController = new PhoneNumberCrawlerController();
        GeographicCrawlerController geographicCrawlerController = new GeographicCrawlerController();
        ImageCrawlerController imageCrawlerController = new ImageCrawlerController();
        VideoCrawlerController  videoCrawlerController = new VideoCrawlerController();
        PDFCrawlerController pdfCrawlerController = new PDFCrawlerController();
        OtherDocumentCrawlerController otherDocumentCrawlerController = new OtherDocumentCrawlerController();
        InterestingFileCrawlerController interestingFileCrawlerController = new InterestingFileCrawlerController();
        crawlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crawlingMessage.setForeground(new Color(3, 119, 32));
                query = queryText.getText().toString().toLowerCase();
                //History.historyList.add(query);
                queryText.setEnabled(false);
                urlBar.setEnabled(false);
                crawlBtn.setText("Reset");
                if (!listModel.isEmpty()){
                    listModel.clear();
                }
                if (loadingBarVisibility)
                {
                    loadingBar.setValue(0);
                }
                if (!loadingBarVisibility)
                {
                    loadingBarVisibility = true;
                    loadingBar.show();
                }
                if (!actionBtnVisibility)
                {
                    visualizeBtn.show();
                    pauseBtn.show();
                    stopBtn.show();
                }
                if (isCrawlingRunning)
                {
                    if (optionSelectorComboBox.getSelectedIndex() == 0)
                    {
                        crawlingThread.stop();
                    } else if (optionSelectorComboBox.getSelectedIndex() == 2) {

                        emailCrawlerThread.stop();
                    } else if (optionSelectorComboBox.getSelectedIndex() == 1) {

                        phoneNumberCrawlerController.stop();
                    } else if (optionSelectorComboBox.getSelectedIndex() == 3) {
                        geographicCrawlerController.stop();
                    } else if (optionSelectorComboBox.getSelectedIndex() == 4) {
                        imageCrawlerController.stop();
                    } else if (optionSelectorComboBox.getSelectedIndex() == 5) {
                        videoCrawlerController.stop();
                    } else if (optionSelectorComboBox.getSelectedIndex() == 6) {
                        pdfCrawlerController.stop();
                    }else if(optionSelectorComboBox.getSelectedIndex() == 7)
                    {
                        otherDocumentCrawlerController.stop();
                    }else if(optionSelectorComboBox.getSelectedIndex() == 8)
                    {
                        interestingFileCrawlerController.stop();
                    }
                    isCrawlingRunning = false;
                    dispose();
                    new Main().setVisible(true);
                }
                if (optionSelectorComboBox.getSelectedIndex() == 0)
                {
                    crawlingThread.start();
                } else if (optionSelectorComboBox.getSelectedIndex() == 1) {
                    phoneNumberCrawlerController.start();

                } else if(optionSelectorComboBox.getSelectedIndex() == 2 )
                {
                    emailCrawlerThread.start();
                } else if (optionSelectorComboBox.getSelectedIndex() == 3) {
                    geographicCrawlerController.start();
                } else if (optionSelectorComboBox.getSelectedIndex() == 4) {
                    imageCrawlerController.start();
                } else if (optionSelectorComboBox.getSelectedIndex() == 5) {
                    videoCrawlerController.start();
                } else if (optionSelectorComboBox.getSelectedIndex() == 6) {
                    pdfCrawlerController.start();
                }else if(optionSelectorComboBox.getSelectedIndex() == 7)
                {
                    otherDocumentCrawlerController.start();
                }else if(optionSelectorComboBox.getSelectedIndex() == 8)
                {
                    interestingFileCrawlerController.start();
                }
                isCrawlingRunning = true;
            }
        });
        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello From Stop Button");
                if (isCrawlingRunning){
                    crawlingThread.stop();
                }
                {
                    loadingBar.setValue(0);
                    loadingBar.hide();
                    visualizeBtn.hide();
                    pauseBtn.hide();
                    stopBtn.hide();
                }
                crawlingMessage.setText("Crawling Process stoped");
                crawlingMessage.setForeground(new Color(180, 0, 0));
            }
        });

        optionSelectorComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Text", "Phone", "Email", "Geographic Information", "Images", "Videos", "PDFs", "Other Docs", "Interesting Files" }));
        optionSelectorComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                optionSelectorComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new Color(51, 153, 0));
        jLabel1.setText("Select Data Type");

        jLabel2.setForeground(new Color(102, 153, 0));
        jLabel2.setText("Threshold");

        thresholdPercent.setForeground(new Color(51, 153, 0));
        thresholdPercent.setText("50%");
        resultListMain.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double click detected
                    int index = resultListMain.locationToIndex(e.getPoint());
                    if (index != -1) {
                        String selectedItem = listModel.getElementAt(index);
                        if (optionSelectorComboBox.getSelectedIndex() != 0)
                        {
                            String url = extractURLs(selectedItem);
                            downloads.addToDownloads(url, "/Users/macbook/Downloads");
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        thresholdSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int sliderValue = thresholdSlider.getValue();
                thresholdPercent.setText(sliderValue+"%");
            }
        });

        resultListMain.setModel(listModel);
        jScrollPane1.setViewportView(resultListMain);

        visualizeBtn.setText("Visualize");
        visualizeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                visualizeBtnActionPerformed(evt);
            }
        });

        historyBtn.setText("History");
        historyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                historyBtnActionPerformed(evt);
            }
        });

        queryText.setBorder(new LineBorder(new Color(0, 153, 153), 3, true));
        queryText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                queryTextActionPerformed(evt);
            }
        });

        jLabel3.setText("Query");

        crawlingMessage.setForeground(new Color(51, 204, 0));

        pauseBtn.setText("Pause");
        pauseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pauseBtnActionPerformed(evt);
            }
        });

        stopBtn.setText("Stop");

        jMenu1.setText("File");

        loadMenuItem.setText("Load");
        jMenu1.add(loadMenuItem);

        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(saveMenuItem);

        saveAsMenuItem.setText("Save as");
        saveAsMenuItem.setActionCommand("Save_as");
        jMenu1.add(saveAsMenuItem);

        jMenuBar1.add(jMenu1);

        downloadsMenuItem.setText("Downloads");
        downloadsMenuItem.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                downloads.getFrame().setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        jMenuBar1.add(downloadsMenuItem);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(urlBar, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel3))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(optionSelectorComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(queryText)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(crawlBtn, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(106, 106, 106)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel2)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(thresholdPercent))
                                                                        .addComponent(thresholdSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1)
                                                .addGap(369, 369, 369)
                                                .addComponent(pauseBtn, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(stopBtn)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(visualizeBtn)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(historyBtn, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                        .addComponent(loadingBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(crawlingMessage)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loadingBar, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(crawlBtn, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                                .addComponent(urlBar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(queryText))
                                        .addComponent(jLabel3))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(jLabel1)
                                                .addGap(4, 4, 4))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(pauseBtn)
                                                        .addComponent(visualizeBtn)
                                                        .addComponent(stopBtn)
                                                        .addComponent(historyBtn))))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(thresholdPercent))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(thresholdSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(optionSelectorComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(crawlingMessage)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
        );

        setBounds(0, 0, 878, 605);
    }


    private void saveMenuItemActionPerformed(ActionEvent evt) {

    }

    private void optionSelectorComboBoxActionPerformed(ActionEvent evt) {
        switch (optionSelectorComboBox.getSelectedIndex()){
            case 0:
                queryText.setEnabled(true);
                System.out.println("Text Selected");
                break;
            case 1:
                queryText.setEnabled(false);
                System.out.println("Phone Selected");
                break;
            case 2:
                queryText.setEnabled(false);
                System.out.println("Email Selected");
                break;
            case 3:
                queryText.setEnabled(false);
                System.out.println("Geographic Information");
                break;
            case 4:
                queryText.setEnabled(false);
                System.out.println("Images");
                break;
            case 5:
                queryText.setEnabled(false);
                System.out.println("Videos");
                break;
            case 6:
                queryText.setEnabled(false);
                System.out.println("Pdfs");
                break;
            case 7:
                queryText.setEnabled(false);
                System.out.println("Other Files");
                break;
            case 8:
                queryText.setEnabled(false);
                System.out.println("Interesting Files");
                break;
            default:
                queryText.setEnabled(true);
                System.out.println("G");
                break;
        }
    }

    private void visualizeBtnActionPerformed(ActionEvent evt) {

    }

    private void historyBtnActionPerformed(ActionEvent evt) {
        HistoryView historyView = new HistoryView();
        historyView.getFrame().setVisible(true);
    }

    private void queryTextActionPerformed(ActionEvent evt) {
    }

    private void pauseBtnActionPerformed(ActionEvent evt) {
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
    public String extractURLs(String text) {
        // Regular expression for detecting URLs
        String regex = "\\bhttps?://\\S+\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        String url = "";
        // Iterate through matches and print URLs
        while (matcher.find()) {
            url = matcher.group();
            System.out.println("URL: " + url);
        }
        return url;
    }
}
