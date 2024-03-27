package nilespider.app.views.components;

import nilespider.app.views.components.interfaces.AtomicComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class TopPanel extends JPanel implements AtomicComponents {
    private BufferedImage backgroundImage;

    public TopPanel() {
        try {
            backgroundImage = ImageIO.read(new File("/Users/macbook/Desktop/background_top.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new URLBarBundle());
        this.add(LOADING_BAR);
        LOADING_BAR.hide();
        initPanelUI();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void initPanelUI() {
        this.setPreferredSize(new Dimension(getWidth(), 70));
        LOADING_BAR.setBorder(new EmptyBorder(0, 10, 0, 10));
    }
}
