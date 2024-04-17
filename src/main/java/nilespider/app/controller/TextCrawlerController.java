package nilespider.app.controller;

import nilespider.app.model.Crawler;
import nilespider.app.views.components.interfaces.AtomicComponents;

import java.util.HashSet;

public class TextCrawlerController implements AtomicComponents {
    private Thread thread;
    private HashSet<String> visitedUrls;
    private String url, query;
    public TextCrawlerController(String url, String query){
        visitedUrls = new HashSet<>();
        thread = new Thread(new Crawler(url, query, visitedUrls));
        this.url = url;
        this.query = query;
    }
}
