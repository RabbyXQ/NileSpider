package nilespider.app.utils.controllers;

import nilespider.app.Main;
import nilespider.app.utils.models.ImageCrawler;

import static nilespider.app.Main.urlBar;

public class ImageCrawlerController extends Thread {

    @Override
    public void run() {
        ImageCrawler imageCrawler = new ImageCrawler(urlBar.getText().toString());
        imageCrawler.crawl(urlBar.getText().toString());
    }
}
