package com.example.shushmita.apit.retrofit_models;

import com.google.gson.JsonArray;
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
    public JsonArray graindetails;

    public PostEnquiryDataModel(String usrId, String procsId, String procsImgId, String fName,String contctPerson,String phn,
                                String country, String pincode, String state, String district, String taluk, String village,
                                String gstNo, String soilCap, String windSpeed, String rainFall, String agePaddy, String avgDensity,
                                JsonArray grainDetails)
    {
        this.user_id = usrId;
        this.process_id = procsId;
        this.process_image_id = procsImgId;
        this.first_name = fName;
        this.contact_person  = contctPerson;
        this.phone = phn;
        this.country = country;
        this.pincode = pincode;
        this.state = state;
        this.district = district;
        this.taluk = taluk;
        this.village = village;
        this.gst_no = gstNo;
        this.soil_capacity = soilCap;
        this.wind_speed = windSpeed;
        this.rain_fall = rainFall;
        this.age_paddy = agePaddy;
        this.ave_density = avgDensity;
        this.graindetails = grainDetails;
    }
    @SerializedName("status")
    public String status ;

    @SerializedName("message")
    public String message ;

}
