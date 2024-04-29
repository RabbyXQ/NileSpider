package nilespider.test.services;

import nilespider.app.model.VideoCrawler;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindVideoTest implements TestInterface{
    VideoCrawler videoCrawler;
    String testUrl;

    @Test
    public void Test1() {
        testUrl = "https://rabbyxq.blogspot.com/";
        String[] links = new String[]{
                "https://www.youtube.com/embed/LYmDxPxGSrM?feature=player_embedded",
                "https://www.youtube.com/embed/tIGnbH4qIjY?feature=player_embedded",
        };
        ArrayList<String> expected = new ArrayList<>(List.of(links));
        Collections.sort(expected);
        HashSet<String> expectedHashSet = new HashSet<>(expected);
        videoCrawler = new VideoCrawler(testUrl, new HashSet<String>());
        videoCrawler.crawl(testUrl);
        Collections.sort(testResultList);
        HashSet<String> actualDataSet = new HashSet<>(testResultList);
        Assert.assertEquals("Not matching", expectedHashSet, actualDataSet);
    }

}
