package nilespider.app.model;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class OtherDocumentCrawler extends Crawler {

    public OtherDocumentCrawler(String baseUrl, HashSet<String> visitedUrls) {
        super(baseUrl, visitedUrls);
    }


    private void addOtherDocToResultList(String url, Elements links){
        for (Element link : links) {
            String docUrl = link.absUrl("href");
            if (isDocumentUrl(docUrl)){
                CRAWLING_MESSAGE_BUNDLE.updateUI(true, url, "Document/Not PDF");
            }
        }
    }

    public Elements documentToElementsPDFS(Document document)
    {
        Elements links = document.select("a");
        return  links;
    }

    @Override
    protected boolean searchStringFound(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            addOtherDocToResultList(url, documentToElementsPDFS(document));
        } catch (IOException e) {
            CRAWLING_MESSAGE_BUNDLE.updateUIError(url);
        }
        return false;
    }

    private boolean isDocumentUrl(String url) {
        return url.matches(".*\\.(txt|odt|doc|docx|dat|script|cs|c|cxx|cpp|exe|app|dmg|apk|apkx|sql|sqlite)$");  // Match common video file extensions

    }
}
