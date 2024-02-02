package nilespider.app.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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
            URL url = new URL(currentUrl);
            HttpURLConnection connection = openConnection(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("href=\"")) {
                    String href = line.split("href=\"")[1].split("\"")[0];
                    if (href.startsWith("/") || href.startsWith(baseUrl)) {
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
                    if (href.startsWith("/") || href.startsWith(baseUrl)) {
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
