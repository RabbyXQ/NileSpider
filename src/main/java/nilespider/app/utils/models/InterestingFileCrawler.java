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

public class InterestingFileCrawler {
    private Set<String> visitedUrls;
    private String baseUrl;

    public InterestingFileCrawler(String baseUrl) {
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
            findDocuments(url);

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

    private void findDocuments(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.getElementsByTag("a");

            for (Element link : links) {
                String documentUrl = link.absUrl("href");
                if (isInterestingFile(documentUrl)) {
                    System.out.println("Found an interesting file at: " + documentUrl);
                    Main.crawlingMessage.setText("Found an interesting file!");
                    Main.listModel.addElement("Found interesting file: " + documentUrl);
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

    private boolean isInterestingFile(String url) {
        // Check if the URL matches certain file formats or sources containing sensitive information
        return url.matches(".*\\.(php|config|ini|conf|txt|key|pem|cer|crt|pub)$");
    }
}
