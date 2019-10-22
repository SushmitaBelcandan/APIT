package com.apinnovations.apit.retrofit_models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface POSTALInterface {

    @GET("pincode/{input}")
    Call<PincodeModel> getAddress(@Path("input") String input);


}
