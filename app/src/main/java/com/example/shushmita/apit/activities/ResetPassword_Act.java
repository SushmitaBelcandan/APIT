package com.example.shushmita.apit.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.shushmita.apit.R;
import com.example.shushmita.apit.adapters.Utils;
import com.example.shushmita.apit.retrofit_models.APIClient;
import com.example.shushmita.apit.retrofit_models.APIInterface;
import com.example.shushmita.apit.retrofit_models.ResetPassModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassword_Act extends AppCompatActivity {

    Toolbar toolbar;
    ProgressDialog progressdialog;
    APIInterface apiInterface;

    private Button btnSubmit;
    private EditText etNewPasswd, etConfirmPasswd;
    private String sConfPass, sNewPass, email, mobile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        progressdialog = new ProgressDialog(ResetPassword_Act.this);
        progressdialog.setMessage("Please Wait....");
        apiInterface = APIClient.getClient().create(APIInterface.class);

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
        btnSubmit = findViewById(R.id.btnSubmit);

        etConfirmPasswd = findViewById(R.id.etConfirmPasswd);
        etNewPasswd = findViewById(R.id.etNewPasswd);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mobile = getIntent().getExtras().getString("Mobileno", "defaultKey");
                email = getIntent().getExtras().getString("Email", "defaultKey");
                sNewPass = etNewPasswd.getText().toString().trim();
                sConfPass = etConfirmPasswd.getText().toString().trim();

                if (TextUtils.isEmpty(sNewPass)) {
                    new AlertDialog.Builder(ResetPassword_Act.this)
                            .setMessage("Please Enter your New Password")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                }

                if (TextUtils.isEmpty(sConfPass)) {
                    new AlertDialog.Builder(ResetPassword_Act.this)
                            .setMessage("Please Enter your Confirm Password")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                }
                if (!(etNewPasswd.getText().toString().equals(etConfirmPasswd.getText().toString()))) {
                    new AlertDialog.Builder(ResetPassword_Act.this)
                            .setMessage("Password Entered is not Matching")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                }
                if (Utils.CheckInternetConnection(ResetPassword_Act.this)) {
                    if (!mobile.equals(null)) {
                        resetPassword(sNewPass, sConfPass, mobile);
                    } else {
                        resetPassword(sNewPass, sConfPass, email);
                    }
                } else {
                    Toast.makeText(ResetPassword_Act.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetPassword(String sNewPass, String sConfPass, String loginid) {
        try {
            progressdialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        final ResetPassModel reset_password = new ResetPassModel(sNewPass, sConfPass, loginid);
        Call<ResetPassModel> calledu = apiInterface.resetPassword(reset_password);
        calledu.enqueue(new Callback<ResetPassModel>() {
            @Override
            public void onResponse(Call<ResetPassModel> calledu, Response<ResetPassModel> response) {
                final ResetPassModel resource = response.body();
                if (resource.status.equals("success")) {

                    Toast.makeText(ResetPassword_Act.this, resource.message, Toast.LENGTH_LONG).show();
                    Intent intentLogin = new Intent(ResetPassword_Act.this, Login_Act.class);
                    intentLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentLogin);

                } else if (resource.status.equals("failure")) {

                    Toast.makeText(ResetPassword_Act.this, resource.message, Toast.LENGTH_LONG).show();
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResetPassModel> calledu, Throwable t) {
                calledu.cancel();
            }
        });
    }


}
