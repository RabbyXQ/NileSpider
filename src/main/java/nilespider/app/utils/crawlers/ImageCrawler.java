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

public class ImageCrawler {
    private Set<String> visitedUrls;
    private ArrayList<String> foundImages;
    private String baseUrl;

    public ImageCrawler(String baseUrl) {
        this.visitedUrls = new HashSet<>();
        this.foundImages = new ArrayList<>();
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

            extractImages(url);

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

    public ArrayList<String> getFoundImages() {
        return foundImages;
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

    private void extractImages(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements images = document.select("img[src]");

            for (Element image : images) {
                String imageUrl = image.attr("src");
                if (ImageValidator.validate(imageUrl)) {
                    foundImages.add(imageUrl);
                    System.out.println("Image found: " + imageUrl);
                }
            }
        } catch (IOException e) {
            System.out.println("URL is not valid");
        }
    }

    public static void main(String[] args) {
        String baseUrl = "https://bbhss.vercel.app";

        ImageCrawler imageCrawler = new ImageCrawler(baseUrl);
        imageCrawler.crawl(baseUrl); // Start crawling from the base URL

        ArrayList<String> foundImages = imageCrawler.getFoundImages();
        System.out.println("Found Images:");
        for (String imageUrl : foundImages) {
            System.out.println(imageUrl);
        }
    }
}

class ImageValidator {
    private static final String IMAGE_REGEX =
            "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    private static final Pattern pattern = Pattern.compile(IMAGE_REGEX);

    public static boolean validate(String imageUrl) {
        Matcher matcher = pattern.matcher(imageUrl);
        return matcher.matches();
    }
}
