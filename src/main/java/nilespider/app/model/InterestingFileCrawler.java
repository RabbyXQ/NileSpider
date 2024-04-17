package nilespider.app.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class InterestingFileCrawler extends Crawler {

    public InterestingFileCrawler(String baseUrl, HashSet<String> visitedUrls) {
        super(baseUrl, visitedUrls);
    }

    private void addInterestingFileToResultList(String url, Elements links){
        for (Element link : links) {
            String docUrl = link.absUrl("href");
            if (isInterestingFile(docUrl)){
                CRAWLING_MESSAGE_BUNDLE.updateUI(true, url, "Interesting File!");
            }
        }
    }

    public Elements documentToElements(Document document)
    {
        Elements links = document.select("a");
        return  links;
    }

    @Override
    protected boolean searchStringFound(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            addInterestingFileToResultList(url, documentToElements(document));
        } catch (IOException e) {
            CRAWLING_MESSAGE_BUNDLE.updateUIError(url);
        }
        return false;
    }

    private boolean isInterestingFile(String url) {
        return url.matches(".*\\.(php|config|ini|conf|txt|key|pem|cer|crt|pub)$");
    }

}
