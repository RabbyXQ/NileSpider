/**
 * The class that organizes The Layout components to given sequence.
 * **/

package nilespider.app.views.main;

import javax.swing.*;

public class Layout extends LayoutComponents{
    public Layout(){
        super();
    }

    protected GroupLayout.Group initMainHorizontalGroup(GroupLayout layout) {
        GroupLayout.ParallelGroup mainVerticalGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        mainVerticalGroup.addGroup(layout.createSequentialGroup().addGroup(initMainGroupHorizontal(layout))
                .addContainerGap());
        mainVerticalGroup.addComponent(loadingBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        mainVerticalGroup.addGroup(initCrawlingMessageGroup(layout));
        return mainVerticalGroup;
    }

    protected GroupLayout.ParallelGroup initMainGroupHorizontal(GroupLayout layout) {
        GroupLayout.ParallelGroup mainGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        mainGroup.addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING);
        mainGroup.addGroup(initUrlBarHorizontalGroup(layout));
        mainGroup.addGroup(initGroupThreSliderComboBox(layout));
        mainGroup.addGroup(initButtonGroup(layout));
        return mainGroup;
    }

    protected GroupLayout.SequentialGroup initVerticalGroup(GroupLayout layout) {
        GroupLayout.SequentialGroup verticalGroup = layout.createSequentialGroup();
        verticalGroup.addComponent(loadingBar, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                .addGroup(initUrlBarVerticalGroup(layout))
                .addGroup(initThresholdGroupVertical(layout))
                .addGroup(initButtonGroupVertical(layout))
                .addComponent(crawlingMessage)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE);
        return verticalGroup;
    }
}
