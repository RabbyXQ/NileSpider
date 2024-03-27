package nilespider.app.views.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftPanel extends JPanel {
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

    private void buttonActions() {
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    activeCurrentButton(finalI);
                    System.out.println(finalI);
                }
            });
        }
    }

}
