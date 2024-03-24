package nilespider.app.utils.models;

import nilespider.app.Main;
import nilespider.app.utils.controllers.BasicCrawlerController;
import nilespider.app.utils.others.URLExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static nilespider.app.Main.listModel;


public class BasicCrawler extends Crawler{

    public BasicCrawler(String baseUrl, String searchString, HashSet<String> visitedUrls) {
        super(baseUrl, searchString, visitedUrls);
    }

}
