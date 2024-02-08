import java.util.*;
import java.net.*;
import java.io.*;

public class Crawler {
    private Set<String> visitedUrls;
    private String searchString;
    private ArrayList<String> foundUrls;
    private String baseUrl;

    public Crawler(String searchString, String baseUrl) {
        this.visitedUrls = new HashSet<>();
        this.searchString = searchString;
        this.foundUrls = new ArrayList<>();
        this.baseUrl = baseUrl;
    }

    public void crawl(String url) {
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

    public void crawl() {
        crawl(baseUrl); // Start crawling from the base URL
    }

    public ArrayList<String> getFoundUrls() {
        return foundUrls;
    }

    private Set<String> extractInternalUrls(String currentUrl) {
        Set<String> internalUrls = new HashSet<>();

        try {
            URL url = new URL(currentUrl);
            HttpURLConnection connection = openConnection(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("href=\"")) {
                    String href = line.split("href=\"")[1].split("\"")[0];
                    if (isValidUrl(href)) {
                        URL absoluteUrl = new URL(url, href);
                        internalUrls.add(absoluteUrl.toString());
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return internalUrls;
    }

    private Set<String> extractInternalHyperlinks(String currentUrl) {
        Set<String> internalHyperlinks = new HashSet<>();

        try {
            URL url = new URL(currentUrl);
            HttpURLConnection connection = openConnection(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("<a ") && line.contains("href=\"")) {
                    String href = line.split("href=\"")[1].split("\"")[0];
                    if (isValidUrl(href)) {
                        URL absoluteUrl = new URL(url, href);
                        internalHyperlinks.add(absoluteUrl.toString());
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return internalHyperlinks;
    }

    private boolean searchStringFound(String url) {
        try {
            URL targetUrl = new URL(url);
            HttpURLConnection connection = openConnection(targetUrl);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(searchString)) {
                        reader.close();
                        return true;
                    }
                }

                reader.close();
            } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println("Page not found: " + url);
            } else {
                System.out.println("Failed to retrieve content from " + url + ". Response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean isValidUrl(String url) {
        return url.startsWith("/") || url.startsWith(baseUrl);
    }

    private HttpURLConnection openConnection(URL url) throws IOException {
        if (url.getProtocol().equalsIgnoreCase("https")) {
            return (HttpURLConnection) url.openConnection();
        } else {
            return (HttpURLConnection) url.openConnection();
        }
    }

    public static void main(String[] args) {
        String baseUrl = "https://bbhss.vercel.app";
        String searchString = "01925";

        Crawler crawler = new Crawler(searchString, baseUrl);
        crawler.crawl();

        ArrayList<String> foundUrls = crawler.getFoundUrls();
        System.out.println("Found URLs:");
        for (String foundUrl : foundUrls) {
            System.out.println(foundUrl);
        }
    }
}
