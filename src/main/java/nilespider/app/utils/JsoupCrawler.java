package nilespider.app.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class JsoupCrawler {
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

    public void crawl() {
        if (!baseUrl.startsWith("http://") && !baseUrl.startsWith("https://")) {
            System.out.println("Invalid base URL. It should start with 'http://' or 'https://'.");
            return;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(baseUrl);

        while (!queue.isEmpty()) {
            String currentUrl = queue.poll();

            if (!visitedUrls.contains(currentUrl) && currentUrl.contains(baseUrl)) {
                visitedUrls.add(currentUrl);
                System.out.println("Crawling: " + currentUrl);

                if (searchStringFound(currentUrl)) {
                    System.out.println("String found on: " + currentUrl);
                    foundUrls.add(currentUrl);
                }

                Set<String> internalUrls = extractInternalUrls(currentUrl);
                Set<String> internalHyperlinks = extractInternalHyperlinks(currentUrl);

                queue.addAll(internalUrls);
                queue.addAll(internalHyperlinks);
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
            System.out.println("Error Occured");
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
        String baseUrl = "https://vjghs.edu.bd";
        String searchString = "Name";

        JsoupCrawler jsoupCrawler = new JsoupCrawler(searchString, baseUrl);
        jsoupCrawler.crawl();

        ArrayList<String> foundUrls = jsoupCrawler.getFoundUrls();
        System.out.println("Found URLs:");
        for (String foundUrl : foundUrls) {
            System.out.println(foundUrl);
        }
    }
}
