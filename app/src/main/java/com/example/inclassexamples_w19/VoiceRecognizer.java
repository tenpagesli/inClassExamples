package com.example.inclassexamples_w19;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VoiceRecognizer extends AppCompatActivity {

    private final static int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 4;
    protected TextView resultsView;
    protected SpeechRecognizer sr ;
    protected StringBuffer str = new StringBuffer("\n");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recognizer);


        Button listenButton = (Button)findViewById(R.id.speechButton);
        listenButton.setOnClickListener(e -> startListening() ); //call the startListening() function below

        resultsView = (TextView)findViewById(R.id.resultsWindow);
        sr = SpeechRecognizer.createSpeechRecognizer(this);
    }




    //this function starts listening
    protected void startListening()
    {

        // sr is a speech recognizer. These functions get called at various times in the recognition process
        sr.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) { }

            @Override
            public void onBeginningOfSpeech() {  }

            @Override
            public void onRmsChanged(float rmsdB) { }

            @Override
            public void onBufferReceived(byte[] buffer) { }

            @Override
            public void onEndOfSpeech() { }

            @Override
            public void onError(int error) { }

            @Override
            public void onPartialResults(Bundle partialResults) { }

            @Override
            public void onEvent(int eventType, Bundle params) { }



            //on results means that the server has found your text. It returns the top N results in order of match. The number N is set below:
            @Override
            public void onResults(Bundle results) {

                StringBuilder str = new StringBuilder("\n");
                Log.d("RESULTS", "onResults " + results);
                ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                for (String s : data)
                {
                    Log.d("RESULTS", "result " + s);
                    str.append(s );
                }
                resultsView.setText( "results: " + data.get(0) );
            }

        });

        // Before you start listening, you need permission for listening:
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED)
        {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Toast.makeText(this, "I need permission to listen to you!!", Toast.LENGTH_LONG).show();
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {


            // Permission has already been granted
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"com.example.inclassexamples_w19");
            intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);

            //Here the value of N is 5. Change this number to be the number of best matches from the server:
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,5);

            //start listening:
            sr.startListening(intent);
        }
    }
}
