package nilespider.app.utils.controllers;

import nilespider.app.Main;
import nilespider.app.utils.models.BasicCrawler;
import nilespider.app.utils.models.EmailCrawler;

import static nilespider.app.Main.queryText;
import static nilespider.app.Main.urlBar;

public class BasicCrawlerController extends Thread{
    public int crawlerValue = 0;
    public BasicCrawlerController(int crawlerValue)
    {
        this.crawlerValue = crawlerValue;
    }
    @Override
    public void run() {

       if (crawlerValue == 0)
       {
           BasicCrawler basicCrawler = new BasicCrawler(queryText.getText().toString(), urlBar.getText().toString());
           basicCrawler.crawl(urlBar.getText().toString());
       } else if (crawlerValue == 1) {
           EmailCrawler emailCrawler = new EmailCrawler(urlBar.getText().toString());
           emailCrawler.crawl(urlBar.getText().toString());
       }
    }


}
