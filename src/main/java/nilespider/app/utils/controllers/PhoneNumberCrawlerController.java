package nilespider.app.utils.controllers;

import nilespider.app.Main;
import nilespider.app.utils.models.PhoneNumberCrawler;

import java.util.HashSet;

import static nilespider.app.Main.urlBar;

public class PhoneNumberCrawlerController extends Thread {

    @Override
    public void run() {
        HashSet<String> visitedUrls = new HashSet<>();
        PhoneNumberCrawler phoneNumberCrawler = new PhoneNumberCrawler(urlBar.getText().toString(), visitedUrls);
    }
}
