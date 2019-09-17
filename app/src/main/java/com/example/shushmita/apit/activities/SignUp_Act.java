package com.example.shushmita.apit.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.shushmita.apit.MainActivity;
import com.example.shushmita.apit.R;

import org.w3c.dom.Text;

import java.util.Locale;

public class SignUp_Act extends AppCompatActivity {

    Toolbar toolbar;
    private ImageView ivShowPass, ivHidePass, ivConfirmHidePass, ivConfirmShowPass;
    private EditText etNewPasswd, etConfirmPaswd;
    private Button btnNext;
    private static final long START_TIME_IN_MILLIS = 60000; //60sec
    //    TODO Accept Input from user & store it in  START_TIME_IN_MILLIS
    int progress;
    private CountDownTimer MyCountDownTimer;
    private boolean TimerRunning;
    //        Initially TimeLeftInMillis will be same as START_TIME_IN_MILLIS
    private long TimeLeftInMillis = START_TIME_IN_MILLIS;
    private int ProgressBarStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        ivShowPass = findViewById(R.id.ivShowPass);
        ivHidePass = findViewById(R.id.ivHidePass);
        etNewPasswd = findViewById(R.id.etNewPasswd);
        etConfirmPaswd = findViewById(R.id.etConfirmPaswd);
        ivConfirmShowPass = findViewById(R.id.ivConfirmShowPass);
        ivConfirmHidePass = findViewById(R.id.ivConfirmHidePass);
        btnNext = findViewById(R.id.btnNext);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
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

        ivShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivShowPass.setVisibility(View.GONE);
                ivHidePass.setVisibility(View.VISIBLE);
                etNewPasswd.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        });
        ivHidePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivHidePass.setVisibility(View.GONE);
                ivShowPass.setVisibility(View.VISIBLE);
                etNewPasswd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        ivConfirmShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivConfirmShowPass.setVisibility(View.GONE);
                ivConfirmHidePass.setVisibility(View.VISIBLE);
                etConfirmPaswd.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        });
        ivConfirmHidePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivConfirmHidePass.setVisibility(View.GONE);
                ivConfirmShowPass.setVisibility(View.VISIBLE);
                etConfirmPaswd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intentMainActivity = new Intent(SignUp_Act.this,MainActivity.class);
                startActivity(intentMainActivity)*/
                signUpOTPAlert();
            }
        });
    }

    public void signUpOTPAlert() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View promptsView = LayoutInflater.from(this).inflate(R.layout.signup_otp_alert, viewGroup, false);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(SignUp_Act.this, R.style.AlertDialogStyle);
        alertBuilder.setView(promptsView);

        final TextView textViewShowTime = promptsView.findViewById(R.id.tv_timerview_time);
        final ProgressBar progressbar_timerview = promptsView.findViewById(R.id.progressbar_timerview);

        EditText etOtp1 = promptsView.findViewById(R.id.etOtp1);
        EditText etOtp2 = promptsView.findViewById(R.id.etOtp2);
        EditText etOtp3 = promptsView.findViewById(R.id.etOtp3);
        EditText etOtp4 = promptsView.findViewById(R.id.etOtp4);
        Button btnSubmitOtp = promptsView.findViewById(R.id.btnSubmitOtp);
        TextView tvResendOtp = promptsView.findViewById(R.id.tvResendOtp);
        SpannableString spannable = new SpannableString("Re-Send OTP");
        spannable.setSpan(new UnderlineSpan(), 0, spannable.length(), 0);
        tvResendOtp.setText(spannable);
        progressbar_timerview.setMax((int) TimeLeftInMillis);
        MyCountDownTimer = new CountDownTimer(START_TIME_IN_MILLIS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                TimeLeftInMillis = millisUntilFinished;
                int minutes = (int) (TimeLeftInMillis / 1000) / 60;
                int seconds = (int) (TimeLeftInMillis / 1000) % 60;
                String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", seconds);
                textViewShowTime.setText(timeLeftFormatted);
                progressbar_timerview.setProgress((int) TimeLeftInMillis);



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
                TimeLeftInMillis = START_TIME_IN_MILLIS;
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
        final AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }
}