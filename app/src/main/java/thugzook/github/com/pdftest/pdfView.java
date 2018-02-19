package thugzook.github.com.pdftest;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.IOException;

public class pdfView extends AppCompatActivity {
    PDFView pdfView; //pdfView object
    String URL;
    String fileName;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        /*Viewing from asset
        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset("posterGundam1.pdf").load();*/

        //Let's try to download a file and view it in one go, using the template from stackOverflow
        Intent intent = getIntent(); //whatever calls this activity, gather the intent
        URL = intent.getStringExtra("File URL"); // in this case, get the file name of the "extra" passed through
        fileName = intent.getStringExtra("File Name");

        /******* referenced code here ******/
        File intDirectory = getFilesDir();
        File folder = new File(intDirectory, "pdf");
        boolean isDirectoryCreated = folder.exists(); // see if the folder exists

        if (!isDirectoryCreated) {
            isDirectoryCreated= folder.mkdir();
        }
        if(isDirectoryCreated) {
            file = new File(folder, fileName);
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            //Call method DownloadFile in Downloader class
            Downloader.DownloadFile(URL, file);
            /**DownloadAsync myAsyncTask = new DownloadAsync();
            myAsyncTask.execute(URL, file);**/ //revisit sometime about asynchronous tasks.
            showPdf();
        }

    }
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    public void showPdf()
    {
        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.fromFile(file).load();
    }

}
