package nilespider.app.views.components;

import nilespider.app.views.components.interfaces.AtomicComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ThresholdSlider extends Panel implements AtomicComponents {

    public ThresholdSlider(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(initThresholdTextBundle());
        this.add(THRESHOLD_SLIDER);
        initThresholSlider();
    }

    public JPanel initThresholdTextBundle() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(THRESHOLD_TITLE, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(THRESHOLD_PERCENT, gbc);
        return panel;
    }

    private void initThresholSlider(){
        THRESHOLD_SLIDER.setBorder(new EmptyBorder(0, 0, 0, 10));
        updateUi();
    }

    private void changePercentange()
    {
        THRESHOLD_PERCENT.setText(THRESHOLD_SLIDER.getValue()+"%");
    }
    private void updateUi(){
        changePercentange();
        THRESHOLD_SLIDER.addChangeListener(e -> changePercentange());
    }

}
