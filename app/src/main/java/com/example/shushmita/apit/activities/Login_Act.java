package com.example.shushmita.apit.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shushmita.apit.R;
import com.example.shushmita.apit.reference.SessionManager;
import com.example.shushmita.apit.retrofit_models.APIClient;
import com.example.shushmita.apit.retrofit_models.APIInterface;
import com.example.shushmita.apit.retrofit_models.LoginModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Act extends AppCompatActivity {


    ProgressDialog progressdialog;
    APIInterface apiInterface;
    SessionManager sessionManager;

    private TextView tvForgotPasswd,tvCreateAccount;
    private EditText etLoginId, etPassword;
    private Button btnSignIn;
    private String strLoginId, strPass;
    private LinearLayout llSignUp, llForgotPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);

        progressdialog = new ProgressDialog(Login_Act.this);
        progressdialog.setMessage("Please Wait....");

        apiInterface = APIClient.getClient().create(APIInterface.class);
        sessionManager = new SessionManager(getApplicationContext());


        etLoginId = findViewById(R.id.etLoginId);
        etPassword = findViewById(R.id.etPassword);

        llSignUp = findViewById(R.id.llSignUp);
        llForgotPassword = findViewById(R.id.llForgotPassword);

        tvForgotPasswd = findViewById(R.id.tvForgotPasswd);
        SpannableString spannableStrForgotPasswd = new SpannableString("Forgot Password");
        spannableStrForgotPasswd.setSpan(new UnderlineSpan(),0,spannableStrForgotPasswd.length(),0);
        tvForgotPasswd.setText(spannableStrForgotPasswd);

        tvCreateAccount = findViewById(R.id.tvCreateAccount);
        SpannableString spannableStrSignUp = new SpannableString("Create an Account");
        spannableStrSignUp.setSpan(new UnderlineSpan(),0,spannableStrSignUp.length(),0);
        tvCreateAccount.setText(spannableStrSignUp);

        llSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUp = new Intent(Login_Act.this,SignUp_Act.class);
                intentSignUp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentSignUp);
            }
        });

        llForgotPassword.setOnClickListener(new View.OnClickListener() {
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
                strLoginId = etLoginId.getText().toString();
                strPass = etPassword.getText().toString();
                if (validateLogin(strLoginId, strPass)) {
                    saveLoginData(strLoginId, strPass);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid User Id and Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validateLogin(String loginid, String passwd) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (loginid == null || loginid.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Invalid User Id", Toast.LENGTH_SHORT).show();
            return false;

        } else {
            if (loginid.matches("[0-9]+")) {
                if (loginid.length() < 10 && loginid.length() > 10) {
                    etLoginId.setError("Please Enter valid phone number");
                    etLoginId.requestFocus();
                    return false;
                } else {
                    if (passwd == null || passwd.trim().length() == 0) {
                        etPassword.requestFocus();
                        etPassword.setError("Please enter password");
                        return false;
                    } else {
                        if (passwd.trim().length() < 4) {
                            etPassword.requestFocus();
                            etPassword.setError("Password should not be less than 4 digit");
                            return false;
                        } else {
                            return true;
                        }
                    }

                }
            } else {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(loginid).matches()) {
                    etLoginId.setError("Please Enter valid email");
                    etLoginId.requestFocus();
                    return false;
                } else {
                    if (passwd == null || passwd.trim().length() == 0) {
                        etPassword.requestFocus();
                        etPassword.setError("Please enter password");
                        return false;
                    } else {
                        if (passwd.trim().length() < 4) {
                            etPassword.requestFocus();
                            etPassword.setError("Password should not be less than 4 digit");
                            return false;
                        } else {
                            return true;
                        }
                    }

                }
            }
        }

    }


    private void saveLoginData(String sEmail, String sPassword) {
        try {
            progressdialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        final LoginModel user = new LoginModel(sEmail, sPassword);
        Call<LoginModel> calledu = apiInterface.loginUser(user);
        calledu.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> calledu, Response<LoginModel> response) {
                final LoginModel resource = response.body();
                if (resource.status.equals("success")) {
                    Toast.makeText(getApplicationContext(), resource.message, Toast.LENGTH_LONG).show();
                    List<LoginModel.LoginDatum> datumList = resource.result;
                    for (LoginModel.LoginDatum datum : datumList) {
                        sessionManager.saveLoginData(datum.user_id, datum.user_name,
                                datum.email, datum.customer_type_id, datum.mobileno, datum.profilepic);
                        Intent intentDashBoard = new Intent(Login_Act.this, DashBoard_Act.class);
                        intentDashBoard.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intentDashBoard.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intentDashBoard);
                        finish();

                    }

                } else if (resource.status.equals("failure")) {
                    Toast.makeText(getApplicationContext(), resource.message, Toast.LENGTH_LONG).show();
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<LoginModel> calledu, Throwable t) {
                calledu.cancel();
            }
        });
    }
}
