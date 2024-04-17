package nilespider.app.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;

public class GeographicCrawler extends Crawler {
    public GeographicCrawler(String baseUrl, HashSet<String> visitedUrls) {
        super(baseUrl, visitedUrls);
    }

    private void addGeoInfoToResultList(Matcher matcher, String url){
        while (matcher.find()) {
            String geoinfo = matcher.group();
            CRAWLING_MESSAGE_BUNDLE.updateUI(true, url, "Map");
        }
    }


    @Override
    protected boolean searchStringFound(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String geoRegex = "\\b(earth|map|address|location|place|geography|maps.google.com)\\b";
            addGeoInfoToResultList(stringToMatcher(geoRegex, document.text()), url);
        } catch (IOException e) {
            CRAWLING_MESSAGE_BUNDLE.updateUIError(url);
        }
        return false;
    }
}
