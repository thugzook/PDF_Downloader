package thugzook.github.com.pdftest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {
    Button pdfButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button pdfButton = findViewById(R.id.button1);

        pdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, pdfView.class);
                intent.putExtra("File URL", "https://www.colorado.edu/physics/phys1110/phys1110_sp15/lectures/Lec14.pdf");
                //intent.putExtra("File URL", "http://download940.mediafire.com/4bjw794wg6yg/tou3v80fs397q8o/posterGundam1.pdf"); //put extra values to be passed into the intent received by pdfView
                intent.putExtra ("File Name", "Gundam1.pdf"); //2nd value is the file name wanted
                startActivity(intent);
            }
        });
    }
}
