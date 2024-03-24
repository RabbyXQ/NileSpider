package nilespider.app.utils.controllers;


import nilespider.app.Main;
import nilespider.app.utils.models.GeographicCrawler;

import java.util.HashSet;

import static nilespider.app.Main.urlBar;

public class GeographicCrawlerController extends Thread {

    @Override
    public void run() {
        HashSet<String> visitedUrls = new HashSet<>();
        GeographicCrawler geographicCrawler = new GeographicCrawler(urlBar.getText().toString(), visitedUrls);
    }
}
