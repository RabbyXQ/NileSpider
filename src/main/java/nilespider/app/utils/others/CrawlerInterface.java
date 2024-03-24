package nilespider.app.utils.others;

public interface CrawlerInterface {

    String CRAWLING_TEXT = "Crawling: ";
    String FOUND_TEXT = "Found on: ";
    String URL_NOT_VALID = "URL is not valid";
    String EMAIL_FOUND = "Email Found: ";
    String GEO_INFO_FOUND = "Geographical Information Found: ";
    String PHONE_NO_FOUND = "Phone number found: ";
    String IMAGE_FOUND = "Image found: ";
    String VIDEO_FOUND = "Video found: ";
    String PDF_FOUND = "PDF Found: ";
    String INTERESTING_FILE_FOUND = "Interesting File Found: ";
    String OTHER_DOCUMENT_FOUNDT = "Other Document Found: ";

    String[] VIDEO_DOMAINS = {"youtube.com/watch", "vimeo.com", "instagram.com", "vumoo.to", "dailymotion.com",
            "metacafe.com", "veoh.com", "twitch.tv", "rutube.ru", "vidio.com", "viki.com",
            "crunchyroll.com", "funimation.com", "netflix.com", "hulu.com", "amazon.com"};

}
