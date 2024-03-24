package nilespider.app.utils.controllers;

import nilespider.app.utils.models.OtherDocumentCrawler;

import java.util.HashSet;

import static nilespider.app.Main.urlBar;

public class OtherDocumentCrawlerController extends Thread {

    @Override
    public void run() {
        HashSet<String> visitedUrls = new HashSet<>();
        OtherDocumentCrawler otherDocumentCrawler = new OtherDocumentCrawler(urlBar.getText().toString(), visitedUrls);
    }
}
