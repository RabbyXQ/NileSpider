package nilespider.app.controller;

import nilespider.app.model.*;
import nilespider.app.views.components.interfaces.AtomicComponents;

import java.util.HashSet;

public class VideoCrawlerController extends Thread implements AtomicComponents {
    private String url, query;
    public VideoCrawlerController(String url){
        super(new VideoCrawler(url, new HashSet<String>()));
        this.url = url;
    }

}

