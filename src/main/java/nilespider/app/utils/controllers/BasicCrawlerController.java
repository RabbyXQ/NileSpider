package nilespider.app.utils.controllers;

import nilespider.app.Main;
import nilespider.app.utils.models.BasicCrawler;

import static nilespider.app.Main.queryText;
import static nilespider.app.Main.urlBar;

public class BasicCrawlerController extends Thread{

    @Override
    public void run() {

        BasicCrawler basicCrawler = new BasicCrawler(queryText.getText().toString(), urlBar.getText().toString());
        basicCrawler.crawl(urlBar.getText().toString());
    }


}
