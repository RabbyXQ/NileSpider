package nilespider.app.utils.controllers;

import nilespider.app.utils.models.InterestingFileCrawler;
import nilespider.app.utils.models.VideoCrawler;

import static nilespider.app.Main.urlBar;

public class InterestingFileCrawlerController extends Thread {

    @Override
    public void run() {
        InterestingFileCrawler interestingFileCrawler = new InterestingFileCrawler(urlBar.getText().toString());
        interestingFileCrawler.crawl(urlBar.getText().toString());
    }
}
