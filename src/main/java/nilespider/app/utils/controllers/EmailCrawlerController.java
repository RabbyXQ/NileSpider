
package nilespider.app.utils.controllers;

import nilespider.app.Main;
import nilespider.app.utils.models.BasicCrawler;
import nilespider.app.utils.models.EmailCrawler;

import java.util.HashSet;

import static nilespider.app.Main.queryText;
import static nilespider.app.Main.urlBar;

public class EmailCrawlerController extends Thread {

    @Override
    public void run() {
        HashSet<String> visitedUrls = new HashSet<>();
        EmailCrawler emailCrawler = new EmailCrawler(urlBar.getText().toString(), visitedUrls);
    }
}
