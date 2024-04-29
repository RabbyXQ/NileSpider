package nilespider.app.controller;

import nilespider.app.model.Crawler;
import nilespider.app.model.EmailCrawler;
import nilespider.app.model.ImageCrawler;
import nilespider.app.model.PDFCrawler;
import nilespider.app.views.components.interfaces.AtomicComponents;

import java.util.HashSet;

public class ImageCrawlerController extends Thread implements AtomicComponents {
    private String url, query;
    public ImageCrawlerController(String url){
        super(new ImageCrawler(url, new HashSet<String>()));
        this.url = url;
    }

}

