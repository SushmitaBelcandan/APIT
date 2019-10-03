package com.example.shushmita.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

public class ResetPassModel {

    @SerializedName("new_password")
    public String new_password;

    @SerializedName("confirm_password")
    public String confirm_password;

    @SerializedName("email_or_mobile")
    public String email_or_mobile;

    public ResetPassModel(String newPass, String confPass, String emailORmobile) {
        this.new_password = newPass;
        this.confirm_password = confPass;
        this.email_or_mobile = emailORmobile;
    }

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

}
