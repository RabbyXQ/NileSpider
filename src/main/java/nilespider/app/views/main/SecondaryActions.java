package nilespider.app.views.main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondaryActions extends MainActions{
    public SecondaryActions(){
        super();
        thresholdAction();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loadingBar.hide();
        initComp();
    }


    private void initComp(){
        thresholdAction();
        stopBtn();
    }

    private void stopBtn(){
        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopCrawling();
            }
        });
    }

    private void thresholdAction(){
        thresholdSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int sliderValue = thresholdSlider.getValue();
                thresholdPercent.setText(sliderValue+"%");
            }
        });
    }

}
