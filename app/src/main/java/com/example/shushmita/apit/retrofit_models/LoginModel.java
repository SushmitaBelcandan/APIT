package com.example.shushmita.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginModel {

    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    public LoginModel(String usr_email, String usr_pass) {
        this.email = usr_email;
        this.password = usr_pass;
    }

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;


    @SerializedName("result")
    public List<LoginDatum> result = null;

    public class LoginDatum {
        @SerializedName("user_id")
        public String user_id;

        @SerializedName("user_name")
        public String user_name;

        @SerializedName("email")
        public String email;

        @SerializedName("customer_type_id")
        public String customer_type_id;

        @SerializedName("mobileno")
        public String mobileno;

        @SerializedName("profilepic")
        public String profilepic;

    }
}
