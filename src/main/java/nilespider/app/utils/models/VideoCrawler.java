package nilespider.app.utils.models;


import nilespider.app.Main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class VideoCrawler extends Crawler{
    String videoExtensionsRegex = ".*\\.(mp4|avi|mov|flv|wmv|mkv|webm|mpeg|mpg|3gp|ogg|ogv|asf|qt|vob|swf|f4v)$";

    public VideoCrawler(String baseUrl, HashSet<String> visitedUrls) {
        super(baseUrl, visitedUrls);
    }


    private void findVideos(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements videos = document.getElementsByTag("video");

            for (Element video : videos) {
                String videoUrl = video.absUrl("src");
                if (isVideoUrl(videoUrl)) {
                    System.out.println("Found video: " + videoUrl);
                    Main.crawlingMessage.setText("Found video: " + videoUrl);
                    Main.listModel.addElement("Found video: " + videoUrl);
                    // Add further processing here if needed
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while crawling " + url);
        }
    }

    private boolean hasVideoExtention(String url) {
        if (url.matches(videoExtensionsRegex)) {
            return true;
        }
        return false;
    }

    private boolean hasVideoDomain(String url) {
        for (String domain : VIDEO_DOMAINS) {
            if (url.contains(domain)) {
                return true;
            }
        }
        return false;
    }

    private boolean isVideoUrl(String url) {
        return  hasVideoDomain(url) || hasVideoExtention(url);
    }
}
