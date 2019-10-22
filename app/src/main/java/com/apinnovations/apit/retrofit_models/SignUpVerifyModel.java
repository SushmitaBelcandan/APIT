package com.apinnovations.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

public class SignUpVerifyModel {

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

    @SerializedName("otp")
    public String otp;

    public SignUpVerifyModel(String userName, String mobileNo, String email, String custTypeId,
                             String geoDetails, String gstNo, String pass, String profilePic, String otp)
    {
        this.user_name = userName;
        this.mobileno = mobileNo;
        this.email = email;
        this.customer_type_id = custTypeId;
        this.geo_details = geoDetails;
        this.gst_no = gstNo;
        this.password = pass;
        this.profilepic = profilePic;
        this.otp = otp;

    }
    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

}