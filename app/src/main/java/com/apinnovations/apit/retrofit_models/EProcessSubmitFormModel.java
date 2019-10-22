package com.apinnovations.apit.retrofit_models;

import com.google.gson.annotations.SerializedName;

public class EProcessSubmitFormModel {

    @SerializedName("user_ids")
    public String  user_ids;

    @SerializedName("process_processtype_ids")
    public String process_processtype_ids ;

    @SerializedName("process_model_ids")
    public String process_model_ids ;

    @SerializedName("process_dryer_ids")
    public String process_dryer_ids ;

    @SerializedName("no_of_hydration_tanks")
    public String  no_of_hydration_tanks;

    @SerializedName("hydration_tank_capacitys")
    public String  hydration_tank_capacitys;

    @SerializedName("batchwise_dryer_capacitys")
    public String  batchwise_dryer_capacitys;

    @SerializedName("no_of_dryerss")
    public String no_of_dryerss;

    @SerializedName("variety_of_paddys")
    public String variety_of_paddys;

    @SerializedName("age_of_paddys")
    public String  age_of_paddys;

    @SerializedName("pincodes")
    public String pincodes;

    @SerializedName("processing_locations")
    public String  processing_locations;

    @SerializedName("moisture_of_paddys")
    public String  moisture_of_paddys;

    @SerializedName("no_of_presteaming_tanks")
    public String no_of_presteaming_tanks;

    @SerializedName("presteaming_tank_capacitys")
    public String  presteaming_tank_capacitys;

    @SerializedName("no_of_cookers")
    public String  no_of_cookers;

    @SerializedName("cooker_capacitys")
    public String  cooker_capacitys;

    @SerializedName("mixedwise_dryer_capacitys")
    public String  mixedwise_dryer_capacitys;

    @SerializedName("no_of_finalsteaming_tanks")
    public String  no_of_finalsteaming_tanks;

    @SerializedName("finalsteaming_tank_capacitys")
    public String  finalsteaming_tank_capacitys;

    @SerializedName("no_of_poststeaming_tanks")
    public String  no_of_poststeaming_tanks;

    @SerializedName("poststeaming_tank_capacitys")
    public String  poststeaming_tank_capacitys;

    @SerializedName("no_of_streaming_tanks")
    public String  no_of_streaming_tanks;

    @SerializedName("no_of_streaming_tank_capacitys")
    public String  no_of_streaming_tank_capacitys;

    @SerializedName("aging_tankss")
    public String  aging_tankss;

    @SerializedName("aging_tanks_capacitys")
    public String  aging_tanks_capacitys;


    public EProcessSubmitFormModel(String usrId, String procsId, String modelId, String dryerId,
                                   String noHydn, String hydrnTnkCapcty, String batchDryerCapcty,
                                   String noDryers, String vrtyPaddy, String agePaddy, String pincode,
                                   String procesngLocations, String paddyMoisture, String noPreStmngTnk,
                                   String preStmngCapcty, String noCookers, String cookerCapcty,
                                   String mixedDryerCapcty, String noFinalStmngTnk, String finalStmngCapcty,
                                   String noPostStmngTnk, String postStmngTnkCapcty, String noStmngTnk,
                                   String stmngTnkCapcty, String agingTanks, String agingTnkCapcty)
    {
                this.user_ids = usrId;
                this.process_processtype_ids = procsId;
                this.process_model_ids = modelId;
                this.process_dryer_ids = dryerId;
                this.no_of_hydration_tanks = noHydn;
                this.hydration_tank_capacitys = hydrnTnkCapcty;
                this.batchwise_dryer_capacitys = batchDryerCapcty;
                this.no_of_dryerss = noDryers;
                this.variety_of_paddys = vrtyPaddy;
                this.age_of_paddys = agePaddy;
                this.pincodes = pincode;
                this.processing_locations  = procesngLocations;
                this.moisture_of_paddys = paddyMoisture;
                this.no_of_presteaming_tanks = noPreStmngTnk;
                this.presteaming_tank_capacitys = preStmngCapcty;
                this.no_of_cookers = noCookers;
                this.cooker_capacitys = cookerCapcty;
                this.mixedwise_dryer_capacitys = mixedDryerCapcty;
                this.no_of_finalsteaming_tanks = noFinalStmngTnk;
                this.finalsteaming_tank_capacitys = finalStmngCapcty;
                this.no_of_poststeaming_tanks = noPostStmngTnk;
                this.poststeaming_tank_capacitys = postStmngTnkCapcty;
                this.no_of_streaming_tanks = noStmngTnk;
                this.no_of_streaming_tank_capacitys = stmngTnkCapcty;
                this.aging_tankss  = agingTanks;
                this.aging_tanks_capacitys = agingTnkCapcty;
    }

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

}
