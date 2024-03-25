/**
 * Interface that provides useful constants of the system
 * **/

package nilespider.app.views.main;

import nilespider.app.utils.controllers.*;

public interface CrawlerTypeInterface {
    BasicCrawlerController crawlingThread = new BasicCrawlerController();
    EmailCrawlerController emailCrawlerThread = new EmailCrawlerController();
    PhoneNumberCrawlerController phoneNumberCrawlerController = new PhoneNumberCrawlerController();
    GeographicCrawlerController geographicCrawlerController = new GeographicCrawlerController();
    ImageCrawlerController imageCrawlerController = new ImageCrawlerController();
    VideoCrawlerController videoCrawlerController = new VideoCrawlerController();
    PDFCrawlerController pdfCrawlerController = new PDFCrawlerController();
    OtherDocumentCrawlerController otherDocumentCrawlerController = new OtherDocumentCrawlerController();
    InterestingFileCrawlerController interestingFileCrawlerController = new InterestingFileCrawlerController();
    String[] COMBO_BOX_MENU_ITEMS = new String[] {
            "Text",
            "Phone",
            "Email",
            "Geographic Information",
            "Images",
            "Videos",
            "PDFs",
            "Other Docs",
            "Interesting Files"
    };
    Thread[] CRAWLER_THREADS = new Thread[]{
            crawlingThread,
            emailCrawlerThread,
            phoneNumberCrawlerController,
            geographicCrawlerController,
            imageCrawlerController,
            videoCrawlerController,
            pdfCrawlerController,
            otherDocumentCrawlerController,
            interestingFileCrawlerController
    };
}
