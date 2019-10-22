package com.apinnovations.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

public class VerifyForgotOtpModel {

    @SerializedName("otp")
    public String otp;

    @SerializedName("email_or_mobile")
    public String email_or_mobile;

    public VerifyForgotOtpModel(String otp, String login_id) {
        this.otp = otp;
        this.email_or_mobile = login_id;
    }

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

}
