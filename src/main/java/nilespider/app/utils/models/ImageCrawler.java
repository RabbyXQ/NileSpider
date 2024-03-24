package nilespider.app.utils.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class ImageCrawler extends Crawler{

    public ImageCrawler(String baseUrl, HashSet<String> visitedUrls) {
            super(baseUrl, visitedUrls);
    }

    private void addImageToResultList(String url, Elements images){
        for (Element image : images) {
            String imageUrl = image.absUrl("src");
            crawlerUIUpdater.imageFoundUpdateUI(IMAGE_FOUND, imageUrl);
        }

    }

    @Override
    protected boolean searchStringFound(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements images = document.getElementsByTag("img");
            addImageToResultList(url, images);
        } catch (IOException e) {
            crawlerUIUpdater.updateUI(URL_NOT_VALID, "", false);
        }
        return false;
    }


}
