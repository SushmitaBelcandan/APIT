package com.apinnovations.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PincodeModel {

    @SerializedName("Message")
    public String Message;

    @SerializedName("Status")
    public String Status;

    @SerializedName("PostOffice")
    public List<PincodeDatum> PostOffice = null;


    public class PincodeDatum {

        @SerializedName("Name")
        public String Name;

        @SerializedName("Description")
        public String Description;

        @SerializedName("BranchType")
        public String BranchType;

        @SerializedName("DeliveryStatus")
        public String DeliveryStatus;

        @SerializedName("Taluk")
        public String Taluk;

        @SerializedName("Circle")
        public String Circle;

        @SerializedName("District")
        public String District;

        @SerializedName("Division")
        public String Division;

        @SerializedName("Region")
        public String Region;

        @SerializedName("State")
        public String State;

        @SerializedName("Country")
        public String Country;

    }

}
