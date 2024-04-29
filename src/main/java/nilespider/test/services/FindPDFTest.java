package nilespider.test.services;

import nilespider.app.model.PDFCrawler;
import nilespider.app.views.components.interfaces.AtomicComponents;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class FindPDFTest implements AtomicComponents, TestInterface {
    PDFCrawler pdfCrawler;
    String testUrl;

    @Before
    public void beforeEachTest(){
        if (!testResultList.isEmpty()){
            testResultList.clear();
        }
    }

    @Test
    public void Test1() {
        testUrl = "https://www.sharedfilespro.com/download-sample-pdf/";
        pdfCrawler = new PDFCrawler(testUrl, new HashSet<String>());
        pdfCrawler.crawl(testUrl);
        Assert.assertEquals("List is empty", false, testResultList.isEmpty());

    }
    @Test
    public void Test2(){
        String[] expected = new String[]{
                "https://www.unm.edu/~unmvclib/powerpoint/powerpointtech.pdf",
                "https://www.unm.edu/~unmvclib/powerpoint/powerpointusing.pdf",
                "https://www.unm.edu/~unmvclib/powerpoint/reallybad.pdf"
        };
        ArrayList<String> expectedResult = new ArrayList<>(List.of(expected));

        testUrl = "https://www.unm.edu/~unmvclib/powerpoint/";
        pdfCrawler = new PDFCrawler(testUrl, new HashSet<String>());
        pdfCrawler.crawl(testUrl);
        System.out.println(testResultList);
        Assert.assertEquals("List is not matching", expectedResult, testResultList);
    }
    @Test
    public void Test3(){
        testUrl = "http://testphp.vulnweb.com/product.php?pic=1";
        pdfCrawler = new PDFCrawler(testUrl, new HashSet<String>());
        pdfCrawler.crawl(testUrl);
        Assert.assertEquals("List is empty", true, testResultList.isEmpty());

    }
}
