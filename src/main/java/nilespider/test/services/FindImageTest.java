package nilespider.test.services;

import nilespider.app.model.ImageCrawler;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindImageTest implements TestInterface{

    ImageCrawler imageCrawler;
    String testUrl;

    @Before
    public void beforeTestCase(){
        if (!testResultList.isEmpty()){
            testResultList.clear();
        }
    }

    @Test
    public void Test1() {
        String testUrl = "https://gharoaa.com/test/imgs/index.html";
        imageCrawler = new ImageCrawler(testUrl, new HashSet<>());
        imageCrawler.crawl(testUrl);
        assertEquals( "No image links found on the page", true, testResultList.size() > 0);

    }

    @Test
    public void Test2(){
        String testUrl = "https://gharoaa.com/test/imgs/index.html";
        String[] expectedData = new String[]{
                "https://gharoaa.com/test/imgs/1.jpeg",
                "https://gharoaa.com/test/imgs/2.png",
                "https://gharoaa.com/test/imgs/3.png",
                "https://gharoaa.com/test/imgs/4.png"
        };
        ArrayList<String> expectedDataArrayList = new ArrayList<>();
        expectedDataArrayList.addAll(List.of(expectedData));
        imageCrawler = new ImageCrawler(testUrl, new HashSet<>());
        imageCrawler.crawl(testUrl);
        assertEquals( "Is not exactly same", expectedDataArrayList, testResultList);

    }

    @Test
    public void Test3(){
        String testUrl = "https://websitedosentexist";
        imageCrawler = new ImageCrawler(testUrl, new HashSet<>());
        imageCrawler.crawl(testUrl);
        assertEquals( "image links found on the page", true, testResultList.size() == 0);
    }
}
