package nilespider.app.ui.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryView implements Serializable {
    private JFrame frame;
    private JLabel historyLabel;
    private JButton clearButton;
    private DefaultListModel<String> listModel;
    private JList<String> historyList;
    private JScrollPane listScrollPane;

    public HistoryView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("History View");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close
        frame.setLayout(new BorderLayout());

        // History Label
        historyLabel = new JLabel("History");
        frame.add(historyLabel, BorderLayout.NORTH);

        // Initialize listModel
        listModel = new DefaultListModel<>();

        // History List
        historyList = new JList<>(listModel);

        loadHistory(); // Load history from file

        listScrollPane = new JScrollPane(historyList);
        frame.add(listScrollPane, BorderLayout.CENTER);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearHistory();
            }
        });
        frame.add(clearButton, BorderLayout.SOUTH);

        //frame.setVisible(true);
    }

    public void addToHistory(String item) {
        listModel.addElement(item);
        saveHistory(); // Save history after adding an item
    }

    private void clearHistory() {
        listModel.clear();
        saveHistory(); // Save history after clearing
    }

    private void loadHistory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("history.dat"))) {
            List<String> history = (List<String>) ois.readObject();
            for (String item : history) {
                listModel.addElement(item);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading history: " + e.getMessage());
        }
    }

    private void saveHistory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("history.dat"))) {
            List<String> history = new ArrayList<>();
            for (int i = 0; i < listModel.getSize(); i++) {
                history.add(listModel.getElementAt(i));
            }
            oos.writeObject(history);
        } catch (IOException e) {
            System.err.println("Error saving history: " + e.getMessage());
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HistoryView();
            }
        });
    }
}
