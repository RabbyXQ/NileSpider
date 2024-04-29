package nilespider.app.controller;

import nilespider.app.model.Crawler;
import nilespider.app.model.PDFCrawler;
import nilespider.app.views.components.interfaces.AtomicComponents;

import java.util.HashSet;

public class TextCrawlerController extends Thread implements AtomicComponents {
    private String url, query;
    public TextCrawlerController(String url, String query){
        super(new Crawler(url, query, new HashSet<String>()));
        this.url = url;
    }

}

