package nilespider.test.services;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConnectionTest {

    private Document connectAndGetDocument(String url) throws Exception {
        Connection connection = Jsoup.connect(url);
        return connection.get();
    }

    @Test
    public void testJsoupConnectionSuccess() {
        String[] urls = {
                "https://bbhss.vercel.app",
                "https://ku.ac.bd",
                "https://gharoaa.com"
        };

        for (String url : urls) {
            try {
                Document doc = connectAndGetDocument(url);
                assertNotNull(doc);
                System.out.println("Connected successfully to: " + url);
            } catch (Exception e) {
                fail("Failed to connect to the website " + url + ": " + e.getMessage());
            }
        }
    }

    @Test
    public void testJsoupConnectionFailure() {
        String url = "https://nonexistentwebsite.com";

        try {
            Document doc = connectAndGetDocument(url);
            fail("Expected an exception but connected successfully to a non-existent website.");
        } catch (Exception e) {
            assertTrue(e instanceof java.net.UnknownHostException);
        }
    }
}
