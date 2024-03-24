package nilespider.app.utils.controllers;

import nilespider.app.utils.models.InterestingFileCrawler;
import nilespider.app.utils.models.VideoCrawler;

import java.util.HashSet;

import static nilespider.app.Main.urlBar;

public class InterestingFileCrawlerController extends Thread {

    @Override
    public void run() {
        HashSet<String> visitedUrls = new HashSet<>();
        InterestingFileCrawler interestingFileCrawler = new InterestingFileCrawler(urlBar.getText().toString(), visitedUrls);

    }
}
