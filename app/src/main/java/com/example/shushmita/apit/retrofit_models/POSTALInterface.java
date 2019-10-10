package com.example.shushmita.apit.retrofit_models;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface POSTALInterface {

    @GET("pincode/{input}")
    Call<PincodeModel> getAddress(@Path("input") String input);


}
