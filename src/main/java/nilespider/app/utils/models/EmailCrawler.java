package nilespider.app.utils.models;

import nilespider.app.Main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailCrawler extends Crawler{
    private String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
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
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(document.text());
            addEmailToResultList(matcher, url);
        } catch (IOException e) {
            crawlerUIUpdater.updateUI(URL_NOT_VALID, "", false);
        }
        return false;
    }

}
