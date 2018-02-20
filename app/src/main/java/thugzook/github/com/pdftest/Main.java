package thugzook.github.com.pdftest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {
    /**NOTE this program only works after including the correct permissions and adding dependencies to build.gradle**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button pdfButton = findViewById(R.id.button1);

        pdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, pdfView.class);

                //pass the URL and filename to pdfView
                intent.putExtra("File URL", "https://www.colorado.edu/physics/phys1110/phys1110_sp15/lectures/Lec14.pdf");
                intent.putExtra ("File Name", "finalfinal.pdf");
                startActivity(intent);
            }
        });
    }
}
