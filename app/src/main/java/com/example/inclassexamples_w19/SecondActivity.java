package com.example.inclassexamples_w19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btn = (Button)findViewById(R.id.previousPageButton);

        btn.setOnClickListener( b -> {
            setResult(50);
            //This line goes back to the previous page, and sends 50 as the result.
            finish();
        });

    }
}
