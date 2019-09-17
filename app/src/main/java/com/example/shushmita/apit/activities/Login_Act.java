package com.example.shushmita.apit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shushmita.apit.R;

public class Login_Act extends AppCompatActivity {

    private TextView tvForgotPasswd,tvCreateAccount;
    private Button btnSignIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);

        tvForgotPasswd = findViewById(R.id.tvForgotPasswd);
        SpannableString spannableStrForgotPasswd = new SpannableString("Forgot Password");
        spannableStrForgotPasswd.setSpan(new UnderlineSpan(),0,spannableStrForgotPasswd.length(),0);
        tvForgotPasswd.setText(spannableStrForgotPasswd);

        tvCreateAccount = findViewById(R.id.tvCreateAccount);
        SpannableString spannableStrSignUp = new SpannableString("Create an Account");
        spannableStrSignUp.setSpan(new UnderlineSpan(),0,spannableStrSignUp.length(),0);
        tvCreateAccount.setText(spannableStrSignUp);

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUp = new Intent(Login_Act.this,SignUp_Act.class);
                intentSignUp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentSignUp);
            }
        });

        tvForgotPasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUp = new Intent(Login_Act.this,ForgotPassword_Act.class);
                intentSignUp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentSignUp);
            }
        });

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUp = new Intent(Login_Act.this,DashBoard_Act.class);
                intentSignUp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentSignUp);
            }
        });
    }
}
