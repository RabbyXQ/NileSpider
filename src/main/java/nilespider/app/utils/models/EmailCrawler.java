package nilespider.app.utils.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;

public class EmailCrawler extends Crawler{
    public EmailCrawler(String baseUrl, HashSet<String> visitedUrls) {
        super(baseUrl, visitedUrls);
    }

    private void addEmailToResultList(Matcher matcher, String url){
        while (matcher.find()) {
            String email = matcher.group();
            crawlerUIUpdater.emailFoundUpdateUI(EMAIL_FOUND, url, email);
        }
    }

    @Override
    protected boolean searchStringFound(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
            addEmailToResultList(stringToMatcher(emailRegex, document.text()), url);
        } catch (IOException e) {
            crawlerUIUpdater.updateUI(URL_NOT_VALID, "", false);
        }
        return false;
    }

}
