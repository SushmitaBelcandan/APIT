package com.apinnovations.apit.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apinnovations.apit.adapters.Utils;
import com.apinnovations.apit.retrofit_models.APIClient;
import com.apinnovations.apit.retrofit_models.APIInterface;
import com.apinnovations.apit.retrofit_models.ForgotPassModel;
import com.apinnovations.apit.retrofit_models.VerifyForgotOtpModel;
import com.apinnovations.apit.R;
import com.apinnovations.apit.sms.AppSignatureHashHelper;
import com.apinnovations.apit.sms.MySMSBroadcastReceiver;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyForgotOtp_Act extends AppCompatActivity implements TextWatcher, View.OnKeyListener,
        View.OnFocusChangeListener,
        MySMSBroadcastReceiver.OTPReceiveListener {

    ProgressDialog progressdialog;
    APIInterface apiInterface;

    Toolbar toolbar;
    private Button btnVerifyOtp;
    private TextView tvResendOtp;
    private String mobile, email;
    private EditText etOtp1, etOtp2, etOtp3, etOtp4;
    private String strOtp1, strOtp2, strOtp3, strOtp4;
    private LinearLayout llResendOtp;
    private String finalOtp;
    private int whoHasFocus;
    private String str_mobile, str_email;
    char[] code = new char[4];//Store the digits in charArray.
    private MySMSBroadcastReceiver smsReceiver;
    private String str_otp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_forgot_otp);


        AppSignatureHashHelper appSignatureHashHelper = new AppSignatureHashHelper(this);
        // This code requires one time to get Hash keys do comment and share key
        //  Log.i("HashKey", "HashKey: " + appSignatureHashHelper.getAppSignatures().get(0));


        progressdialog = new ProgressDialog(VerifyForgotOtp_Act.this);
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
        startSMSListener();
        etOtp1 = findViewById(R.id.etOtp1);
        etOtp2 = findViewById(R.id.etOtp2);
        etOtp3 = findViewById(R.id.etOtp3);
        etOtp4 = findViewById(R.id.etOtp4);
        etOtp1.requestFocus();//Left digit gets focus after adding of fragment in Container

        btnVerifyOtp = findViewById(R.id.btnVerifyOtp);


        llResendOtp = findViewById(R.id.llResendOtp);
        tvResendOtp = findViewById(R.id.tvResendOtp);
        SpannableString spannableStrLogin = new SpannableString("Re-Send OTP");
        spannableStrLogin.setSpan(new UnderlineSpan(), 0, spannableStrLogin.length(), 0);
        tvResendOtp.setText(spannableStrLogin);
        llResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile = getIntent().getExtras().getString("Mobile_1", "defaultKey");
                email = getIntent().getExtras().getString("Email_1", "defaultKey");

                if (Utils.CheckInternetConnection(VerifyForgotOtp_Act.this)) {
                    if (!mobile.equals(null)) {
                        forgetPassword(mobile);
                        startSMSListener();
                    } else {
                        forgetPassword(email);
                    }
                } else {
                    Toast.makeText(VerifyForgotOtp_Act.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void otpVerify(String finalOtp, final String loginid) {
        try {
            progressdialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        final VerifyForgotOtpModel verifyOTP = new VerifyForgotOtpModel(finalOtp, loginid);
        Call<VerifyForgotOtpModel> calledu = apiInterface.verifyOtp(verifyOTP);
        calledu.enqueue(new Callback<VerifyForgotOtpModel>() {
            @Override
            public void onResponse(Call<VerifyForgotOtpModel> calledu, Response<VerifyForgotOtpModel> response) {
                final VerifyForgotOtpModel resource = response.body();
                if (resource.status.equals("success")) {

                    Intent intentResetPassword = new Intent(VerifyForgotOtp_Act.this, ResetPassword_Act.class);
                    intentResetPassword.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (loginid.matches("[0-9]+")) {
                        str_mobile = loginid;
                        str_email = null;
                        intentResetPassword.putExtra("Mobile1", loginid);
                    } else {
                        if (android.util.Patterns.EMAIL_ADDRESS.matcher(loginid).matches()) {
                            str_email = loginid;
                            str_mobile = null;
                            intentResetPassword.putExtra("Email1", loginid);
                        }
                    }
                    startActivity(intentResetPassword);

                } else if (resource.status.equals("failure")) {

                    Toast.makeText(VerifyForgotOtp_Act.this, resource.message, Toast.LENGTH_LONG).show();
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<VerifyForgotOtpModel> calledu, Throwable t) {
                calledu.cancel();
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
                        Intent intentVerifyOtp = new Intent(VerifyForgotOtp_Act.this, VerifyForgotOtp_Act.class);
                        intentVerifyOtp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        if (strInput.matches("[0-9]+")) {
                            intentVerifyOtp.putExtra("Mobile_1", fogotDatum.mobileno);
                        } else {
                            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(strInput).matches()) {
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        switch (whoHasFocus) {
            case 1:
                if (!etOtp1.getText().toString().isEmpty()) {
                    code[0] = etOtp1.getText().toString().charAt(0);
                    etOtp2.requestFocus();
                }
                break;

            case 2:
                if (!etOtp2.getText().toString().isEmpty()) {
                    code[1] = etOtp2.getText().toString().charAt(0);
                    etOtp2.requestFocus();
                }
                break;

            case 3:
                if (!etOtp3.getText().toString().isEmpty()) {
                    code[2] = etOtp3.getText().toString().charAt(0);
                    etOtp4.requestFocus();
                }
                break;

            case 4:
                if (!etOtp4.getText().toString().isEmpty()) {
                    code[3] = etOtp4.getText().toString().charAt(0);
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.etOtp1:
                whoHasFocus = 1;
                break;

            case R.id.etOtp2:
                whoHasFocus = 2;
                break;

            case R.id.etOtp3:
                whoHasFocus = 3;
                break;

            case R.id.etOtp4:
                whoHasFocus = 4;
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                switch (v.getId()) {
                    case R.id.etOtp2:
                        if (etOtp2.getText().toString().isEmpty())
                            etOtp1.requestFocus();
                        break;

                    case R.id.etOtp3:
                        if (etOtp3.getText().toString().isEmpty())
                            etOtp2.requestFocus();
                        break;

                    case R.id.etOtp4:
                        if (etOtp4.getText().toString().isEmpty())
                            etOtp3.requestFocus();
                        break;

                    default:
                        break;
                }
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);
        }
    }


    /**
     * Starts SmsRetriever, which waits for ONE matching SMS message until timeout
     * (5 minutes). The matching SMS message will be sent via a Broadcast Intent with
     * action SmsRetriever#SMS_RETRIEVED_ACTION.
     */
    private void startSMSListener() {
        try {
            smsReceiver = new MySMSBroadcastReceiver();
            smsReceiver.setOTPListener(this);

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
            this.registerReceiver(smsReceiver, intentFilter);

            SmsRetrieverClient client = SmsRetriever.getClient(this);

            Task<Void> task = client.startSmsRetriever();
            task.addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    // API successfully started
                    //  Log.d("otp", String.valueOf(otp));
                }
            });

            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Fail to start API
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onOTPReceived(String otp) {
        showToast("OTP Received: " + otp);
        String otp_arr[] = otp.split(" ");
        str_otp = otp_arr[otp_arr.length - 2];
        if (str_otp != null) {
            int int_otp = Integer.parseInt(str_otp);
            int n = 0;
            for (int i = 0; i < str_otp.length(); i++) {
                n = int_otp % 10;
                Log.e("---n---", String.valueOf(n));
                int_otp = int_otp / 10;

                if (i == 0) {
                    etOtp4.setText(String.valueOf(n));
                } else if (i == 1) {
                    etOtp3.setText(String.valueOf(n));
                } else if (i == 2) {
                    etOtp2.setText(String.valueOf(n));
                } else {
                    etOtp1.setText(String.valueOf(n));
                }

            }

            btnVerifyOtp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mobile = getIntent().getExtras().getString("Mobile_1", "defaultKey");
                    email = getIntent().getExtras().getString("Email_1", "defaultKey");

                    strOtp1 = etOtp1.getText().toString().trim();
                    strOtp2 = etOtp2.getText().toString().trim();
                    strOtp3 = etOtp3.getText().toString().trim();
                    strOtp4 = etOtp4.getText().toString().trim();

                    finalOtp = strOtp1 + strOtp2 + strOtp3 + strOtp4;

                    if (Utils.CheckInternetConnection(VerifyForgotOtp_Act.this)) {
                        if (!mobile.equals(null)) {
                            otpVerify(finalOtp, mobile);
                        } else {
                            otpVerify(finalOtp, email);
                        }
                    } else {
                        Toast.makeText(VerifyForgotOtp_Act.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(VerifyForgotOtp_Act.this, "Please enter valid OTP", Toast.LENGTH_SHORT).show();
        }
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);
            smsReceiver = null;
        }
    }

    @Override
    public void onOTPTimeOut() {
        showToast("OTP Time out");
    }

    @Override
    public void onOTPReceivedError(String error) {
        showToast(error);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
