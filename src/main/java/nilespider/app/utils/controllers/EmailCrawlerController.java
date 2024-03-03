
package nilespider.app.utils.controllers;

import nilespider.app.Main;
import nilespider.app.utils.models.BasicCrawler;
import nilespider.app.utils.models.EmailCrawler;

import static nilespider.app.Main.queryText;
import static nilespider.app.Main.urlBar;

public class EmailCrawlerController extends Thread {

    @Override
    public void run() {

        EmailCrawler emailCrawler = new EmailCrawler(urlBar.getText().toString());
        emailCrawler.crawl(urlBar.getText().toString());
    }
}
