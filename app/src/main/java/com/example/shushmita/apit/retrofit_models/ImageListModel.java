package com.example.shushmita.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageListModel {

    @SerializedName("id")
    public String id;

    public ImageListModel(String process_id) {
        this.id = process_id;
    }

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

    @SerializedName("response")
    public List<ImageListDatum> response = null;

    public class ImageListDatum {
        @SerializedName("image_id")
        public int image_id;

        @SerializedName("name")
        public String name;

        @SerializedName("image")
        public String image;
    }
}