package nilespider.app.controller;

import nilespider.app.model.Crawler;
import nilespider.app.views.components.interfaces.AtomicComponents;

import java.util.HashSet;

public class CrawlerController
        extends Thread implements AtomicComponents {
    protected Thread thread;
    protected String url, query;
    protected Runnable crawler;
    public CrawlerController(String url, String query){
        super(new Crawler(URL_BAR.getText().toString(), QUERY_BOX.getText().toString(), new HashSet<>()));
        this.url = url;
        this.query = query;
    }
    public void stopThread(){
        this.stop();
    }

}
