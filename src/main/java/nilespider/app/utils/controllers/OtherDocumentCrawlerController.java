package nilespider.app.utils.controllers;

import nilespider.app.utils.models.OtherDocumentCrawler;
import nilespider.app.utils.models.VideoCrawler;

import static nilespider.app.Main.urlBar;

public class OtherDocumentCrawlerController extends Thread {

    @Override
    public void run() {
        OtherDocumentCrawler otherDocumentCrawler = new OtherDocumentCrawler(urlBar.getText().toString());
        otherDocumentCrawler.crawl(urlBar.getText().toString());
    }
}
