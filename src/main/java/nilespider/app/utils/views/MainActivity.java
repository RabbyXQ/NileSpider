package nilespider.app.utils.views;

import nilespider.app.utils.views.components.TopPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The main activity class representing the application's main window.
 */
public class MainActivity extends JFrame {
    private BorderLayout mainLayout;
    private JPanel leftPanel;
    private TopPanel topPanel;
    private JPanel centerPanel;

    /**
     * Constructor to initialize the main activity.
     */
    MainActivity() {
        initMainLayout();
        setSize(800, 600); // Set initial size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
    }

    /**
     * Initialize the main layout of the frame.
     */
    private void initMainLayout(){
        initPanels();
        mainLayout = new BorderLayout();
        setLayout(mainLayout); // Set the layout manager
        add(leftPanel, BorderLayout.WEST); // Add left panel to the west
        add(topPanel, BorderLayout.NORTH); // Add top panel to the north
        add(centerPanel, BorderLayout.CENTER); // Add center panel to the center
    }

    /**
     * Initialize all panels.
     */
    public void initPanels(){
        initLeftPanel();
        initTopPanel();
        initCenterPanel();
    }

    /**
     * Initialize the left panel.
     */
    private void initLeftPanel(){
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(8, 119, 161, 147)); // Set background color
        leftPanel.setPreferredSize(new Dimension(50, getHeight())); // Set preferred size
    }

    /**
     * Initialize the top panel.
     */
    private void initTopPanel(){
        topPanel = new TopPanel();
    }

    /**
     * Initialize the center panel.
     */
    private void initCenterPanel(){
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE); // Set background color
    }

    /**
     * The main method to start the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String args[]) {
        MainActivity mainActivity = new MainActivity();
        mainActivity.setVisible(true);
    }
}
