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
                signUpOTPAlert();
            }
        });
    }

    public void signUpOTPAlert() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View promptsView = LayoutInflater.from(this).inflate(R.layout.signup_otp_alert, viewGroup, false);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(SignUp_Act.this, R.style.AlertDialogStyle);
        alertBuilder.setView(promptsView);

        EditText etOtp1 = promptsView.findViewById(R.id.etOtp1);
        EditText etOtp2 = promptsView.findViewById(R.id.etOtp2);
        EditText etOtp3 = promptsView.findViewById(R.id.etOtp3);
        EditText etOtp4 = promptsView.findViewById(R.id.etOtp4);
        Button btnSubmitOtp = promptsView.findViewById(R.id.btnSubmitOtp);
        TextView tvResendOtp = promptsView.findViewById(R.id.tvResendOtp);
        SpannableString spannable = new SpannableString("Re-Send OTP");
        spannable.setSpan(new UnderlineSpan(), 0, spannable.length(), 0);
        tvResendOtp.setText(spannable);

        final AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }
}