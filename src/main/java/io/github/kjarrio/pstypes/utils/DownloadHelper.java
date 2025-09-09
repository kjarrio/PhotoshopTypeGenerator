package io.github.kjarrio.pstypes.utils;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import java.io.File;

public class DownloadHelper {

    public static File downloadToTempDirectory(String url, String fileName) {
        try {
            Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).execute();
            if (response.statusCode() != 200) return null;
            File outputFile = new File(getTempDir(), fileName);
            FileUtils.writeByteArrayToFile(outputFile, response.bodyAsBytes());
            return outputFile;
        } catch (Exception e) {
            return null;
        }
    }

    public static File getTempDir() {
        return new File(System.getProperty("java.io.tmpdir"));
    }

}
