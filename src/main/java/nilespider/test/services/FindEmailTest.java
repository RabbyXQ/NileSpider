package nilespider.test.services;

import nilespider.app.model.EmailCrawler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindEmailTest implements TestInterface{

    EmailCrawler emailCrawler;
    String testUrl;

    @Before
    public void beforeTestCases(){
        if (!testResultList.isEmpty()){
            testResultList.clear();
        }
    }

    @Test
    public void Test() {
      testUrl = "https://gharoaa.com/test/email.html";
      String[] expectedData = new String[]{
              "email@example.com",
              "firstname.lastname@example.com",
              "email@subdomain.example.com",
              "firstname+lastname@example.com",
              "email@example.com"
      };
      emailCrawler = new EmailCrawler(testUrl, new HashSet<>());
      emailCrawler.crawl(testUrl);
      Assert.assertEquals("Empty", new ArrayList<String>(List.of(expectedData)), testResultList);
    }
}
