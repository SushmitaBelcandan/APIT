package com.example.shushmita.apit.retrofit_models;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("userregister")
    Call<SignUpModel> userSignUp(@Body SignUpModel data);

    @GET("customertype")
    Call<CustTypeModel> custType();

    @POST("verifyregisterotp")
    Call<SignUpVerifyModel> setOTP(@Body SignUpVerifyModel data);

    @GET("aboutus")
    Call<AboutUsModel> getAboutusData();

    @POST("userlogin")
    Call<LoginModel> loginUser(@Body LoginModel data);

    @POST("forgotpassword")
    Call<ForgotPassModel> forgotPass(@Body ForgotPassModel data);

    @POST("verifyotp")
    Call<VerifyForgotOtpModel> verifyOtp(@Body VerifyForgotOtpModel data);

    @POST("savechangedpassword")
    Call<ResetPassModel> resetPassword(@Body ResetPassModel data);

    @GET("countrylist")
    Call<CountryListModel> getCountries();

    @GET("paddyagelist")
    Call<PaddysAgeListModel> getPaddyAgeList();

    @POST("imagelist")
    Call<ImageListModel> getImages(@Body ImageListModel data);

    @POST("enquiry")
    Call<PostEnquiryDataModel> submitEnquiryData(@Body PostEnquiryDataModel data);

}
