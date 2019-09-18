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
    }
}
