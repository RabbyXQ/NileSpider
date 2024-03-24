package nilespider.app.utils.models;

import nilespider.app.utils.others.CrawlerInterface;
import nilespider.app.utils.others.CrawlerUIUpdater;
import nilespider.app.utils.others.URLExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler implements CrawlerInterface {
    protected String baseUrl;
    protected Set<String> visitedUrls;
    protected String searchString;
    protected CrawlerUIUpdater crawlerUIUpdater;

    public Crawler(String baseUrl, HashSet<String> visitedUrls)
    {
        this.baseUrl = baseUrl;
        this.crawlerUIUpdater = new CrawlerUIUpdater();
        this.visitedUrls = new HashSet<>();
        crawl(baseUrl);
    }

    public Crawler(String baseUrl, String searchString, HashSet<String> visitedUrls)
    {
        this.baseUrl = baseUrl;
        this.visitedUrls = visitedUrls;
        this.searchString = searchString;
        this.crawlerUIUpdater = new CrawlerUIUpdater();
        crawl(baseUrl);
    }


    public void crawl(String url) {
        if (!(baseUrl.startsWith("http://") || baseUrl.startsWith("https://"))) {
            return;
        }
        if (!visitedUrls.contains(url) && url.contains(baseUrl)) {
            visitedUrls.add(url);
            crawlerUIUpdater.updateUI(CRAWLING_TEXT, url, false);
            checkAndProcessUrl(url);
        }
    }

    public void checkAndProcessUrl(String url) {
        if (searchStringFound(url)) {
            crawlerUIUpdater.updateUI(FOUND_TEXT, url, true);
        }
        URLExtractor urlExtractor = new URLExtractor(baseUrl);
        Set<String> internalUrls = urlExtractor.extractInternalUrls(url);
        for (String internalUrl : internalUrls) {
            crawl(internalUrl);
        }
        crawlerUIUpdater.crawlingDone();
    }

    protected Matcher stringToMatcher(String regex, String text)
    {
        return Pattern.compile(regex).matcher(text);
    }

    protected boolean searchStringFound(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            if (document.text().contains(searchString)) {
                return true;
            }
        } catch (IOException e) {
            crawlerUIUpdater.updateUI(URL_NOT_VALID, "", false);
        }
        return false;
    }
}
