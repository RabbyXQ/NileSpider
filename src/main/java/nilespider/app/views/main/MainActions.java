package nilespider.app.views.main;

import nilespider.app.ui.pages.History;

import java.io.File;

public class MainActions extends StylizeLayout{
    public MainActions(){
        super();
        initActions();
    }

    private void initActions(){
        historyActions();
    }

    private void historyActions(){
        History.loadHistory("history.dat");
        if (new File("history.dat").exists()) {
            History.loadHistory("history.dat");
        } else {
            History.saveHistory("history.dat");
        }
        History.loadHistory("history.dat");
        History.addHistory("URL: " + urlBar.getText().toString() + "Query: " + query);
        History.saveHistory("history.dat");
    }
}
