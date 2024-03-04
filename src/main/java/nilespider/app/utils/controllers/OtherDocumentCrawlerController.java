package nilespider.app.utils.controllers;

import nilespider.app.utils.models.VideoCrawler;

import static nilespider.app.Main.urlBar;

public class OtherDocumentCrawlerController extends Thread {

    @Override
    public void run() {
        VideoCrawler videoCrawler = new VideoCrawler(urlBar.getText().toString());
        videoCrawler.crawl(urlBar.getText().toString());
    }
}
