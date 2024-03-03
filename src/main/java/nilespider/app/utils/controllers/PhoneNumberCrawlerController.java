package nilespider.app.utils.controllers;

import nilespider.app.Main;
import nilespider.app.utils.models.PhoneNumberCrawler;

import static nilespider.app.Main.urlBar;

public class PhoneNumberCrawlerController extends Thread {

    @Override
    public void run() {
        PhoneNumberCrawler phoneNumberCrawler = new PhoneNumberCrawler(urlBar.getText().toString());
        phoneNumberCrawler.crawl(urlBar.getText().toString());
    }
}
