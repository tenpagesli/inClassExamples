package com.example.inclassexamples_w19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // get the intent that got us here
        Intent fromPrevious = getIntent();

        String previousTyped = fromPrevious.getStringExtra("typed");
        String someText = fromPrevious.getStringExtra("ItemOne");
        int extraInt = fromPrevious.getIntExtra("ItemTwo", 0);

        //Put the string that was sent from FirstActivity into the edit text:
        EditText enterText = (EditText)findViewById(R.id.value_text);
        enterText.setText(previousTyped);

        Button btn = (Button)findViewById(R.id.backButton);
        btn.setOnClickListener( b -> {

            //create an intent to send data back to FirstActivity:
            Intent dataToGoBack = new Intent();

            //Send data back to previous page. Under "NextPageTyped", save the string:
            dataToGoBack.putExtra("NextPageTyped", enterText.getText().toString());

            //Result 60 will represent the back button
            setResult(60, dataToGoBack);
            //This line goes back to the previous page, and sends 60 as the result.
            finish();
        });


        Button save = (Button)findViewById(R.id.saveButton);
        save.setOnClickListener ( bt -> {
            startActivity( new Intent( ThirdActivity.this, SharedPreferencesExample.class)  );
        });

    }
}
