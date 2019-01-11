package com.example.inclassexamples_w19;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private int numClicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This line says to load activity_main.xml in the res/layout folder:
        setContentView(R.layout.activity_main);

        //After this line, you can start importing your XML Widgets:
        //Button button1 = (Button) findViewById(R.id.button1);

    }
}
