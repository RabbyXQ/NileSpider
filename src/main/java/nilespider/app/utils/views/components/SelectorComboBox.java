package nilespider.app.utils.views.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SelectorComboBox extends Panel implements AtomicComponents{
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
    }

}
