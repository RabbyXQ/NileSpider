package nilespider.app.controller;

import nilespider.app.model.Crawler;
import nilespider.app.model.EmailCrawler;
import nilespider.app.model.GeographicCrawler;
import nilespider.app.model.PDFCrawler;
import nilespider.app.views.components.interfaces.AtomicComponents;

import java.util.HashSet;
public class GeographicCrawlerController extends Thread implements AtomicComponents {
        private String url, query;
        public GeographicCrawlerController(String url){
            super(new GeographicCrawler(url, new HashSet<String>()));
            this.url = url;
        }

}
