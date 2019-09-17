package com.example.shushmita.apit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.shushmita.apit.R;

public class ForgotPassword_Act extends AppCompatActivity {

    Toolbar toolbar;
    private TextView tvLogin;
    private Button btnSubmit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

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

        tvLogin = findViewById(R.id.tvLogin);
        SpannableString spannableStrLogin = new SpannableString("Login");
        spannableStrLogin.setSpan(new UnderlineSpan(),0,spannableStrLogin.length(),0);
        tvLogin.setText(spannableStrLogin);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(ForgotPassword_Act.this,Login_Act.class);
                intentLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentLogin);
            }
        });

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(ForgotPassword_Act.this,VerifyForgotOtp_Act.class);
                intentLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentLogin);
            }
        });
    }
}
