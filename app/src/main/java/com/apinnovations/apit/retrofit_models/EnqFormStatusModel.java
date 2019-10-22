package com.apinnovations.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EnqFormStatusModel {

    @SerializedName("user_id")
    public String user_id;

    public EnqFormStatusModel(String usrId)
    {
        this.user_id = usrId;
    }

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

    @SerializedName("response")
    public List<EnqFormRespDatum> response = null;

    public class EnqFormRespDatum{

        @SerializedName("status")
        public String status;

    }


}
