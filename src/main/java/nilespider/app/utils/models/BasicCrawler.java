package nilespider.app.utils.models;

import java.util.HashSet;


public class BasicCrawler extends Crawler{

    public BasicCrawler(String baseUrl, String searchString, HashSet<String> visitedUrls) {
        super(baseUrl, searchString, visitedUrls);
    }

}
