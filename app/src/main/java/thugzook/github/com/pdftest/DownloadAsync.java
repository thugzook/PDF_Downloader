package thugzook.github.com.pdftest;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;

import java.io.File;

public class DownloadAsync extends AsyncTask<String, String, String> {
    //ProgressDialog pDialog;

    @Override
    /**
     protected void onPreExecute() {
     super.onPreExecute();
     Log.d("Hi", "thugzook.github.com.pdftest.Download Commencing");

     pDialog = new ProgressDialog(MainActivity.this);
     pDialog.setMessage("Downloading Database...");


     String message= "Executing Process";

     SpannableString ss2 =  new SpannableString(message);
     ss2.setSpan(new RelativeSizeSpan(2f), 0, ss2.length(), 0);
     ss2.setSpan(new ForegroundColorSpan(Color.BLACK), 0, ss2.length(), 0);

     pDialog.setMessage(ss2);

     pDialog.setCancelable(false);
     pDialog.show();
     }

     @Override**/
    protected String doInBackground(String... params) {

        //INSERT YOUR FUNCTION CALL HERE

        return "Executed!";

    }

    //@Override
    /** protected void onPostExecute(String result) {
     super.onPostExecute(result);
     Log.d("Hi", "Done Downloading.");
     pDialog.dismiss();

     }**/
}
