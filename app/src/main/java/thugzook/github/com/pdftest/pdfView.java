package thugzook.github.com.pdftest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
//outside library that allows for inline pdfViewing
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class pdfView extends AppCompatActivity {
    PDFView pdfView; //pdfView object
    String URL;
    String fileName;
    File directory; //path of created File

    // Container for all parameters of DownloadAsync
    private static class AsyncParameters {
        String URL;
        File directory;
        AsyncParameters(String URL, File directory) {
            this.URL = URL;
            this.directory = directory;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        //Grab the extras from the intent call
        Intent intent = getIntent(); //whatever calls this activity, gather the intent
        URL = intent.getStringExtra("File URL"); // in this case, get the file name of the "extra" passed through
        fileName = intent.getStringExtra("File Name");

        //Grab the internal storage directory and create a folder if it doesn't exist
        File intDirectory = getFilesDir();
        File folder = new File(intDirectory, "pdf");
        boolean isDirectoryCreated = folder.exists();

        //See if the file exists
        if (!isDirectoryCreated) {
            isDirectoryCreated= folder.mkdir();
        }
        if(isDirectoryCreated) {
            directory = new File(folder, fileName);
            try {
                directory.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            //See if file already exists (reduces wait time)
            boolean empty = directory.length() == 0;
            if (empty) {
                /**Call class to create parameter container **/
                AsyncParameters param = new AsyncParameters(URL, directory);
                DownloadAsync Downloader = new DownloadAsync();
                Downloader.execute(param);
            }
                showPdf();
        }

    }
    /**class for library showPdf**/
    public void showPdf()
    {
        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.fromFile(directory).load();
    }

    /**Class for asynchronous tasks**/
    public class DownloadAsync extends AsyncTask<AsyncParameters, Void, Void> {

        // Container for all parameters of DownloadAsync
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            //Create a progress bar that details the program downloading
            super.onPreExecute();
            pDialog = new ProgressDialog(pdfView.this);
            pDialog.setMessage("Downloading Database...");
            String message= "Downloading Files";

            SpannableString ss2 =  new SpannableString(message);
            ss2.setSpan(new RelativeSizeSpan(2f), 0, ss2.length(), 0);
            ss2.setSpan(new ForegroundColorSpan(Color.BLACK), 0, ss2.length(), 0);

            pDialog.setMessage(ss2);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(AsyncParameters... params) {
            /**grab the container AsyncParameters and the values within it**/
            String fileURL = params[0].URL;
            File directory = params[0].directory;
            try {
                FileOutputStream f = new FileOutputStream(directory);
                java.net.URL u = new URL(fileURL);
                HttpURLConnection c = (HttpURLConnection) u.openConnection();
                c.connect();
                InputStream in = c.getInputStream();

                byte[] buffer = new byte[8192];
                int len1 = 0;
                while ((len1 = in.read(buffer)) > 0) {
                    f.write(buffer, 0, len1);
                }
                f.close();
            } catch (Exception e) {
                e.printStackTrace();
                pDialog.setMessage(new SpannableString("ERROR DOWNLOADING"));
            }
            onPostExecute();
            return null;
        }

        private void onPostExecute() {
            pDialog.dismiss();
            showPdf();
        }
    }

}
