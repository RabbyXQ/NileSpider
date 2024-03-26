package nilespider.app.utils.views;

import nilespider.app.utils.views.components.BottomPanel;
import nilespider.app.utils.views.components.CenterPanel;
import nilespider.app.utils.views.components.LeftPanel;
import nilespider.app.utils.views.components.TopPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The main activity class representing the application's main window.
 */
public class MainActivity extends JFrame {
    private BorderLayout mainLayout;
    private LeftPanel leftPanel;
    private TopPanel topPanel;
    public CenterPanel centerPanel;
    private BottomPanel bottomPanel;

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
        setLayout(mainLayout);
        add(leftPanel, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Initialize all panels.
     */
    public void initPanels(){
        initLeftPanel();
        initTopPanel();
        initCenterPanel();
        initBottomPanel();
    }

    /**
     * Initialize the left panel.
     */
    private void initLeftPanel(){
        leftPanel = new LeftPanel();
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
        centerPanel = new CenterPanel();
    }

    private void initBottomPanel(){
        bottomPanel = new BottomPanel();
    }

    public void setCenterPanel(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
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
