package nilespider.app.utils.controllers;

import nilespider.app.utils.models.BasicCrawler;
import java.util.HashSet;
import static nilespider.app.Main.queryText;
import static nilespider.app.Main.urlBar;

public class BasicCrawlerController extends Thread{

    @Override
    public void run() {
        HashSet<String> visitedUrls = new HashSet<>();
        BasicCrawler basicCrawler = new BasicCrawler(urlBar.getText().toString(), queryText.getText().toString(), visitedUrls);
    }


}
