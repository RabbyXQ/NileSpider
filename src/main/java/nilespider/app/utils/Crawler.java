package nilespider.app.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {

    private HashSet<String> visitedUrls;
    private ArrayList<String> allVisitedUrls;
    private ArrayList<String> matchingWebpages;
    private String baseUrl;
    private String query;

    public Crawler(String url, String query) {
        baseUrl = getBaseUrl(url);
        visitedUrls = new HashSet<>();
        allVisitedUrls = new ArrayList<>();
        matchingWebpages = new ArrayList<>();
        this.query = query;
        crawl(url);
    }

    public void crawl(String url) {
        if (!visitedUrls.contains(url) && url.contains(baseUrl)) {
            try {
                visitedUrls.add(url);
                allVisitedUrls.add(url);

                URLConnection connection = new URL(url).openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder html = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    html.append(line);
                }
                reader.close();

                String htmlString = html.toString();

                if (htmlString.contains(query)) {
                    matchingWebpages.add(url);
                }

                Pattern pattern = Pattern.compile("<a\\s+href\\s*=\\s*\"(.*?)\"");
                Matcher matcher = pattern.matcher(htmlString);
                while (matcher.find()) {
                    String link = matcher.group(1);

                    // Normalize the link
                    URL normalizedUrl = new URL(new URL(url), link);
                    String normalizedLink = normalizedUrl.toString();

                    // Ensure the normalized link contains the base URL
                    if (normalizedLink.contains(baseUrl)) {
                        crawl(normalizedLink);
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage() + "\n" + e.toString());
            }
        }
    }

    public ArrayList<String> getAllVisitedUrls() {
        return allVisitedUrls;
    }

    public ArrayList<String> getMatchingWebpages() {
        return matchingWebpages;
    }

    private String getBaseUrl(String url) {
        try {
            URL parsedUrl = new URL(url);
            return parsedUrl.getProtocol() + "://" + parsedUrl.getHost();
        } catch (Exception e) {
            return "";
        }
    }

    public static void main(String args[]) {
        Crawler crawler = new Crawler("https://sites.google.com/site/manishankarmondalwebsite", "IWSC 2016");

        for (String s : crawler.visitedUrls) {
            System.out.println(s);
        }

        System.out.println("Matching Webpages:");
        ArrayList<String> matchingWebpages = crawler.getMatchingWebpages();
        for (String s : matchingWebpages) {
            System.out.println(s);
        }
    }
}
