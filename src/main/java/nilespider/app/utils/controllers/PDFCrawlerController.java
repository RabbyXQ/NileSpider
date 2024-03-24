package nilespider.app.utils.controllers;

import nilespider.app.utils.models.PDFCrawler;

import java.util.HashSet;

import static nilespider.app.Main.urlBar;

public class PDFCrawlerController extends Thread {

    @Override
    public void run() {
        HashSet<String> visitedUrls = new HashSet<>();
        PDFCrawler pdfCrawler = new PDFCrawler(urlBar.getText().toString(), visitedUrls);
    }
}
