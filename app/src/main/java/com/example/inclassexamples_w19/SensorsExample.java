package com.example.inclassexamples_w19;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class SensorsExample extends AppCompatActivity {

    private TextView stepCountView = null;
    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_example);

        stepCountView = (TextView)findViewById(R.id.stepId);


        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if(mSensor != null) //might not have a step counter
            mSensorManager.registerListener(new StepCountListener(), mSensor, SensorManager.SENSOR_DELAY_NORMAL);



        Button flashButton = (Button)findViewById(R.id.flashlightButton);
        flashButton.setOnClickListener( e ->
        {

        });
    }


    private class StepCountListener implements SensorEventListener{
        @Override
        public void onSensorChanged(SensorEvent sensorEvent)
        {
            Log.i("Step count:", "Step count is now:" + sensorEvent.values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }
}
