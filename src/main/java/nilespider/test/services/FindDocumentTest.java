package nilespider.test.services;

import nilespider.app.model.OtherDocumentCrawler;
import nilespider.app.model.PDFCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class FindDocumentTest implements TestInterface{

    OtherDocumentCrawler otherDocumentCrawler;
    String testUrl;

    @Before
    public void beforeEachTest(){
        if (!testResultList.isEmpty()){
            testResultList.clear();
        }
    }

    @Test
    public void Test1(){
        String[] expected = new String[]{
                "https://www.unm.edu/~unmvclib/powerpoint/powerpointguidelines.ppt",
                "https://www.unm.edu/~unmvclib/powerpoint/pptexamples.ppt",
                "https://www.unm.edu/~unmvclib/powerpoint/presentationstips.pptx"
        };
        ArrayList<String> expectedResult = new ArrayList<>(List.of(expected));

        testUrl = "https://www.unm.edu/~unmvclib/powerpoint/";
        otherDocumentCrawler = new OtherDocumentCrawler(testUrl, new HashSet<String>());
        otherDocumentCrawler.crawl(testUrl);
        Collections.sort(expectedResult);
        Collections.sort(testResultList);
        System.out.println(testResultList);
        Assert.assertEquals("List is not matching", expectedResult, testResultList);
    }
    @Test
    public void Test2(){
        testUrl = "http://testphp.vulnweb.com/product.php?pic=1";
        otherDocumentCrawler = new OtherDocumentCrawler(testUrl, new HashSet<String>());
        otherDocumentCrawler.crawl(testUrl);
        Assert.assertEquals("List is empty", true, testResultList.isEmpty());

    }
}
