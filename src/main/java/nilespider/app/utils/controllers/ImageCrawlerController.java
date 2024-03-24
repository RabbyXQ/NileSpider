package nilespider.app.utils.controllers;

import nilespider.app.Main;
import nilespider.app.utils.models.ImageCrawler;

import java.util.HashSet;

import static nilespider.app.Main.urlBar;

public class ImageCrawlerController extends Thread {

    @Override
    public void run() {
        HashSet<String> visitedUrls = new HashSet<>();
        ImageCrawler imageCrawler = new ImageCrawler(urlBar.getText().toString(), visitedUrls);
    }
}
