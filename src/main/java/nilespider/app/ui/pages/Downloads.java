package nilespider.app.ui.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Downloads {
    private JFrame frame;
    private JLabel downloadsLabel;
    private JButton clearButton;
    private DefaultListModel<DownloadItem> listModel;
    private JList<DownloadItem> downloadsList;
    private JScrollPane listScrollPane;

    public Downloads() {
        initialize();
        loadDownloads("downloads.dat");

        // Add window listener to save downloads when the application is closed
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveDownloads("downloads.dat");
            }
        });
    }

    private void initialize() {
        frame = new JFrame("Downloads");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close
        frame.setLayout(new BorderLayout());

        // Downloads Label
        downloadsLabel = new JLabel("Downloads");
        frame.add(downloadsLabel, BorderLayout.NORTH);

        // Initialize listModel
        listModel = new DefaultListModel<>();

        // Downloads List
        downloadsList = new JList<>(listModel);
        downloadsList.setCellRenderer(new DownloadListRenderer());
        downloadsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = downloadsList.locationToIndex(e.getPoint());
                    DownloadItem downloadItem = listModel.getElementAt(index);
                    openDownloadedFile(downloadItem);
                }
            }
        });
        listScrollPane = new JScrollPane(downloadsList);
        frame.add(listScrollPane, BorderLayout.CENTER);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearDownloads();
                saveDownloads("downloads.dat");
            }
        });
        frame.add(clearButton, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.dispose();
    }

    public void addToDownloads(String url, String saveDir) {
        DownloadItem downloadItem = new DownloadItem(url, saveDir);
        listModel.addElement(downloadItem);
        new Thread(() -> {
            try {
                downloadFile(downloadItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void clearDownloads() {
        listModel.clear();
    }

    private void downloadFile(DownloadItem downloadItem) throws IOException {
        String saveDir = downloadItem.getSaveDir();
        File directory = new File(saveDir);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory and any necessary parent directories
        }

        URL url = new URL(downloadItem.getUrl());
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");

            if (disposition != null) {
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10, disposition.length() - 1);
                }
            } else {
                fileName = downloadItem.getUrl().substring(downloadItem.getUrl().lastIndexOf("/") + 1);
            }

            String saveFilePath = saveDir + File.separator + fileName;
            long fileSize = httpConn.getContentLengthLong();

            InputStream inputStream = httpConn.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            byte[] buffer = new byte[4096];
            int bytesRead;
            long totalBytesRead = 0;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                int progress = (int) (totalBytesRead * 100 / fileSize);
                downloadItem.setProgress(progress);
                SwingUtilities.invokeLater(() -> listModel.setElementAt(downloadItem, listModel.indexOf(downloadItem)));
            }

            outputStream.close();
            inputStream.close();

            downloadItem.setProgress(100); // Set progress to 100% when download completes
            downloadItem.setDownloaded(true); // Mark as downloaded
            SwingUtilities.invokeLater(() -> listModel.setElementAt(downloadItem, listModel.indexOf(downloadItem)));

        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }

    private void openDownloadedFile(DownloadItem downloadItem) {
        if (downloadItem.isDownloaded()) {
            String saveFilePath = downloadItem.getSaveDir() + File.separator + downloadItem.getFileName();
            try {
                Desktop.getDesktop().open(new File(saveFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadDownloads(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            ArrayList<DownloadItem> downloadItems = (ArrayList<DownloadItem>) inputStream.readObject();
            listModel.clear(); // Clear existing items
            for (DownloadItem item : downloadItems) {
                listModel.addElement(item); // Add items to the list model
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveDownloads(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            ArrayList<DownloadItem> downloadItems = new ArrayList<>();
            for (int i = 0; i < listModel.getSize(); i++) {
                downloadItems.add(listModel.getElementAt(i));
            }
            outputStream.writeObject(downloadItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Downloads downloads = new Downloads();
            //downloads.addToDownloads("https://assets.ctfassets.net/31lakm8hkl1j/3WKmQ14C8oqmlyccCX9AcT/46157f1e58409ff3bbdaf1e456d4f3c7/All_deo_kuhulz_zone_20230917_0001.pdf__23.pdf", "downloads");
        });
    }

    class DownloadItem implements Serializable {
        private static final long serialVersionUID = 1L;
        private String url;
        private String saveDir;
        private int progress;
        private boolean downloaded;
        private String fileName;

        public DownloadItem(String url, String saveDir) {
            this.url = url;
            this.saveDir = saveDir;
            this.progress = 0;
            this.downloaded = false;
            this.fileName = url.substring(url.lastIndexOf("/") + 1);
        }

        public String getUrl() {
            return url;
        }

        public String getSaveDir() {
            return saveDir;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public boolean isDownloaded() {
            return downloaded;
        }

        public void setDownloaded(boolean downloaded) {
            this.downloaded = downloaded;
        }

        public String getFileName() {
            return fileName;
        }

        @Override
        public String toString() {
            return (downloaded ? "Downloaded: " : "Downloading: ") + fileName;
        }
    }

    class DownloadListRenderer extends DefaultListCellRenderer {
        private Map<Integer, JProgressBar> progressBarMap = new HashMap<>();

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            DownloadItem downloadItem = (DownloadItem) value;
            int progress = downloadItem.getProgress();
            JProgressBar progressBar = progressBarMap.computeIfAbsent(index, k -> new JProgressBar(0, 100));
            progressBar.setValue(progress);
            progressBar.setString(progress + "%");
            progressBar.setStringPainted(true);
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(label, BorderLayout.WEST);
            if (!downloadItem.isDownloaded()) {
                panel.add(progressBar, BorderLayout.EAST);
            }
            return panel;
        }
    }
}