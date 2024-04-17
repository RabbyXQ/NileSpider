package nilespider.test.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

public class FindTextTest {

    String HTML = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>The Magical Forest</title>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: Arial, sans-serif;\n" +
            "            margin: 20px;\n" +
            "            line-height: 1.6;\n" +
            "        }\n" +
            "\n" +
            "        h1 {\n" +
            "            text-align: center;\n" +
            "            color: #339966;\n" +
            "        }\n" +
            "\n" +
            "        p {\n" +
            "            margin-bottom: 10px;\n" +
            "        }\n" +
            "\n" +
            "        .highlight {\n" +
            "            font-weight: bold;\n" +
            "            color: #FF9900;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <h1>The Magical Forest</h1>\n" +
            "\n" +
            "    <p>Once upon a time, in a <span class=\"highlight\">magical forest</span> far, far away, there lived a curious young elf named Elara. Elara was known for her adventurous spirit and her love for exploring the mysteries of the forest.</p>\n" +
            "\n" +
            "    <p>One bright morning, as Elara was wandering through the ancient trees, she stumbled upon a hidden path that she had never noticed before. Excited by the prospect of a new adventure, she followed the path deeper into the heart of the forest.</p>\n" +
            "\n" +
            "    <p>Along the way, Elara encountered <span class=\"highlight\">talking animals</span> who shared tales of the forest's secrets. She met wise old owls, mischievous squirrels, and even a friendly bear who taught her the importance of kindness and bravery.</p>\n" +
            "\n" +
            "    <p>As the sun began to set, Elara reached a <span class=\"highlight\">magical waterfall</span> shimmering with colors she had never seen before. Mesmerized by its beauty, she sat by the waterfall, listening to its soothing melody.</p>\n" +
            "\n" +
            "    <p>By the time Elara returned home, she was filled with memories of her magical journey. From that day on, she became known as the <span class=\"highlight\">Forest Explorer</span>, sharing her adventures with other elves and creatures of the forest.</p>\n" +
            "\n" +
            "    <p>The end.</p>\n" +
            "</body>\n" +
            "</html>\n";

    @BeforeClass
    public static void testStart(){
        System.out.println("Test Start." + new Date());
    }


    String findTextFromHtml(String query){
        Document document = Jsoup.parse(HTML);
        String[] queryParts = query.split(" ");
        for (String q: queryParts){
            if (document.text().toLowerCase().contains(q.toLowerCase())) return "Found";
        }
        return "Not Found";
    }

    @Test
    public void Test1(){
        String desired = "Found";
        String result = findTextFromHtml("Elara");
        Assert.assertEquals(desired, result);
    }

    @Test
    public void Test2(){
        String desired = "Found";
        String result = findTextFromHtml("Animal");
        Assert.assertEquals(desired, result);
    }

    @Test
    public void Test3(){
        String desired = "Found";
        String result = findTextFromHtml("Elara forest");
        Assert.assertEquals(desired, result);
    }

    @Test
    public void Test4(){
        String desired = "Not Found";
        String result = findTextFromHtml("Khulna");
        Assert.assertEquals(desired, result);
    }

    @Test
    public void Test5(){
        String desired = "Not Found";
        String result = findTextFromHtml("Khulna");
        Assert.assertEquals(desired, result);
    }

}
