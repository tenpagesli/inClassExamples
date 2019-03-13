package com.example.inclassexamples_w19;



import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        networkThread.execute( "http://torunski.ca/CST2335_XML.xml" ); //this starts doInBackground on other thread

        messageBox = (EditText)findViewById(R.id.messageBox);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);  //show the progress bar
    }





    // a subclass of AsyncTask                  Type1    Type2    Type3
    private class DataFetcher extends AsyncTask<String, Integer, String>
    {
        @Override
        protected String doInBackground(String ... params) {


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
                        if(tagName.equals("dt"))
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

                //End of XML reading

                //Start of JSON reading of UV factor:

                //create the network connection:
                URL UVurl = new URL("http://api.openweathermap.org/data/2.5/uvi?appid=7e943c97096a9784391a981c4d878b22&lat=45.348945&lon=-75.759389");
                HttpURLConnection UVConnection = (HttpURLConnection) UVurl.openConnection();
                inStream = UVConnection.getInputStream();

                //create a JSON object from the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                String result = sb.toString();

                //now a JSON table:
                JSONObject jObject = new JSONObject(result);
                double aDouble = jObject.getDouble("value");
                Log.i("UV is:", ""+ aDouble);

                //END of UV rating

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
