package nilespider.app.views.main;

import javax.swing.*;

public class LayoutComponents extends Skeleton{
    public LayoutComponents()
    {
        super();
    }

    protected GroupLayout.SequentialGroup initCrawlingMessageGroup(GroupLayout layout) {
        GroupLayout.SequentialGroup crawlingMessageGroup = layout.createSequentialGroup();
        crawlingMessageGroup.addContainerGap();
        crawlingMessageGroup.addComponent(crawlingMessage);
        crawlingMessageGroup.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        return crawlingMessageGroup;
    }

    protected GroupLayout.SequentialGroup initButtonGroup(GroupLayout layout) {
        GroupLayout.SequentialGroup buttonGroup = layout.createSequentialGroup();
        buttonGroup.addContainerGap();
        buttonGroup.addComponent(jLabel1);
        buttonGroup.addGap(369);
        buttonGroup.addComponent(pauseBtn, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE);
        buttonGroup.addComponent(stopBtn);
        buttonGroup.addComponent(visualizeBtn);
        buttonGroup.addComponent(historyBtn, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE);
        return buttonGroup;
    }

    protected GroupLayout.Group initButtonGroupVertical(GroupLayout layout) {
        GroupLayout.ParallelGroup buttonGroup = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        buttonGroup.addComponent(jLabel1)
                .addComponent(pauseBtn)
                .addComponent(stopBtn)
                .addComponent(visualizeBtn)
                .addComponent(historyBtn);
        return buttonGroup;
    }

    GroupLayout.SequentialGroup initGroupThreSliderComboBox(GroupLayout layout){
        GroupLayout.SequentialGroup sequentialGroup = layout.createSequentialGroup();
        sequentialGroup.addComponent(optionSelectorComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        sequentialGroup.addGap(106);
        sequentialGroup.addComponent(jLabel2);
        sequentialGroup.addComponent(thresholdPercent);
        sequentialGroup.addComponent(thresholdSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        return sequentialGroup;
    }

    protected GroupLayout.Group initThresholdGroupVertical(GroupLayout layout) {
        GroupLayout.ParallelGroup thresholdGroup = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        thresholdGroup.addComponent(optionSelectorComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2)
                .addComponent(thresholdPercent)
                .addComponent(thresholdSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        return thresholdGroup;
    }


    protected GroupLayout.SequentialGroup initUrlBarHorizontalGroup(GroupLayout layout){
        GroupLayout.SequentialGroup urlBarGroup = layout.createSequentialGroup();
        urlBarGroup.addComponent(urlBar, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE);
        urlBarGroup.addComponent(jLabel3);
        urlBarGroup.addComponent(queryText);
        urlBarGroup.addComponent(crawlBtn, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE);
        return urlBarGroup;
    }

    protected GroupLayout.ParallelGroup initUrlBarVerticalGroup(GroupLayout layout){
        GroupLayout.ParallelGroup urlBarGroup = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        urlBarGroup.addComponent(urlBar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE);
        urlBarGroup.addComponent(jLabel3);
        urlBarGroup.addComponent(queryText);
        urlBarGroup.addComponent(crawlBtn, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE);
        return urlBarGroup;
    }

}
