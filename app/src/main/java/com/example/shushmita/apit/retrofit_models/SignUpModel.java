package com.example.shushmita.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignUpModel {

    @SerializedName("user_name")
    public String user_name;

    @SerializedName("mobileno")
    public String mobileno;

    @SerializedName("email")
    public String email;

    @SerializedName("customer_type_id")
    public String customer_type_id;

    @SerializedName("geo_details")
    public String geo_details;

    @SerializedName("gst_no")
    public String gst_no;

    @SerializedName("password")
    public String password;

    @SerializedName("profilepic")
    public String profilepic;

    public SignUpModel(String strUsrName, String strPhone, String strEmail, String strCustTypeId,
                       String strGeoDetails, String strGstNo, String strPass, String strProfilePic)
    {
        this.user_name = strUsrName;
        this.mobileno = strPhone;
        this.email = strEmail;
        this.customer_type_id = strCustTypeId;
        this.geo_details = strGeoDetails;
        this.gst_no = strGstNo;
        this.password = strPass;
        this.profilepic = strProfilePic;
    }

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

    @SerializedName("result")
    public List<SignUpResultDatum> result = null;

    public class SignUpResultDatum {

        @SerializedName("user_name")
        public String user_name;

        @SerializedName("mobileno")
        public String mobileno;

        @SerializedName("email")
        public String email;

        @SerializedName("customer_type_id")
        public String customer_type_id;

        @SerializedName("geo_details")
        public String geo_details;

        @SerializedName("gst_no")
        public String gst_no;

        @SerializedName("password")
        public String password;

        @SerializedName("profilepic")
        public String profilepic;

        @SerializedName("user_id")
        public int user_id;
    }
}
