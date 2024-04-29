package nilespider.test.services;

import nilespider.app.model.Crawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class FindTextTest implements TestInterface{
    Crawler crawler;
    String testUrl;
    String query;

    @Before
    public void beforeEachTest()
    {
        if (!testResultList.isEmpty()) {
            testResultList.clear();
        }
    }

    @Test
    public void Test1(){
        testUrl = "https://bbhss.vercel.app";
        String[] expectedData = new String[]{
                "https://bbhss.vercel.app/downloads/book/howard-anto-lenear-algebra/",
                "https://bbhss.vercel.app/downloads/book"
        };
        ArrayList<String> arrayList = new ArrayList<String>(List.of(expectedData));
        query = "Howard";
        crawler = new Crawler(testUrl, query, new HashSet<>());
        crawler.crawl(testUrl);
        Collections.sort(arrayList);
        Collections.sort(testResultList);
        Assert.assertEquals("List Empty", arrayList, testResultList);
    }

    @Test
    public void Test2(){
        testUrl = "https://bbhss.vercel.app";
        query = "Abraca Dabra";
        crawler = new Crawler(testUrl, query, new HashSet<>());
        crawler.crawl(testUrl);
        Assert.assertEquals("List is not Empty", true, testResultList.size());
    }

}
