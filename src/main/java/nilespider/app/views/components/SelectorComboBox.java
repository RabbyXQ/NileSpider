package nilespider.app.views.components;

import nilespider.app.controller.TextCrawlerController;
import nilespider.app.views.components.interfaces.AtomicComponents;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectorComboBox extends Panel
        implements AtomicComponents {
    public SelectorComboBox() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(SELECT_TYPE_TITLE, gbc);
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(SELECTOR_COMBO_BOX, gbc);
        initComboBox();
    }

    private void initComboBox(){
        SELECT_TYPE_TITLE.setBorder(new EmptyBorder(0, 5, 0, 0));
        SELECTOR_COMBO_BOX.setModel(SELECTOR_COMBOBOX_MODEL);
        SELECTOR_COMBO_BOX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SELECTOR_COMBO_BOX.getSelectedIndex() > 0){QUERY_BOX.enable(false); QUERY_BOX.setText("Not Available");}else{ QUERY_BOX.enable(true); QUERY_BOX.setText("Enter query");}
            }
        });
    }

}
