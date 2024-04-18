package nilespider.test.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class CrawlLinksTest {

    private Elements extractLinks(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.select("a[href]");
    }

    @Test
    public void testExtractLinksSuccess() {
        String[] urls = {
                "https://bbhss.vercel.app",
                "https://gharoaa.com",
                "https://ku.ac.bd"
        };

        for (String url : urls) {
            try {
                Elements links = extractLinks(url);
                assertNotNull(links);
                assertFalse(links.isEmpty());
                System.out.println("Extracted " + links.size() + " links from " + url);
            } catch (IOException e) {
                fail("Failed to extract links from " + url + ": " + e.getMessage());
            }
        }
    }

    @Test
    public void testExtractLinksFailure() {
        String url = "https://nonexistentwebsite.com";
        try {
            Elements links = extractLinks(url);
            fail("Expected an IOException but extracted links successfully from a non-existent website.");
        } catch (IOException e) {
            assertNotNull(e.getMessage());
        }
    }
}
