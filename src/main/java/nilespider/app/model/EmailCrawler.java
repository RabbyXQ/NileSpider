package nilespider.app.model;

import nilespider.app.views.components.interfaces.AtomicComponents;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;

public class EmailCrawler extends Crawler implements AtomicComponents {
    public EmailCrawler(String baseUrl, HashSet<String> visitedUrls) {
        super(baseUrl, visitedUrls);
    }

    private void addEmailToResultList(Matcher matcher, String url){
        while (matcher.find()) {
            String email = matcher.group();
            CRAWLING_MESSAGE_BUNDLE.updateUI(true, url, "Email");
        }
    }

    @Override
    protected boolean searchStringFound(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
            addEmailToResultList(stringToMatcher(emailRegex, document.text()), url);
        } catch (IOException e) {
            CRAWLING_MESSAGE_BUNDLE.updateUIError(url);
        }
        return false;
    }

}
