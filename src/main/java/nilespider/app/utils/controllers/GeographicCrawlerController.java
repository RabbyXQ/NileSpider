package nilespider.app.utils.controllers;


import nilespider.app.Main;
import nilespider.app.utils.models.GeographicCrawler;

import static nilespider.app.Main.urlBar;

public class GeographicCrawlerController extends Thread {

    @Override
    public void run() {
        GeographicCrawler geographicCrawler = new GeographicCrawler(urlBar.getText().toString());
        geographicCrawler.crawl(urlBar.getText().toString());
    }
}
