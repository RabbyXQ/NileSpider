/**
 *This module is used to Extract weblinks from a webpage based.
 * **/

package nilespider.app.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLExtractor {
    private String baseUrl;
    public URLExtractor(String baseUrl)
    {
        this.baseUrl = baseUrl;
    }
    public URLExtractor() {

    }
    /**
     * Extracts all the internal webpage urls from given url
     *
     * **/
    public Set<String> extractInternalUrls(String currentUrl) {
        Set<String> internalUrls = new HashSet<>();
        try {
            Elements links = Jsoup.connect(currentUrl).get().select("[href]");
            internalUrls = this.constructInternalUrls(links);
        } catch (IOException e) {
            System.out.println("Error occured!");
        }
        return internalUrls;
    }
    /**
     * Extracts all the links from a single page or file and construct them as absolute urls.
     * **/
    public Set<String> constructInternalUrls(Elements links) throws MalformedURLException {
        Set<String> internalUrls = new HashSet<>();
        for (Element link : links) {
            String href = link.attr("href");
            if (isInternalLink(href)) {
                internalUrls.add(constructAbsoluteUrl(href));
            }
        }
        return internalUrls;
    }

    /**
     * This functions checks a hyperlink whether it is associated with the base link or not
     * **/
    public boolean isInternalLink(String href) {
        return href.startsWith("/") || href.startsWith(baseUrl);
    }

    /**
     * Creat a path like link to an absolute link.
     * This method converts /index.php to https://baseurl.com/index.php
     * **/
    public String constructAbsoluteUrl(String href) throws MalformedURLException {
        URL absoluteUrl = new URL(new URL(baseUrl), href);
        return absoluteUrl.toString();
    }

}