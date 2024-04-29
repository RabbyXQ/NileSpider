package nilespider.app.model;

import nilespider.test.services.TestInterface;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class ImageCrawler extends Crawler implements TestInterface {

    public ImageCrawler(String baseUrl, HashSet<String> visitedUrls) {
            super(baseUrl, visitedUrls);
    }

    private void addImageToResultList(String url, Elements images){
        for (Element image : images) {
            String imageUrl = image.absUrl("src");
            testResultList.add(imageUrl);
            System.out.println("Found Image: "+ imageUrl);
            CRAWLING_MESSAGE_BUNDLE.updateUI(true, url, "Image");
        }

    }

    @Override
    protected boolean searchStringFound(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements images = document.getElementsByTag("img");
            addImageToResultList(url, images);
        } catch (IOException e) {
            CRAWLING_MESSAGE_BUNDLE.updateUIError(url);
        }
        return false;
    }


}
