package nilespider.app.controller;

import nilespider.app.model.*;
import nilespider.app.views.components.interfaces.AtomicComponents;

import java.util.HashSet;

public class PhoneNumberCrawlerController implements AtomicComponents {
    private Thread thread;
    private HashSet<String> visitedUrls;
    private String url, query;
    public PhoneNumberCrawlerController(String url){
        visitedUrls = new HashSet<>();
        thread = new Thread(new PhoneNumberCrawler(url, visitedUrls));
        this.url = url;
        this.query = query;
        thread.start();
    }
    public void stopThread(){
        thread.stop();
    }
    public void resetTread(){
        thread = new Thread(new PhoneNumberCrawler(url, visitedUrls));
    }
}
