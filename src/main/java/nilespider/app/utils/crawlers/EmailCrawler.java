package nilespider.app.utils.crawlers;

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

public class EmailCrawler {
    private Set<String> visitedUrls;
    private ArrayList<String> foundEmails;
    private String baseUrl;

    public EmailCrawler(String baseUrl) {
        this.visitedUrls = new HashSet<>();
        this.foundEmails = new ArrayList<>();
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

            extractEmails(url);

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

    private void extractEmails(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String text = document.text();
            String[] words = text.split("\\s+");
            for (String word : words) {
                if (EmailValidator.validate(word)) {
                    foundEmails.add(word);
                    System.out.println("Email found: " + word);
                }
            }
        } catch (IOException e) {
            System.out.println("URL is not valid");
        }
    }

    public static void main(String[] args) {
        String baseUrl = "https://bbhss.vercel.app";

        EmailCrawler emailCrawler = new EmailCrawler(baseUrl);
        emailCrawler.crawl(baseUrl); // Start crawling from the base URL

        ArrayList<String> foundEmails = emailCrawler.getFoundEmails();
        System.out.println("Found Emails:");
        for (String email : foundEmails) {
            System.out.println(email);
        }
    }
}

class EmailValidator {
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static boolean validate(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
