package com.apinnovations.apit.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.apinnovations.apit.adapters.SpinnerAdapter;
import com.apinnovations.apit.adapters.Utils;
import com.apinnovations.apit.reference.SessionManager;
import com.apinnovations.apit.retrofit_models.APIClient;
import com.apinnovations.apit.retrofit_models.APIInterface;
import com.apinnovations.apit.retrofit_models.CustTypeModel;
import com.apinnovations.apit.retrofit_models.SignUpModel;
import com.apinnovations.apit.retrofit_models.SignUpResendOtp;
import com.apinnovations.apit.retrofit_models.SignUpVerifyModel;
import com.apinnovations.apit.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUp_Act extends AppCompatActivity {

    SessionManager session;
    ProgressDialog progressdialog;
    APIInterface apiInterface;
    Toolbar toolbar;
    private ImageView ivShowPass, ivHidePass, ivConfirmHidePass, ivConfirmShowPass;
    private EditText etNewPasswd, etConfirmPaswd;
    private Button btnNext;
    private EditText etUsrName, etGeoDetails, etGstNumber, etEmailId, etMobileNumber;
    private Spinner spnr_custType;
    private String strUsrName, strGeoDetails, strGstNo, strEmailId, strMobileNumber, strNewPass, strConfirmPass, strCustType;
    private String strCustTypeName;
    private int strCustTypeId;
    int hidingItemIndex = 0;
    private String strOtp1, strOtp2, strOtp3, strOtp4, str1Otp;
    private String str1Name, str1Mobile, str1Email, str1GeoDetails, str1GstNo, str1CustId, str1Pass, str1ProfPic;
    Intent intent = getIntent();
    private String address = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        session = new SessionManager(getApplicationContext());
        progressdialog = new ProgressDialog(SignUp_Act.this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.setCancelable(false);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        ivShowPass = findViewById(R.id.ivShowPass);
        ivHidePass = findViewById(R.id.ivHidePass);
        etNewPasswd = findViewById(R.id.etNewPasswd);
        etConfirmPaswd = findViewById(R.id.etConfirmPaswd);
        ivConfirmShowPass = findViewById(R.id.ivConfirmShowPass);
        ivConfirmHidePass = findViewById(R.id.ivConfirmHidePass);
        btnNext = findViewById(R.id.btnNext);

        etUsrName = findViewById(R.id.etUsrName);
        etGeoDetails = findViewById(R.id.etGeoDetails);
        etGstNumber = findViewById(R.id.etGstNumber);
        etEmailId = findViewById(R.id.etEmailId);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        spnr_custType = findViewById(R.id.spnr_CustList);

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
        getCustType();//get customer list
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
                callSignUp();
            }
        });

        //----------------------------------Google Places AutoCompleteLocation----------------------
     etGeoDetails.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intentLocation = new Intent(SignUp_Act.this, LocationSearch.class);
             intentLocation.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(intentLocation);
         }
     });
        //------------------------------------------------------------------------------------------

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                address = data.getStringExtra("Address");

                if(getIntent().getExtras() != null)
                {
                    etGeoDetails.setText(address);
                }
                else {
                    etGeoDetails.setText("  ");
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
    public void signUpOTPAlert(final String str_usr_name, final String str_mobile_number, final String str_email_id,
                               final String cust_type_id, final String str_geo_details,
                               final String str_gst_no, final String str_pass, final String profile_pic) {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View promptsView = LayoutInflater.from(this).inflate(R.layout.signup_otp_alert, viewGroup, false);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(SignUp_Act.this, R.style.AlertDialogStyle);
        alertBuilder.setView(promptsView);

        final EditText etOtp1 = promptsView.findViewById(R.id.etOtp1);
        final EditText etOtp2 = promptsView.findViewById(R.id.etOtp2);
        final EditText etOtp3 = promptsView.findViewById(R.id.etOtp3);
        final EditText etOtp4 = promptsView.findViewById(R.id.etOtp4);
        Button btnSubmitOtp = promptsView.findViewById(R.id.btnSubmitOtp);
        final TextView tvResendOtp = promptsView.findViewById(R.id.tvResendOtp);
        SpannableString spannable = new SpannableString("Re-Send OTP");
        spannable.setSpan(new UnderlineSpan(), 0, spannable.length(), 0);
        tvResendOtp.setText(spannable);

        final AlertDialog alertDialog = alertBuilder.create();
        btnSubmitOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strOtp1 = etOtp1.getText().toString();
                strOtp2 = etOtp2.getText().toString();
                strOtp3 = etOtp3.getText().toString();
                strOtp4 = etOtp4.getText().toString();
                str1Otp = strOtp1 + strOtp2 + strOtp3 + strOtp4;
                //-----------------------------------------------------------------------------verify otp---------------
                if (Utils.CheckInternetConnection(getApplicationContext())) {
                    SignUpVerifyModel suvm = new SignUpVerifyModel(str_usr_name, str_mobile_number, str_email_id,
                            cust_type_id, str_geo_details, str_gst_no, str_pass, profile_pic, str1Otp);
                    Call<SignUpVerifyModel> signUpVerifyModelCall = apiInterface.setOTP(suvm);
                    signUpVerifyModelCall.enqueue(new Callback<SignUpVerifyModel>() {
                        @Override
                        public void onResponse(Call<SignUpVerifyModel> call, Response<SignUpVerifyModel> response) {

                            SignUpVerifyModel resourceSgnUpVerfy = response.body();
                            if (resourceSgnUpVerfy.status.equals("success")) {
                                Toast.makeText(SignUp_Act.this, resourceSgnUpVerfy.message, Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                                Intent intentLogin = new Intent(SignUp_Act.this, DashBoard_Act.class);
                                intentLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intentLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intentLogin);
                            } else {
                                Toast.makeText(SignUp_Act.this, resourceSgnUpVerfy.message, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SignUpVerifyModel> call, Throwable t) {

                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //----------------------------------------------------------------------resend otp---------------------------------
        tvResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.CheckInternetConnection(getApplicationContext())) {
                    SignUpResendOtp signUpResendOtp = new SignUpResendOtp(str_mobile_number);
                    Call<SignUpResendOtp> signUpResendOtpCall = apiInterface.getOtp(signUpResendOtp);
                    signUpResendOtpCall.enqueue(new Callback<SignUpResendOtp>() {
                        @Override
                        public void onResponse(Call<SignUpResendOtp> call, Response<SignUpResendOtp> response) {

                            SignUpResendOtp resourceSgnUpVerfy = response.body();
                            if (resourceSgnUpVerfy.status.equals("success")) {
                                Toast.makeText(SignUp_Act.this, resourceSgnUpVerfy.message, Toast.LENGTH_SHORT).show();
                               /* Toast.makeText(SignUp_Act.this, resourceSgnUpVerfy.message, Toast.LENGTH_SHORT).show();
                                Intent intentLogin = new Intent(SignUp_Act.this, Login_Act.class);
                                intentLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intentLogin);*/
                            } else {
                                Toast.makeText(SignUp_Act.this, resourceSgnUpVerfy.message, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SignUpResendOtp> call, Throwable t) {
                            call.cancel();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.show();
    }

    public void callSignUp() {

        strUsrName = etUsrName.getText().toString();
        strGeoDetails = etGeoDetails.getText().toString();
        strGstNo = etGstNumber.getText().toString();
        strEmailId = etEmailId.getText().toString();
        strMobileNumber = etMobileNumber.getText().toString();
        strNewPass = etNewPasswd.getText().toString();
        strConfirmPass = etConfirmPaswd.getText().toString();

        if (validateInput(strUsrName, strGeoDetails, strEmailId, strCustTypeId, strMobileNumber, strNewPass, strConfirmPass)) {
            callSignUpApi(strUsrName, strGeoDetails, strGstNo, strEmailId, strCustTypeId, strMobileNumber, strConfirmPass);

        } else {
            Toast.makeText(SignUp_Act.this, "Invalidate Data", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean validateInput(String sUsrName, String sGeoDetails, String sEmail, int sCustTypeId,
                                  String sMobileNumber, String sNewPass, String sConfirmPass) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (sUsrName == null || sUsrName.trim().length() == 0) {
            etUsrName.requestFocus();
            etUsrName.setError("Invalid User Name");
            return false;
        }
        if (sGeoDetails == null || sGeoDetails.trim().length() == 0) {
            etGeoDetails.requestFocus();
            etGeoDetails.setError("Invalid Address");
            return false;
        }
        if (!(sEmail.matches(emailPattern) && sEmail.length() > 0)) {
            etEmailId.requestFocus();
            etEmailId.setError("Please enter a Valid Email Id");
            return false;
        }
        if (sCustTypeId == 0) {
            TextView errorText = (TextView) spnr_custType.getSelectedView();
            errorText.requestFocus();
            errorText.setError("Please Select Customer Type");
            return false;
        }
        if (sMobileNumber == null || sMobileNumber.trim().length() == 0) {
            etMobileNumber.requestFocus();
            etMobileNumber.setError("Please enter Mobile Number");
            return false;
        }
        if (!(sMobileNumber.matches("[0-9]+"))) {
            etMobileNumber.requestFocus();
            etMobileNumber.setError("Invalid Mobile Number");
            return false;
        }
        if (sNewPass == null || sNewPass.trim().length() == 0) {
            etNewPasswd.requestFocus();
            etNewPasswd.setError("Please enter a Valid Password");
        }

        if (sNewPass.trim().length() < 4) {
            etNewPasswd.requestFocus();
            etNewPasswd.setError("Password should not be less than 4 digit");
            return false;
        }
        if (sConfirmPass == null || sConfirmPass.trim().length() == 0) {
            etConfirmPaswd.requestFocus();
            etConfirmPaswd.setError("Please enter a Valid Password");
        }

        if (sConfirmPass.trim().length() < 4) {
            etConfirmPaswd.requestFocus();
            etConfirmPaswd.setError("Password should not be less than 4 digit");
            return false;
        }

        if (!(sNewPass.equals(sConfirmPass))) {
            etConfirmPaswd.requestFocus();
            etConfirmPaswd.setError("Password mismatch");
            return false;
        }
        return true;
    }

    public void callSignUpApi(String str_usr_name, String str_geo_details, String str_gst_no, String str_email_id,
                              int cust_type_id, String str_mobile_number, String str_confirm_pass) {

        try {
            progressdialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Utils.CheckInternetConnection(getApplicationContext())) {

            String strCustId = String.valueOf(cust_type_id);

            final SignUpModel signUpModel = new SignUpModel(str_usr_name, str_mobile_number, str_email_id, strCustId,
                    str_geo_details, str_gst_no, str_confirm_pass, "");

            Call<SignUpModel> signUpModelCall = apiInterface.userSignUp(signUpModel);
            signUpModelCall.enqueue(new Callback<SignUpModel>() {
                @Override
                public void onResponse(Call<SignUpModel> call, Response<SignUpModel> response) {
                    SignUpModel signUpResources = response.body();
                    if (signUpResources.status.equals("success")) {

                        List<SignUpModel.SignUpResultDatum> signUpList = signUpResources.result;
                        for (SignUpModel.SignUpResultDatum signUpDatum : signUpList) {

                            //user name -------------------------------------
                            if (signUpDatum.user_name != null && !signUpDatum.user_name.isEmpty() &&
                                    !signUpDatum.user_name.equals("null")) {
                                str1Name = signUpDatum.user_name;
                            } else {
                                str1Name = null;
                            }
                            //mobile number--------------------------------------
                            if (signUpDatum.mobileno != null && !signUpDatum.mobileno.isEmpty() &&
                                    !signUpDatum.mobileno.equals("null")) {
                                str1Mobile = signUpDatum.mobileno;
                            } else {
                                str1Mobile = null;
                            }
                            //email-----------------------------------------------
                            if (signUpDatum.email != null && !signUpDatum.email.isEmpty() &&
                                    !signUpDatum.email.equals("null")) {
                                str1Email = signUpDatum.email;
                            } else {
                                str1Email = null;
                            }
                            //Customer type id-------------------------------------
                            if (signUpDatum.customer_type_id != null && !signUpDatum.customer_type_id.isEmpty() &&
                                    !signUpDatum.customer_type_id.equals("null")) {
                                str1CustId = signUpDatum.customer_type_id;
                            } else {
                                str1CustId = null;
                            }
                            //Geo Details--------------------------------------------
                            if (signUpDatum.geo_details != null && !signUpDatum.geo_details.isEmpty() &&
                                    !signUpDatum.geo_details.equals("null")) {
                                str1GeoDetails = signUpDatum.geo_details;
                            } else {
                                str1GeoDetails = null;
                            }
                            //GST Number---------------------------------------------
                            if (signUpDatum.gst_no != null && !signUpDatum.gst_no.isEmpty() &&
                                    !signUpDatum.gst_no.equals("null")) {
                                str1GstNo = signUpDatum.gst_no;
                            } else {
                                str1GstNo = null;
                            }
                            //Password---------------------------------------------------
                            if (signUpDatum.password != null && !signUpDatum.password.isEmpty() &&
                                    !signUpDatum.password.equals("null")) {
                                str1Pass = signUpDatum.password;
                            } else {
                                str1Pass = null;
                            }
                            //Profile Picture-----------------------------------------------
                            if (signUpDatum.profilepic != null && !signUpDatum.profilepic.isEmpty() &&
                                    !signUpDatum.profilepic.equals("null")) {
                                str1ProfPic = signUpDatum.profilepic;
                            } else {
                                str1ProfPic = null;
                            }

                            signUpOTPAlert(signUpDatum.user_name, signUpDatum.mobileno, signUpDatum.email,
                                    signUpDatum.customer_type_id, signUpDatum.geo_details, signUpDatum.gst_no, signUpDatum.password,
                                    signUpDatum.profilepic);
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), signUpResources.message, Toast.LENGTH_SHORT).show();
                    }
                    progressdialog.dismiss();
                }

                @Override
                public void onFailure(Call<SignUpModel> call, Throwable t) {
                    call.cancel();
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "Please check internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void getCustType() {
        if (Utils.CheckInternetConnection(getApplicationContext())) {
            Call<CustTypeModel> custTypeModelCall = apiInterface.custType();
            custTypeModelCall.enqueue(new Callback<CustTypeModel>() {
                @Override
                public void onResponse(Call<CustTypeModel> call, Response<CustTypeModel> response) {
                    CustTypeModel custTypeResource = response.body();
                    if (custTypeResource.status.equals("success")) {

                        List<CustTypeModel.TypeListDatum> datumListObj = custTypeResource.response;
                        final ArrayList<String> custTypeNameArr = new ArrayList<>();
                        final ArrayList<Integer> custTypeIdArr = new ArrayList<>();
                        custTypeNameArr.add("Select Customer Type");
                        custTypeIdArr.add(0);

                        for (CustTypeModel.TypeListDatum custTypeList : datumListObj) {

                            custTypeNameArr.add(custTypeList.customertype);
                            custTypeIdArr.add(custTypeList.customer_type_id);

                        }

                        spnr_custType.setAdapter(new SpinnerAdapter(getApplicationContext(), custTypeNameArr, custTypeIdArr, hidingItemIndex));
                        spnr_custType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {

                                strCustTypeName = custTypeNameArr.get(position); //get selected cust name
                                strCustTypeId = custTypeIdArr.get(position); //get selected cust type id

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                strCustTypeId = 0;
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "No data Available", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CustTypeModel> call, Throwable t) {
                    call.cancel();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onResume() {
        super.onResume();

    }

}