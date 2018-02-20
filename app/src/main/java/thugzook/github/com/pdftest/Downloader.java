package thugzook.github.com.pdftest;

import android.os.StrictMode;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Noah Sushimushi on 2/12/2018.
 *      Called by some "main" activity to download a file from a website URL
 *      References: https://stackoverflow.com/questions/9759205/download-a-pdf-file-and-save-it-to-sdcard-and-then-read-it-from-there
 */

public class Downloader {

    /*** THIS VERSION GOES AROUND THE ASYNCEVENT REQUIREMENT WITH CHANGING THE SDK'S THREAD POLICY ***/
    public static void DownloadFile(String fileURL, File directory) {
        try {
            //Circumvents the async requirement needed by higher build versions.
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            //////////////////////////////////////////////////////////////////////

            FileOutputStream f = new FileOutputStream(directory);
            URL u = new URL(fileURL);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.connect();
            InputStream in = c.getInputStream();

            //Read bytes of the file
            byte[] buffer = new byte[8192];
            int len1 = 0;
            while ((len1 = in.read(buffer)) > 0) {
                f.write(buffer, 0, len1);
            }
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}