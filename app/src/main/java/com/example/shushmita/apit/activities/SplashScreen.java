package com.example.shushmita.apit.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseLongArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.shushmita.apit.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spalsh_screen);

        final VideoView videoView;
        videoView = (VideoView)findViewById(R.id.video_view);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.apit_logo);
        videoView.start();

        Thread loading = new Thread() {
            public void run() {
                try {
                    sleep(6000);
                    Intent main = new   Intent(SplashScreen.this,Login_Act.class);
                    startActivity(main);
                    finish();


                }

                catch (Exception e) {
                    e.printStackTrace();
                }

                finally {
                    finish();
                }
            }
        };

        loading.start();
    }

}