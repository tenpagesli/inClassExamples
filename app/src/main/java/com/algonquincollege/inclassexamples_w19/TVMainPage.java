package com.algonquincollege.inclassexamples_w19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class TVMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_page);

        Intent playMovie = new Intent(this, PlayerActivity.class);
        Button playButton = (Button)findViewById(R.id.button1);
        playButton.setOnClickListener( e ->  {
            playMovie.putExtra("URL", "http://ftp.halifax.rwth-aachen.de/blender/demo/movies/ToS/tears_of_steel_720p.mov");
            startActivity(playMovie);
        });


        playButton = (Button)findViewById(R.id.button2);
        playButton.setOnClickListener( e ->  {
            playMovie.putExtra("URL", "http://peach.themazzone.com/durian/movies/sintel-1280-surround.mp4");
            startActivity(playMovie);
        });

        playButton = (Button)findViewById(R.id.button3);
        playButton.setOnClickListener( e ->  {
            playMovie.putExtra("URL", "https://download.blender.org/peach/bigbuckbunny_movies/big_buck_bunny_720p_h264.mov");
            startActivity(playMovie);
        });

        playButton = (Button)findViewById(R.id.button4);
        playButton.setOnClickListener( e ->  {
            playMovie.putExtra("URL", "https://archive.org/download/ElephantsDream/ed_1024_512kb.mp4");
            startActivity(playMovie);
        });
    }
}
