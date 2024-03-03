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

public class VideoCrawler {
    private Set<String> visitedUrls;
    private String baseUrl;

    public VideoCrawler(String baseUrl) {
        this.visitedUrls = new HashSet<>();
        this.baseUrl = baseUrl;
    }

    static int i = 0;

    public void crawl(String url) {
        if (!baseUrl.startsWith("http://") && !baseUrl.startsWith("https://")) {
            System.out.println("Invalid base URL. It should start with 'http://' or 'https://'.");
            return;
        }
        Main.loadingBar.setValue(i += 1);
        if (!visitedUrls.contains(url) && url.contains(baseUrl)) {
            visitedUrls.add(url);
            System.out.println("Crawling: " + url);
            Main.crawlingMessage.setText("Crawling: " + url);
            findVideos(url);

            Set<String> internalUrls = extractInternalUrls(url);
            Set<String> internalHyperlinks = extractInternalHyperlinks(url);

            for (String internalUrl : internalUrls) {
                crawl(internalUrl); // Recursively crawl internal URLs
            }

            for (String hyperlink : internalHyperlinks) {
                crawl(hyperlink); // Recursively crawl hyperlinks
            }
        }
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

    private Set<String> extractInternalUrls(String currentUrl) {
        Set<String> internalUrls = new HashSet<>();

        try {
            Document document = Jsoup.connect(currentUrl).get();
            Elements links = document.select("[href]");

            for (Element link : links) {
                String href = link.attr("href");
                // Remove the query part from URLs
                href = removeQuery(href);
                if (href.startsWith("/") || href.startsWith(baseUrl)) {
                    URL absoluteUrl = new URL(new URL(baseUrl), href);
                    internalUrls.add(absoluteUrl.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred while extracting internal URLs from " + currentUrl);
        }

        return internalUrls;
    }

    private Set<String> extractInternalHyperlinks(String currentUrl) {
        Set<String> internalHyperlinks = new HashSet<>();

        try {
            Document document = Jsoup.connect(currentUrl).get();
            Elements links = document.select("a[href]");

            for (Element link : links) {
                String href = link.attr("href");
                // Remove the query part from URLs
                href = removeQuery(href);
                if (href.startsWith("/") || href.startsWith(baseUrl)) {
                    URL absoluteUrl = new URL(new URL(baseUrl), href);
                    internalHyperlinks.add(absoluteUrl.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred while extracting internal hyperlinks from " + currentUrl);
        }

        return internalHyperlinks;
    }

    private String removeQuery(String url) {
        int index = url.indexOf('?');
        if (index != -1) {
            return url.substring(0, index);
        }
        return url;
    }

    private boolean isVideoUrl(String url) {
        // Check if the URL matches certain video file formats or sources
        return url.matches(".*\\.(mp4|avi|mov|flv|wmv|mkv|webm|mpeg|mpg|3gp|ogg|ogv|asf|qt|vob|swf|f4v)$") ||  // Match common video file extensions
                url.contains("youtube.com") ||         // YouTube URLs
                url.contains("vimeo.com") ||           // Vimeo URLs
                url.contains("instagram.com") ||       // Instagram URLs
                url.contains("vumoo.to") ||            // Vumoo URLs
                url.contains("dailymotion.com") ||     // Dailymotion URLs
                url.contains("metacafe.com") ||        // Metacafe URLs
                url.contains("veoh.com") ||            // Veoh URLs
                url.contains("twitch.tv") ||           // Twitch URLs
                url.contains("rutube.ru") ||           // Rutube URLs
                url.contains("vidio.com") ||           // Vidio URLs
                url.contains("viki.com") ||            // Viki URLs
                url.contains("crunchyroll.com") ||     // Crunchyroll URLs
                url.contains("funimation.com") ||      // Funimation URLs
                url.contains("netflix.com") ||         // Netflix URLs
                url.contains("hulu.com") ||            // Hulu URLs
                url.contains("amazon.com");            // Amazon Prime Video URLs
    }
}
