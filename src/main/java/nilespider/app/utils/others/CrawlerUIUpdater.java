package nilespider.app.utils.others;

import nilespider.app.Main;

import static nilespider.app.Main.listModel;

public class CrawlerUIUpdater {
    private int LOADING_BAR_VALUE = 1;
    public void updateUI(String message, String url, boolean found)
    {
        if(url.length() > 0){
            Main.loadingBar.setValue(LOADING_BAR_VALUE++);
        }
        Main.crawlingMessage.setText(message+ url);
        if (found) {
            listModel.addElement(url.toString().toLowerCase());
        }
    }

    public void geoInfoFoundUpdateUI(String message, String url, String geoinfo){
        Main.crawlingMessage.setText(message+geoinfo);
        Main.loadingBar.setValue(LOADING_BAR_VALUE++);
        listModel.addElement(message+geoinfo+"\n"+url.toString().toLowerCase());
    }
    public void imageFoundUpdateUI(String message, String url) {
        Main.crawlingMessage.setText(message+url);
        Main.loadingBar.setValue(LOADING_BAR_VALUE++);
        listModel.addElement(message+url.toString().toLowerCase());
    }
    public void phoneNoFoundUpdateUI(String message, String url, String phoneNo){
        Main.crawlingMessage.setText(message+phoneNo);
        Main.loadingBar.setValue(LOADING_BAR_VALUE++);
        listModel.addElement(message+phoneNo+"\n"+url.toString().toLowerCase());
    }

    public void emailFoundUpdateUI(String message, String url, String email)
    {
        Main.crawlingMessage.setText(message+email);
        Main.loadingBar.setValue(LOADING_BAR_VALUE++);
        listModel.addElement(message+email+"\n"+url.toString().toLowerCase());
    }

    public void crawlingDone(){
        if (LOADING_BAR_VALUE < 100) {
            Main.loadingBar.setValue(100);
        }
        Main.crawlingMessage.setText("Crawling process has been finished.");
    }
}
