package com.example.shushmita.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EProcessImagesModel {

    @SerializedName("processtype_id")
    public String processtype_id;

    @SerializedName("model_id")
    public String model_id;

    @SerializedName("dryer_id")
    public String dryer_id;

    public EProcessImagesModel(String processId, String modelId, String dryerId) {
        this.processtype_id = processId;
        this.model_id = modelId;
        this.dryer_id = dryerId;
    }

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

    @SerializedName("process_id")
    public String process_id;

    @SerializedName("process_processtype_id")
    public String process_processtype_id;

    @SerializedName("process_model_id")
    public String process_model_id;

    @SerializedName("process_dryer_id")
    public String process_dryer_id;

    @SerializedName("data1")
    public List<ImagesEProcessDatum1> data1 = null;

    public class ImagesEProcessDatum1 {

        @SerializedName("image")
        public String image;

        @SerializedName("description")
        public String description;
    }

    @SerializedName("data2")
    public List<ImagesEProcessDatum2> data2 = null;

    public class ImagesEProcessDatum2
    {
        @SerializedName("process_image_id")
        public String process_image_id;

        @SerializedName("process_image_name")
        public String process_image_name;

        @SerializedName("image_color")
        public String image_color;
    }
}
