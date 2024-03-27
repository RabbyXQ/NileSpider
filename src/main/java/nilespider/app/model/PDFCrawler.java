package nilespider.app.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class PDFCrawler extends Crawler {
    public PDFCrawler(String baseUrl, HashSet<String> visitedUrls) {
        super(baseUrl, visitedUrls);
    }

    private void addPDFToResultList(String url, Elements links){
        for (Element link : links) {
            String pdfUrl = link.absUrl("href");
            //crawlerUIUpdater.pdfFoundUpdateUI(PDF_FOUND, pdfUrl);
        }
    }

    public Elements documentToElementsPDFS(Document document)
    {
        Elements links = document.select("a[href$=.pdf]");
        return  links;
    }

    @Override
    protected boolean searchStringFound(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            addPDFToResultList(url, documentToElementsPDFS(document));
        } catch (IOException e) {
            //crawlerUIUpdater.updateUI(URL_NOT_VALID, "", false);
        }
        return false;
    }


}
