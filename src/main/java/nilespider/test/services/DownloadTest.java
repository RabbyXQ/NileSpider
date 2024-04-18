package nilespider.test.services;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTest {
    private final String TEST_FILE_URL = "https://downloads.ctfassets.net/31lakm8hkl1j/2utibijwKHOfgC8s0opM8p/49bcb450640cabe700034d2b81c0e8d3/Howard_Anton__Chris_Rorres_-_Elementary_Linear_Algebra_with_Applications-Wiley__2005_.pdf";
    private final String TEST_SAVE_DIR = "test_downloads";


    public static void downloadFile(String fileUrl, String saveDir) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");

            if (disposition != null) {
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10, disposition.length() - 1);
                }
            } else {
                fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            }

            String saveFilePath = saveDir + File.separator + fileName;
            long fileSize = httpConn.getContentLengthLong();

            InputStream inputStream = httpConn.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            byte[] buffer = new byte[4096];
            int bytesRead;
            long totalBytesRead = 0;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                int progress = (int) (totalBytesRead * 100 / fileSize);
                System.out.println("Download Progress: " + progress + "%");
            }

            outputStream.close();
            inputStream.close();
            System.out.println("File downloaded successfully!");

        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }


    @Before
    public void setUp() {
        File dir = new File(TEST_SAVE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @After
    public void tearDown() {
        File dir = new File(TEST_SAVE_DIR);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
            dir.delete();
        }
    }

    @Test
    public void testDownloadFile() {
        try {
            downloadFile(TEST_FILE_URL, TEST_SAVE_DIR);
            File downloadedFile = new File(TEST_SAVE_DIR + File.separator + "Howard_Anton__Chris_Rorres_-_Elementary_Linear_Algebra_with_Applications-Wiley__2005_.pdf");
            assertTrue(downloadedFile.exists());
            assertTrue(downloadedFile.isFile());
            assertTrue(downloadedFile.length() > 0);
        } catch (IOException e) {
            fail("Exception thrown during file download: " + e.getMessage());
        }
    }
}
