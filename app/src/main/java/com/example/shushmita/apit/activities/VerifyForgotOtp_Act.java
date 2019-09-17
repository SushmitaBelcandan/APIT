package com.example.shushmita.apit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.shushmita.apit.R;

import java.util.Locale;

public class VerifyForgotOtp_Act extends AppCompatActivity {


    Toolbar toolbar;
    private Button btnVerifyOtp;
    private TextView tvResendOtp;
    private static final long START_TIME_IN_MILLIS = 60000; //60sec
    //    TODO Accept Input from user & store it in  START_TIME_IN_MILLIS
    int progress;
    private CountDownTimer MyCountDownTimer;
    private boolean TimerRunning;
    //        Initially TimeLeftInMillis will be same as START_TIME_IN_MILLIS
    private long TimeLeftInMillis = START_TIME_IN_MILLIS;
    private int ProgressBarStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_forgot_otp);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnVerifyOtp = findViewById(R.id.btnVerifyOtp);
        btnVerifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResetPasswd = new Intent(VerifyForgotOtp_Act.this,ResetPassword_Act.class);
                intentResetPasswd.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentResetPasswd);
            }
        });

        tvResendOtp = findViewById(R.id.tvResendOtp);
        SpannableString spannableStrLogin = new SpannableString("Re-Send OTP");
        spannableStrLogin.setSpan(new UnderlineSpan(),0,spannableStrLogin.length(),0);
        tvResendOtp.setText(spannableStrLogin);

        final TextView textViewShowTime = findViewById(R.id.tv_timerview_time);
        final ProgressBar progressbar_timerview = findViewById(R.id.progressbar_timerview);

        MyCountDownTimer = new CountDownTimer(START_TIME_IN_MILLIS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                TimeLeftInMillis = millisUntilFinished;
                int minutes = (int) (TimeLeftInMillis / 1000) / 60;
                int seconds = (int) (TimeLeftInMillis / 1000) % 60;
                String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", seconds);
                textViewShowTime.setText(timeLeftFormatted);
                progressbar_timerview.setProgress((int)TimeLeftInMillis);



                /*for incrementing progressbar every second calculating progress for every second*/
                progress = (int) (START_TIME_IN_MILLIS / (1 * 100));
                //incrementing progress on every tick
                ProgressBarStatus += progress;
                progressbar_timerview.setProgress(ProgressBarStatus);

            }

            @Override
            public void onFinish() {
                TimerRunning = false;
                textViewShowTime.setText("00");
                progressbar_timerview.setProgress(100);
            }
        }.start();

        TimerRunning = true;
        //resent otp
        tvResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeLeftInMillis =START_TIME_IN_MILLIS ;
                int minutes = (int) (TimeLeftInMillis / 1000) / 60;
                int seconds = (int) (TimeLeftInMillis / 1000) % 60;
                String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", seconds);
                textViewShowTime.setText(timeLeftFormatted);

                progress = (int) (START_TIME_IN_MILLIS / (1 * 100));
                //incrementing progress on every tick
                ProgressBarStatus += progress;
                progressbar_timerview.setProgress(ProgressBarStatus);
            }
        });
    }
}
