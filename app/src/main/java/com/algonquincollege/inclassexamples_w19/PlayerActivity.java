package com.algonquincollege.inclassexamples_w19;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Vibrator;
import android.os.VibrationEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;


public class PlayerActivity extends AppCompatActivity {



    private int position = 0;
    private MediaController mediaController = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String URL = getIntent().getStringExtra("URL");

        setContentView(R.layout.activity_player);


        VideoView player =(VideoView) findViewById(R.id.videoView);
        mediaController = new MediaController(this);
        mediaController.setAnchorView(player);
        mediaController.setMediaPlayer(player);

        player.setMediaController(mediaController);
        player.setVideoURI(Uri.parse(URL));
        player.setOnPreparedListener( video ->{
            player.seekTo(position);
            if(position == 0)
                player.start();
        });

    }


}
// http://ftp.halifax.rwth-aachen.de/blender/demo/movies/ToS/tears_of_steel_720p.mov
// http://peach.themazzone.com/durian/movies/sintel-1280-surround.mp4
// https://download.blender.org/peach/bigbuckbunny_movies/big_buck_bunny_720p_h264.mov
// http://peach.themazzone.com/durian/movies/sintel-1280-surround.mp4
