package com.example.inclassexamples_w19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class UnitTestingExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_testing_example);

        EditText result = (EditText)findViewById(R.id.resultsWindow);

        Button b1 = (Button)findViewById(R.id.button1);
        b1.setOnClickListener(e -> result.setText("You clicked button 1"));


        Button b2 = (Button)findViewById(R.id.button2);
        b2.setOnClickListener(e -> result.setText("You clicked button 2"));

        Button b3 = (Button)findViewById(R.id.button3);
        b3.setOnClickListener(e -> result.setText("You clicked button 3"));

    }
}
