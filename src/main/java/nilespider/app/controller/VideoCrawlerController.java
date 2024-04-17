package nilespider.app.controller;

import nilespider.app.model.Crawler;
import nilespider.app.model.EmailCrawler;
import nilespider.app.model.ImageCrawler;
import nilespider.app.model.VideoCrawler;
import nilespider.app.views.components.interfaces.AtomicComponents;

import java.util.HashSet;

public class VideoCrawlerController implements AtomicComponents {
    private Thread thread;
    private HashSet<String> visitedUrls;
    private String url, query;
    public VideoCrawlerController(String url){
        visitedUrls = new HashSet<>();
        thread = new Thread(new VideoCrawler(url, visitedUrls));
        this.url = url;
        this.query = query;
        thread.start();
    }
    public void stopThread(){
        thread.stop();
    }
    public void resetTread(){
        thread = new Thread(new VideoCrawler(url, visitedUrls));
    }
}
