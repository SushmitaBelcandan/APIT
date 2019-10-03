package com.example.shushmita.apit.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.shushmita.apit.R;
import com.example.shushmita.apit.adapters.Utils;
import com.example.shushmita.apit.retrofit_models.APIClient;
import com.example.shushmita.apit.retrofit_models.APIInterface;
import com.example.shushmita.apit.retrofit_models.ForgotPassModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword_Act extends AppCompatActivity {

    APIInterface apiInterface;
    ProgressDialog progressdialog;
    Toolbar toolbar;
    private TextView tvResendOtp;
    private EditText etLoginId;
    private LinearLayout llResendOtp;
    private Button btnSubmit;
    private String strInput;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

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

        apiInterface = APIClient.getClient().create(APIInterface.class);
        progressdialog = new ProgressDialog(ForgotPassword_Act.this);
        progressdialog.setMessage("Please Wait....");

        etLoginId = findViewById(R.id.etLoginId);
        llResendOtp = findViewById(R.id.llResendOtp);
        tvResendOtp = findViewById(R.id.tvResendOtp);
        SpannableString spannableStrLogin = new SpannableString("Login");
        spannableStrLogin.setSpan(new UnderlineSpan(), 0, spannableStrLogin.length(), 0);
        tvResendOtp.setText(spannableStrLogin);

        llResendOtp.setOnClickListener(new View.OnClickListener() {
            //clear top
            @Override
            public void onClick(View v) {

                strInput = etLoginId.getText().toString();

                if (TextUtils.isEmpty(strInput)) {
                    new AlertDialog.Builder(ForgotPassword_Act.this)
                            .setMessage("Please Enter Registered Email or Mobile No !")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                }

                if (Utils.CheckInternetConnection(ForgotPassword_Act.this)) {
                    forgetPassword(strInput);
                } else {
                    Toast.makeText(ForgotPassword_Act.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strInput = etLoginId.getText().toString().trim();
                if (TextUtils.isEmpty(strInput)) {
                    new AlertDialog.Builder(ForgotPassword_Act.this)
                            .setMessage("Please Enter Registered Email or Mobile No !")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                } else {
                    if (Utils.CheckInternetConnection(ForgotPassword_Act.this)) {
                        if (validateInput(strInput)) {
                            forgetPassword(strInput);
                        } else {
                            //nothing to show
                        }
                    } else {
                        Toast.makeText(ForgotPassword_Act.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void forgetPassword(final String strInput) {
        try {
            progressdialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        final ForgotPassModel fpm = new ForgotPassModel(strInput);
        Call<ForgotPassModel> forgotPassModelCall = apiInterface.forgotPass(fpm);
        forgotPassModelCall.enqueue(new Callback<ForgotPassModel>() {
            @Override
            public void onResponse(Call<ForgotPassModel> call, Response<ForgotPassModel> response) {
                ForgotPassModel forgotResources = response.body();
                if (forgotResources.status.equals("success")) {
                    List<ForgotPassModel.ForgotPassDatum> forgotList = forgotResources.resultData;
                    for (ForgotPassModel.ForgotPassDatum fogotDatum : forgotList) {
                        Intent intentVerifyOtp = new Intent(ForgotPassword_Act.this, VerifyForgotOtp_Act.class);
                        intentVerifyOtp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (strInput.matches("[0-9]+")) {
                            intentVerifyOtp.putExtra("Mobile1", fogotDatum.mobileno);
                        } else {
                            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(strInput).matches()) {
                                intentVerifyOtp.putExtra("Email1", fogotDatum.email);
                            }
                        }
                        startActivity(intentVerifyOtp);
                    }
                    progressdialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), forgotResources.message, Toast.LENGTH_SHORT).show();
                    progressdialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ForgotPassModel> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public boolean validateInput(String strVal) {

        if (strVal == null || strVal.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Invalid Email Id and Mobile No", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (strVal.matches("[0-9]+")) {
                if (strVal.length() <= 2) {
                    etLoginId.setError("Please Enter valid phone number");
                    etLoginId.requestFocus();
                    return false;
                }
            } else {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(strVal).matches()) {
                    etLoginId.setError("Please Enter valid email");
                    etLoginId.requestFocus();
                    return false;
                }
            }
        }

        return true;

    }
}
