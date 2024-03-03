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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeographicCrawler {
    private Set<String> visitedUrls;
    private String baseUrl;

    public GeographicCrawler(String baseUrl) {
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
            findGeographicTerms(url);

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

    private void findGeographicTerms(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String text = document.text();

            // Regular expression for finding geographic terms
            String geoRegex = "\\b(earth|map|address|location|place|geography)\\b";
            Pattern pattern = Pattern.compile(geoRegex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                String term = matcher.group();
                System.out.println("Found geographic term: " + term);
                Main.crawlingMessage.setText("Found geographic term: " + term);
                Main.listModel.addElement(term + "\n" + url);
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
}
