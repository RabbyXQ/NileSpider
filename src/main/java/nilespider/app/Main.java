package nilespider.app;

import nilespider.app.ui.pages.History;
import nilespider.app.ui.pages.HistoryView;
import nilespider.app.utils.controllers.*;
import nilespider.app.utils.models.InterestingFileCrawler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends javax.swing.JFrame {
    public JButton crawlBtn;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        urlBar.setFont(new java.awt.Font("Helvetica", 0, 13));
        urlBar.setText("https://");
        urlBar.setActionCommand("<Not Set>");
        urlBar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 3, true));
        {
            visualizeBtn.hide();
            pauseBtn.hide();
            stopBtn.hide();
        }
        crawlBtn.setFont(new java.awt.Font("Helvetica", 0, 13)); // NOI18N
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

        jLabel1.setForeground(new java.awt.Color(51, 153, 0));
        jLabel1.setText("Select Data Type");

        jLabel2.setForeground(new java.awt.Color(102, 153, 0));
        jLabel2.setText("Threshold");

        thresholdPercent.setForeground(new java.awt.Color(51, 153, 0));
        thresholdPercent.setText("50%");

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

        queryText.setBorder(new LineBorder(new java.awt.Color(0, 153, 153), 3, true));
        queryText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                queryTextActionPerformed(evt);
            }
        });

        jLabel3.setText("Query");

        crawlingMessage.setForeground(new java.awt.Color(51, 204, 0));

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
        jMenuBar1.add(downloadsMenuItem);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(urlBar, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel3))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(optionSelectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(queryText)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(crawlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(106, 106, 106)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel2)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(thresholdPercent))
                                                                        .addComponent(thresholdSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1)
                                                .addGap(369, 369, 369)
                                                .addComponent(pauseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(stopBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(visualizeBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(historyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                        .addComponent(loadingBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(crawlingMessage)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loadingBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(crawlBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                                .addComponent(urlBar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(queryText))
                                        .addComponent(jLabel3))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(jLabel1)
                                                .addGap(4, 4, 4))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(pauseBtn)
                                                        .addComponent(visualizeBtn)
                                                        .addComponent(stopBtn)
                                                        .addComponent(historyBtn))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(thresholdPercent))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(thresholdSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(optionSelectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(crawlingMessage)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
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

    private void visualizeBtnActionPerformed(java.awt.event.ActionEvent evt) {

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
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

}
