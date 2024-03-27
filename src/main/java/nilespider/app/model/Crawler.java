/**
 * Base Class For Crawler that inherits all the classes that provide Crawling Mechanism
 * **/

package nilespider.app.model;

import nilespider.app.views.components.interfaces.AtomicComponents;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler
        implements AtomicComponents, Runnable {
    protected String baseUrl;
    protected Set<String> visitedUrls;
    protected String searchString;
    protected  int loadingValue = 0;
    public Crawler(String baseUrl, HashSet<String> visitedUrls)
    {
        this.baseUrl = baseUrl;
        this.visitedUrls = new HashSet<>();
        crawl(baseUrl);
    }

    public Crawler(String baseUrl, String searchString, HashSet<String> visitedUrls)
    {
        this.baseUrl = baseUrl;
        this.visitedUrls = visitedUrls;
        this.searchString = searchString;
    }


    public void crawl(String url) {
        if (!(baseUrl.startsWith("http://") || baseUrl.startsWith("https://"))) {
            return;
        }
        if (!visitedUrls.contains(url) && url.contains(baseUrl)) {
            visitedUrls.add(url);
            CRAWLING_MESSAGE_BUNDLE.updateUI(false, url, QUERY_BOX.getText().toString());
            LOADING_BAR.setValue(loadingValue++);
            checkAndProcessUrl(url);
        }
    }

    public void checkAndProcessUrl(String url) {
        if (searchStringFound(url)) {
            CRAWLING_MESSAGE_BUNDLE.updateUI(true, url, QUERY_BOX.getText().toString());
        }
        URLExtractor urlExtractor = new URLExtractor(baseUrl);
        Set<String> internalUrls = urlExtractor.extractInternalUrls(url);
        for (String internalUrl : internalUrls) {
            crawl(internalUrl);
        }
        LOADING_BAR.setValue(100);
        CRAWLING_MESSAGE_BUNDLE.updateUiOnDone();
    }

    protected Matcher stringToMatcher(String regex, String text)
    {
        return Pattern.compile(regex).matcher(text);
    }

    protected boolean searchStringFound(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String[] searchWords = searchString.toString().toLowerCase().split(" ");
            int found = 0, total = 1;
            for (String word : searchWords) {
                if (document.text().toLowerCase().contains(word)) {
                    found++;
                }
                total++;
            }
            if (((double)found/total)*100>=10){
                return true;
            }
            return false;
        } catch (IOException e) {
            CRAWLING_MESSAGE_BUNDLE.updateUIError(url);
            return false;
        }
    }


    @Override
    public void run() {
        crawl(baseUrl);
    }
}
