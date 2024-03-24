package nilespider.app.utils.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;

public class GeographicCrawler extends Crawler{
    public GeographicCrawler(String baseUrl, HashSet<String> visitedUrls) {
        super(baseUrl, visitedUrls);
    }

    private void addGeoInfoToResultList(Matcher matcher, String url){
        while (matcher.find()) {
            String geoinfo = matcher.group();
            crawlerUIUpdater.geoInfoFoundUpdateUI(GEO_INFO_FOUND, url, geoinfo);
        }
    }


    @Override
    protected boolean searchStringFound(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String geoRegex = "\\b(earth|map|address|location|place|geography|maps.google.com)\\b";
            addGeoInfoToResultList(stringToMatcher(geoRegex, document.text()), url);
        } catch (IOException e) {
            crawlerUIUpdater.updateUI(URL_NOT_VALID, "", false);
        }
        return false;
    }
}
