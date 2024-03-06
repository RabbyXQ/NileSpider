package nilespider.app.ui.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryView {
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

        // History List

        historyList = new JList<>(listModel);
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

        frame.setVisible(true);
    }

    public void addToHistory(String item) {
        listModel.addElement(item);
    }

    private void clearHistory() {
        listModel.clear();
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
