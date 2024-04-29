package nilespider.app.views.components;

import nilespider.app.ui.pages.Downloads;
import nilespider.app.ui.pages.HistoryView;
import nilespider.app.views.components.interfaces.AtomicComponents;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LeftPanel extends JPanel implements AtomicComponents {
    private String[] buttonTitles = new String[]{"Save", "Copy", "Downloads", "History", "Help"};
    private JButton[] buttons = new JButton[5];

    public LeftPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(200, 208, 204, 255));
        setPreferredSize(new Dimension(100, getHeight()));
        initButtons();
        for (int i = 0; i < 5; i++) {
            this.add(buttons[i]);
        }
    }

    private void initButtons() {
        for (int i = 0; i < 5; i++) {
            buttons[i] = createButton(buttonTitles[i]);
        }
        buttonActions();
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setSize(100, getHeight());
        button.setBackground(Color.black);
        button.setFocusPainted(false);
        return button;
    }

    public void activeCurrentButton(int x) {
        for (int i = 0; i < 5; i++) {
            if (i == x) {
                buttons[i].setBackground(new Color(0, 0, 0, 103));
            } else {
                buttons[i].setBackground(null); // Reset background color for inactive buttons
            }
        }
    }

     void saveDataToFile(DefaultListModel<String> model) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".txt")) {
                filePath += ".txt"; // Ensure file has .txt extension
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (int i = 0; i < model.size(); i++) {
                    writer.write(model.getElementAt(i) + "\n");
                }
                JOptionPane.showMessageDialog(null, "Data saved successfully to:\n" + filePath);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving data to file:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void copyDataToClipboard(DefaultListModel<String> model) {
        StringBuilder dataBuilder = new StringBuilder();
        for (int i = 0; i < model.size(); i++) {
            dataBuilder.append(model.getElementAt(i)).append("\n");
        }
        StringSelection stringSelection = new StringSelection(dataBuilder.toString().trim());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        JOptionPane.showMessageDialog(null, "Data copied to clipboard successfully!");
    }


    void openDownloads(){
        downloads.getFrame().setVisible(true);
    }

    void openHistory(){
        historyView.getFrame().setVisible(true);
    }

    private void buttonActions() {
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (finalI == 2){
                        openDownloads();
                    }else if ( finalI == 0){
                        saveDataToFile(RESULT_LIST_MODEL);
                    } else if (finalI == 1) {
                        copyDataToClipboard(RESULT_LIST_MODEL);
                    } else if (finalI == 3) {
                        openHistory();
                    }
                }
            });
        }
    }

}
