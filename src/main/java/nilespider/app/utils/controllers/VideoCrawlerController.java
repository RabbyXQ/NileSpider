package nilespider.app.utils.controllers;

import nilespider.app.utils.models.VideoCrawler;

import java.util.HashSet;

import static nilespider.app.Main.urlBar;

public class VideoCrawlerController extends Thread {

    @Override
    public void run() {
        HashSet<String> visitedUrls = new HashSet<>();
        VideoCrawler videoCrawler = new VideoCrawler(urlBar.getText().toString(), visitedUrls);
    }
}
