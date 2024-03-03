package nilespider.app.utils.controllers;

import nilespider.app.utils.models.PDFCrawler;

import static nilespider.app.Main.urlBar;

public class PDFCrawlerController extends Thread {

    @Override
    public void run() {
        PDFCrawler pdfCrawler = new PDFCrawler(urlBar.getText().toString());
        pdfCrawler.crawl(urlBar.getText().toString());
    }
}
