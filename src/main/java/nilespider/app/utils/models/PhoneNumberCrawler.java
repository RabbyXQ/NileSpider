package nilespider.app.utils.models;

import nilespider.app.Main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberCrawler {
    private Set<String> visitedUrls;
    private String baseUrl;
    private ArrayList<String> foundPhoneNumbers;

    public PhoneNumberCrawler(String baseUrl) {
        this.visitedUrls = new HashSet<>();
        this.baseUrl = baseUrl;
        this.foundPhoneNumbers = new ArrayList<>();
    }

    static int i = 0;
    public void crawl(String url) {
        if (!baseUrl.startsWith("http://") && !baseUrl.startsWith("https://")) {
            System.out.println("Invalid base URL. It should start with 'http://' or 'https://'.");
            return;
        }
        Main.loadingBar.setValue(i+=1);
        if (!visitedUrls.contains(url) && url.contains(baseUrl)) {
            visitedUrls.add(url);
            System.out.println("Crawling: " + url);
            Main.crawlingMessage.setText("Crawling: " + url);
            findPhoneNumbers(url);

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

    private void findPhoneNumbers(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String text = document.text();

            // Regular expression for finding phone numbers with country codes
            String phoneRegex = "\\+?[0-9]{1,3}-?[0-9]{4,14}";
            Pattern pattern = Pattern.compile(phoneRegex);
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                String phoneNumber = matcher.group();
                foundPhoneNumbers.add(phoneNumber);
                System.out.println("Found phone number: " + phoneNumber);
                Main.crawlingMessage.setText("Found phone number: " + phoneNumber);
                Main.listModel.addElement(phoneNumber+"\n"+url);
            }
        } catch (IOException e) {
            System.out.println("Error occurred while crawling " + url);
        }
    }

    public ArrayList<String> getFoundPhoneNumbers() {
        return foundPhoneNumbers;
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

