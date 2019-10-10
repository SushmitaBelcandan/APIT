package com.example.shushmita.apit.activities;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.shushmita.apit.intrfaces.OtpReceivedInterface;
import com.example.shushmita.apit.reference.SmsBroadcastReceiver;
import com.example.shushmita.apit.retrofit_models.APIClient;
import com.example.shushmita.apit.retrofit_models.APIInterface;
import com.example.shushmita.apit.retrofit_models.ForgotPassModel;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword_Act extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        OtpReceivedInterface, GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;
    SmsBroadcastReceiver mSmsBroadcastReceiver;
    private int RESOLVE_HINT = 2;


    APIInterface apiInterface;
    ProgressDialog progressdialog;
    Toolbar toolbar;
    private TextView tvResendOtp;
    private EditText etLoginId;
    private LinearLayout llResendOtp;
    private Button btnSubmit;
    private String strInput;
    private LinearLayout llVerifyOtp, llRequestOtp;
    private LinearLayout llResenVerifyOtp;
    private TextView tvResendVerifyOtp;
    private EditText etOtp1, etOtp2, etOtp3, etOtp4;


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

        mSmsBroadcastReceiver = new SmsBroadcastReceiver();

        //set google api client for hint request
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.CREDENTIALS_API)
                .build();

        mSmsBroadcastReceiver.setOnOtpListeners(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
        getApplicationContext().registerReceiver(mSmsBroadcastReceiver, intentFilter);
        // get mobile number from phone
        getHintPhoneNumber();
        llVerifyOtp = findViewById(R.id.llVerifyOtp);
        llRequestOtp = findViewById(R.id.llRequestOtp);
        llResenVerifyOtp  = findViewById(R.id.llResendVerifyOtp);
        tvResendVerifyOtp = findViewById(R.id.tvResendVerifyOtp);
        etOtp1  = findViewById(R.id.etOtp1);
        etOtp2  = findViewById(R.id.etOtp2);
        etOtp3  = findViewById(R.id.etOtp3);
        etOtp4  = findViewById(R.id.etOtp4);

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

                        startSMSListener();
                        Intent intentVerifyOtp = new Intent(ForgotPassword_Act.this, VerifyForgotOtp_Act.class);
                        intentVerifyOtp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        if (strInput.matches("[0-9]+")) {
                            intentVerifyOtp.putExtra("Mobile_1", fogotDatum.mobileno);
                        } else {
                            if (android.util.Patterns.EMAIL_ADDRESS.matcher(strInput).matches()) {
                                intentVerifyOtp.putExtra("Email_1", fogotDatum.email);
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

    @Override
    public void onOtpReceived(String otp) {
        Toast.makeText(this, "Otp Received " + otp, Toast.LENGTH_LONG).show();
        etOtp1.setText(otp.charAt(0));
        etOtp2.setText(otp.charAt(1));
        etOtp3.setText(otp.charAt(2));
        etOtp4.setText(otp.charAt(3));
    }

    @Override
    public void onOtpTimeout() {
        Toast.makeText(this, "Time out, please resend", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void startSMSListener() {
        SmsRetrieverClient mClient = SmsRetriever.getClient(this);
        Task<Void> mTask = mClient.startSmsRetriever();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override public void onSuccess(Void aVoid) {
                llRequestOtp.setVisibility(View.GONE);
                llVerifyOtp.setVisibility(View.VISIBLE);
                Toast.makeText(ForgotPassword_Act.this, "SMS Retriever starts", Toast.LENGTH_LONG).show();
            }
        });
        mTask.addOnFailureListener(new OnFailureListener() {
            @Override public void onFailure(@NonNull Exception e) {
                Toast.makeText(ForgotPassword_Act.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getHintPhoneNumber() {
        HintRequest hintRequest = new HintRequest.Builder()
                        .setPhoneNumberIdentifierSupported(true)
                        .build();
        PendingIntent mIntent = Auth.CredentialsApi.getHintPickerIntent(mGoogleApiClient, hintRequest);
        try {
            startIntentSenderForResult(mIntent.getIntentSender(), RESOLVE_HINT, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Result if we want hint number
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                    // credential.getId();  <-- will need to process phone number string
                    Toast.makeText(getApplicationContext(),"get credential"+credential.getId(),Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
}
