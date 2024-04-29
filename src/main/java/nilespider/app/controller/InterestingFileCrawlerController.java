package nilespider.app.controller;

import nilespider.app.model.*;
import nilespider.app.views.components.interfaces.AtomicComponents;

import java.util.HashSet;

public class InterestingFileCrawlerController extends Thread implements AtomicComponents {
    private String url, query;
    public InterestingFileCrawlerController(String url){
        super(new InterestingFileCrawler(url, new HashSet<String>()));
        this.url = url;
    }

}
