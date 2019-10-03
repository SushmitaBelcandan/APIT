package com.example.shushmita.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

public class PostEnquiryDataModel {

    @SerializedName("user_id")
    public String user_id;

    @SerializedName("process_id")
    public String  process_id;

    @SerializedName("process_image_id")
    public String  process_image_id;

    @SerializedName("first_name")
    public String  first_name;

    @SerializedName("contact_person")
    public String contact_person;

    @SerializedName("phone")
    public String phone;

    @SerializedName("country")
    public String  country;

    @SerializedName("pincode")
    public String  pincode;

    @SerializedName("state")
    public String  state;

    @SerializedName("district")
    public String  district;

    @SerializedName("taluk")
    public String  taluk;

    @SerializedName("village")
    public String village;

    @SerializedName("gst_no")
    public String  gst_no;

    @SerializedName("soil_capacity")
    public String  soil_capacity;

    @SerializedName("wind_speed")
    public String wind_speed;

    @SerializedName("rain_fall")
    public String rain_fall;

    @SerializedName("age_paddy")
    public String age_paddy ;

    @SerializedName("ave_density")
    public String ave_density ;

    @SerializedName("graindetails")
    public Object graindetails;

}
