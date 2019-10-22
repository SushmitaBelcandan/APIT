package com.apinnovations.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

public class SignUpResendOtp {

    @SerializedName("mobileno")
    public String mobileno;

    public SignUpResendOtp(String mobileNo)
    {
        this.mobileno = mobileNo;
    }

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

}
