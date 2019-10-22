package com.apinnovations.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaddysAgeListModel {

    @SerializedName("status")
    public String status;

    @SerializedName("response")
    public List<PaddysAgeDatum> response = null;

    public class PaddysAgeDatum
    {
        @SerializedName("paddyage_id")
        public int paddyage_id;

        @SerializedName("paddy_age")
        public String paddy_age;

    }
}
