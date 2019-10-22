package com.apinnovations.apit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import com.apinnovations.apit.R;

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