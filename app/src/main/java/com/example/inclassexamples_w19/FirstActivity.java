package com.example.inclassexamples_w19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button nextButton = (Button)findViewById(R.id.nextPageButton);
        nextButton.setOnClickListener( b -> {

            //Give directions to go from this page, to SecondActivity
            Intent nextPage = new Intent(FirstActivity.this, SecondActivity.class);
            
            //Now make the transition:
            startActivity( nextPage );
        });

        Button thirdPage = (Button)findViewById(R.id.thirdPageButton);
        thirdPage.setOnClickListener( c -> {

            Intent nextPage = new Intent(FirstActivity.this, ThirdActivity.class);
            //Give directions to go from this page, to SecondActivity
            EditText et = (EditText)findViewById(R.id.value_text);
            nextPage.putExtra("ItemOne", "Some text");
            nextPage.putExtra("ItemTwo", 500);
            nextPage.putExtra("typed", et.getText().toString());

            //Now make the transition:
            startActivityForResult( nextPage, 345);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //if request code is 345, then we are coming back from ThirdActivity, as written on line 36
        if(requestCode == 345)
        {
            // resultCode will only be 60 if the user clicks on the back button on page 3 (ThirdActivity.java line 35)
            if(resultCode == 60)
            {
                EditText et = (EditText)findViewById(R.id.value_text);
                String fromPageThree = data.getStringExtra("NextPageTyped");
                et.setText(fromPageThree);
                Log.i("Back", "Message");
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
