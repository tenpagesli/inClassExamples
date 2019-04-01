package com.example.inclassexamples_w19;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class SensorExample extends AppCompatActivity
{
    SensorManager sm = null;
    Sensor aSensor = null;
    boolean flashLightStatus = false;
    boolean deviceHasCameraFlash = false;

    CameraManager camManager = null;
    String cameraId = null;

    EditText xEditText , yEditText, zEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_example);

        camManager= (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try{
        cameraId = camManager.getCameraIdList()[0]; // Usually front camera is at 0 position.
        deviceHasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        }
        catch (Exception e)
        {

        }

        try {
            if(deviceHasCameraFlash){
                if(flashLightStatus){//when light on

                    //turn the light off:
                    camManager.setTorchMode(cameraId, false);
                }
                else //when light off

                    //turn the light on:
                    camManager.setTorchMode(cameraId, true);
            }

            flashLightStatus = !flashLightStatus ; //flip true to false, or false to true
        }
        catch( Throwable t)
        {
            Log.i("Exception:", t.getMessage());
        }
    }

}
