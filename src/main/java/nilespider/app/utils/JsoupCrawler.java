package nilespider.app.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class JsoupCrawler{
    private Set<String> visitedUrls;

    private String searchString;
    private ArrayList<String> foundUrls;
    private String baseUrl;

    public JsoupCrawler(String searchString, String baseUrl) {
        this.visitedUrls = new HashSet<>();
        this.searchString = searchString;
        this.foundUrls = new ArrayList<>();
        this.baseUrl = baseUrl;
    }

    public void crawl(String url) {
        if (!baseUrl.startsWith("http://") && !baseUrl.startsWith("https://")) {
            System.out.println("Invalid base URL. It should start with 'http://' or 'https://'.");
            return;
        }

        if (!visitedUrls.contains(url) && url.contains(baseUrl)) {
            visitedUrls.add(url);
            System.out.println("Crawling: " + url);

            if (searchStringFound(url)) {
                System.out.println("String found on: " + url);
                foundUrls.add(url);
            }

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

    public ArrayList<String> getFoundUrls() {
        return foundUrls;
    }

    private Set<String> extractInternalUrls(String currentUrl) {
        Set<String> internalUrls = new HashSet<>();

        try {
            Document document = Jsoup.connect(currentUrl).get();
            Elements links = document.select("[href]");

            for (Element link : links) {
                String href = link.attr("href");
                if (href.startsWith("/") || href.startsWith(baseUrl)) {
                    URL absoluteUrl = new URL(new URL(baseUrl), href);
                    internalUrls.add(absoluteUrl.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error Occurred");
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
                if (href.startsWith("/") || href.startsWith(baseUrl)) {
                    URL absoluteUrl = new URL(new URL(baseUrl), href);
                    internalHyperlinks.add(absoluteUrl.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("URL is not valid");
        }

        return internalHyperlinks;
    }

    private boolean searchStringFound(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String text = document.text();

            if (text.contains(searchString)) {
                return true;
            }
        } catch (IOException e) {
            System.out.println("URL is not valid");
        }

        return false;
    }

    public static void main(String[] args) {
        String baseUrl = "https://gharoaa.com";
        String searchString = "Email";

        JsoupCrawler jsoupCrawler = new JsoupCrawler(searchString, baseUrl);
        jsoupCrawler.crawl(baseUrl); // Start crawling from the base URL

        ArrayList<String> foundUrls = jsoupCrawler.getFoundUrls();
        System.out.println("Found URLs:");
        for (String foundUrl : foundUrls) {
            System.out.println(foundUrl);
        }
    }
}
