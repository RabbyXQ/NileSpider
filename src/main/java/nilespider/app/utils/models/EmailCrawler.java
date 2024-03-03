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

import static nilespider.app.Main.listModel;

public class EmailCrawler {
    private Set<String> visitedUrls;

    private String baseUrl;
    private ArrayList<String> foundEmails;

    public EmailCrawler(String baseUrl) {
        this.visitedUrls = new HashSet<>();
        this.baseUrl = baseUrl;
        this.foundEmails = new ArrayList<>();
    }

    static int i = 1;

    public void crawl(String url) {
        if (!baseUrl.startsWith("http://") && !baseUrl.startsWith("https://")) {
            System.out.println("Invalid base URL. It should start with 'http://' or 'https://'.");
            return;
        }

        if (!visitedUrls.contains(url) && url.contains(baseUrl)) {
            visitedUrls.add(url);
            Main.loadingBar.setValue(i += 1);
            Main.crawlingMessage.setText("Crawling: " + url);
            System.out.println("Crawling: " + url);

            findEmails(url);

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

    private void findEmails(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String text = document.text();

            // Split the text by space, newlines, and common email delimiters
            String[] tokens = text.split("[\\s@.]+");

            for (String token : tokens) {
                if (isEmail(token)) {
                    foundEmails.add(token);
                    Main.crawlingMessage.setText("Email " + token+"\n"+" has been fount at url: "+ url);
                    listModel.addElement("Email: " + token+"\n"+"Url: "+ url);
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while crawling " + url);
        }
    }

    public ArrayList<String> getFoundEmails() {
        return foundEmails;
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
    public boolean isEmail(String email) {
        // Regular expression for a valid email address
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
