package nilespider.app.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;

public class PhoneNumberCrawler extends Crawler {
    String phoneRegex = "\\+?[0-9]{1,3}-?[0-9]{4,14}";

    public PhoneNumberCrawler(String baseUrl, HashSet<String> visitedUrls) {
        super(baseUrl, visitedUrls);
    }


    private void addPhoneToResultList(Matcher matcher, String url){
        while (matcher.find()) {
            String phoneno = matcher.group();
            CRAWLING_MESSAGE_BUNDLE.updateUI(true, url, "Phone Number");
        }
    }


    @Override
    protected boolean searchStringFound(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            addPhoneToResultList(stringToMatcher(phoneRegex, document.text()), url);
        } catch (IOException e) {
            CRAWLING_MESSAGE_BUNDLE.updateUIError(url);
        }
        return false;
    }
}

