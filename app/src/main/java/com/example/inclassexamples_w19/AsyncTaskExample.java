package com.example.inclassexamples_w19;



import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskExample extends AppCompatActivity {

    private EditText messageBox;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_example);

        DataFetcher networkThread = new DataFetcher();
        networkThread.execute("http://torunski.ca/CST2335.xml"); //this starts doInBackground on other thread

        messageBox = (EditText)findViewById(R.id.messageBox);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);  //show the progress bar
    }

    //                                          Type1    Type2    Type3
    private class DataFetcher extends AsyncTask<String, Integer, String>
    {
        @Override
        protected String doInBackground(String... params) {


            try {
                //get the string url:
                String myUrl = params[0];

                //create the network connection:
                URL url = new URL(myUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inStream = urlConnection.getInputStream();


                //create a pull parser:
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput( inStream  , "UTF-8");  //inStream comes from line 46

                //now loop over the XML:
                while(xpp.getEventType() != XmlPullParser.END_DOCUMENT)
                {
                    if(xpp.getEventType() == XmlPullParser.START_TAG)
                    {
                        String tagName = xpp.getName(); //get the name of the starting tag: <tagName>
                        if(tagName.equals("AMessage"))
                        {
                            String parameter = xpp.getAttributeValue(null, "message");
                            Log.e("AsyncTask", "Found parameter message: "+ parameter);
                            publishProgress(1); //tell android to call onProgressUpdate with 1 as parameter
                        }


                        else if(tagName.equals("Weather"))
                        {
                            String parameter = xpp.getAttributeValue(null, "outlook");
                            Log.e("AsyncTask", "Found parameter outlook: "+ parameter);

                            parameter = xpp.getAttributeValue(null, "windy");
                            Log.e("AsyncTask", "Found parameter windy: "+ parameter);
                            publishProgress(2); //tell android to call onProgressUpdate with 2 as parameter
                        }


                        else if(tagName.equals("Temperature")) {
                            xpp.next(); //move to the text between opening and closing tags:
                            String temp = xpp.getText();
                            publishProgress(3); //tell android to call onProgressUpdate with 3 as parameter
                        }
                    }

                    xpp.next(); //advance to next XML event
                }

                Thread.sleep(2000); //pause for 2000 milliseconds to watch the progress bar spin

            }catch (Exception ex)
            {
                Log.e("Crash!!", ex.getMessage() );
            }

            //return type 3, which is String:
            return "Finished task";
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i("AsyncTaskExample", "update:" + values[0]);
            messageBox.setText("At step:" + values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            //the parameter String s will be "Finished task" from line 27

            messageBox.setText("Finished all tasks");
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
