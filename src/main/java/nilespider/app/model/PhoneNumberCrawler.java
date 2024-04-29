package nilespider.app.model;

import nilespider.test.services.TestInterface;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;

public class PhoneNumberCrawler extends Crawler implements TestInterface {
    String phoneRegex = "\\+?[0-9]{1,3}-?[0-9]{4,14}";

    public PhoneNumberCrawler(String baseUrl, HashSet<String> visitedUrls) {
        super(baseUrl, visitedUrls);
    }

    public boolean isValidNumber(String str){
        int length = str.length();
        if (str.startsWith("0")){
            length = str.length() - 1;
        }
        if (length < 10 || length > 10){
            return false;
        }
        return true;
    }

    private void addPhoneToResultList(Matcher matcher, String url){
        while (matcher.find()) {
            String phoneno = matcher.group();
            if (isValidNumber(phoneno)){
                CRAWLING_MESSAGE_BUNDLE.updateUI(true, url, "Phone Number");
                testResultList.add(phoneno);
            }

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

