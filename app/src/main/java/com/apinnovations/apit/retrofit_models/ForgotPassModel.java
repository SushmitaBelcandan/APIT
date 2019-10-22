package com.apinnovations.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForgotPassModel {

    @SerializedName("email_or_mobile")
    public String email_or_mobile;

    public ForgotPassModel(String login_id) {
        this.email_or_mobile = login_id;
    }

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

    @SerializedName("result")
    public List<ForgotPassDatum> resultData = null;

    public class ForgotPassDatum {

        @SerializedName("user_id")
        public String user_id;

        @SerializedName("user_name")
        public String user_name;

        @SerializedName("email")
        public String email;

        @SerializedName("customer_type_id")
        public String customer_type;

        @SerializedName("mobileno")
        public String mobileno;

        @SerializedName("flag")
        public String flag;

        @SerializedName("profilepic")
        public String profilepic;

    }


}
