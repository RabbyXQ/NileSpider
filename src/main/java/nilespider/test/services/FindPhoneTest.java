package nilespider.test.services;

import nilespider.app.model.PhoneNumberCrawler;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindPhoneTest implements TestInterface{

    PhoneNumberCrawler phoneNumberCrawler;
    String testUrl;

    @Before
    public void beforeEachTest()
    {
        if (!testResultList.isEmpty()){
            testResultList.clear();
        }
    }


    @Test
    public void Test() {
        String[] expectedData = new String[]{
                "01757948842",
                "01911655249",
                "01846216032",
                "01720394971",
                "01712962646",
                "01924804870",
                "01688627987",
                "01718152278",
                "01716631978",
                "01712616185",
        };
        ArrayList<String> expectedList = new ArrayList<>(List.of(expectedData));
        String testUrl = "https://www.noakhalicoll.gov.bd/teacher_list_department.php?department=POLITICAL%20SCIENCE";
        phoneNumberCrawler = new PhoneNumberCrawler(testUrl, new HashSet<>());
        phoneNumberCrawler.crawl(testUrl);
        assertEquals("No phone numbers found on the page", expectedList, testResultList);
    }

}
