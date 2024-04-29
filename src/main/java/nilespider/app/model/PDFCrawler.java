package nilespider.app.model;

import nilespider.app.views.components.interfaces.AtomicComponents;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class PDFCrawler extends Crawler implements AtomicComponents {

    public PDFCrawler(String baseUrl, HashSet<String> visitedUrls) {
        super(baseUrl, visitedUrls);
    }
    private void findPDf(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements links = document.select("a[href$=.pdf]");

        for (Element link : links) {
            String pdfUrl = link.absUrl("href");
            System.out.println("Found PDF: " + pdfUrl);
            testResultList.add(pdfUrl);
            CRAWLING_MESSAGE_BUNDLE.updateUI(true, pdfUrl, "PDF");
        }
    }


    @Override
    protected boolean searchStringFound(String url) {
        try {
            findPDf(url);
        } catch (IOException e) {
            System.out.println("Error");
            CRAWLING_MESSAGE_BUNDLE.updateUIError(url);
        }
        return false;
    }


}
