package nilespider.app.utils.views.components;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends Panel {
    public CenterPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        Panel topPanel = topPanel();
        this.add(topPanel, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        this.add(new ResultListPane(), gbc);
    }

    public Panel topPanel() {
        Panel panel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new SelectorComboBox(), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.ipadx = 100;
        panel.add(new ThresholdSlider(), gbc);
        return panel;
    }


}
