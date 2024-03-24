package nilespider.app.utils.models;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class VideoCrawler extends Crawler{

    public VideoCrawler(String baseUrl, HashSet<String> visitedUrls) {
        super(baseUrl, visitedUrls);
    }


    private void addVideoToResultList(String url, Elements videos){
        for (Element video : videos) {
            String videoUrl = video.absUrl("src");
            if (isVideoUrl(videoUrl)) {
                crawlerUIUpdater.videoFoundUpdateUI(VIDEO_FOUND, videoUrl);
            }
        }

    }

    public Elements documentToElementsVideos(Document document)
    {
        Elements videos = document.getElementsByAttribute("ref");
        videos.addAll(document.getElementsByAttribute("href"));
        videos.addAll(document.getElementsByAttribute("src"));
        return  videos;
    }

    @Override
    protected boolean searchStringFound(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            addVideoToResultList(url, documentToElementsVideos(document));
        } catch (IOException e) {
            crawlerUIUpdater.updateUI(URL_NOT_VALID, "", false);
        }
        return false;
    }


    private boolean hasVideoExtention(String url) {
        String videoExtensionsRegex = ".*\\.(mp4|avi|mov|flv|wmv|mkv|webm|mpeg|mpg|3gp|ogg|ogv|asf|qt|vob|swf|f4v)$";
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
