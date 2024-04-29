package nilespider.app.controller;

import nilespider.app.model.*;
import nilespider.app.views.components.interfaces.AtomicComponents;

import java.util.HashSet;

public class PhoneNumberCrawlerController extends Thread implements AtomicComponents {
    private String url, query;
    public PhoneNumberCrawlerController(String url){
        super(new PhoneNumberCrawler(url, new HashSet<String>()));
        this.url = url;
    }

}

