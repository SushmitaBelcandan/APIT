package com.apinnovations.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryListModel {

    @SerializedName("status")
    public String status;

    @SerializedName("response")
    public List<CountryListDatum> response = null;

    public class CountryListDatum {
        @SerializedName("country_id")
        public int country_id;

        @SerializedName("country_name")
        public String country_name;
    }


}
