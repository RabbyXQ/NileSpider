package nilespider.app.model;

import nilespider.app.views.components.interfaces.AtomicComponents;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class VideoCrawler extends Crawler implements AtomicComponents {

    public VideoCrawler(String baseUrl, HashSet<String> visitedUrls) {
        super(baseUrl, visitedUrls);
    }

    private void findVideos(String url) throws IOException {
        Document document = Jsoup.connect(url).get();

        // Check for video links in anchor tags
        Elements videoLinks = document.select("a[href*=youtube.com], a[href*=vimeo.com], a[href*=dailymotion.com]");
        extractAndProcessVideos(videoLinks);

        // Check for video formats directly embedded in the page
        Elements videoEmbeds = document.select("video > source[src]");
        extractAndProcessVideos(videoEmbeds);

        // Check for videos embedded in iframes
        Elements iframeVideos = document.select("iframe[src*=youtube.com], iframe[src*=vimeo.com], iframe[src*=dailymotion.com]");
        extractAndProcessVideos(iframeVideos);
    }

    private void extractAndProcessVideos(Elements elements) {
        for (Element element : elements) {
            String videoUrl = element.absUrl("src");
            System.out.println("Found Video: " + videoUrl);
            testResultList.add(videoUrl);
            CRAWLING_MESSAGE_BUNDLE.updateUI(true, videoUrl, "Video");
        }
    }

    @Override
    protected boolean searchStringFound(String url) {
        try {
            findVideos(url);
        } catch (IOException e) {
            System.out.println("Error crawling URL: " + url);
            CRAWLING_MESSAGE_BUNDLE.updateUIError(url);
        }
        return false;
    }
}
