package com.example.shushmita.apit.fragment_activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.shushmita.apit.activities.Login_Act;
import com.example.shushmita.apit.adapters.ImageId;
import com.example.shushmita.apit.adapters.Images;
import com.example.shushmita.apit.adapters.PaddyImagesAdapter;
import com.example.shushmita.apit.adapters.SpinnerAdapter;

import android.widget.TextView;
import android.widget.Toast;

import com.example.shushmita.apit.R;
import com.example.shushmita.apit.adapters.Utils;
import com.example.shushmita.apit.intrfaces.SelectImage_Intfc;
import com.example.shushmita.apit.models.PaddyColorsModels;
import com.example.shushmita.apit.models.RemoveGrnVarty_Models;
import com.example.shushmita.apit.reference.GrainsParcelable;
import com.example.shushmita.apit.reference.SessionManager;
import com.example.shushmita.apit.retrofit_models.APIClient;
import com.example.shushmita.apit.retrofit_models.APIInterface;
import com.example.shushmita.apit.retrofit_models.CountryListModel;
import com.example.shushmita.apit.retrofit_models.EnqFormStatusModel;
import com.example.shushmita.apit.retrofit_models.ImageListModel;
import com.example.shushmita.apit.retrofit_models.POSTALClient;
import com.example.shushmita.apit.retrofit_models.POSTALInterface;
import com.example.shushmita.apit.retrofit_models.PaddysAgeListModel;
import com.example.shushmita.apit.retrofit_models.PincodeModel;
import com.example.shushmita.apit.retrofit_models.PostEnquiryDataModel;
import com.example.shushmita.apit.tinydb.TinyDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardFragmentGetaQuote extends Fragment {

    SessionManager session;
    APIInterface apiInterface;
    POSTALInterface postalInterface;
    ConstraintLayout clGetaQuoteContainer;
    private TextView tvSoilBearingCapacity, tvAvgDensity;
    private EditText etSoilBearingCapacity, etAvgDensity;
    private LinearLayout llAddGrainVarities, llAddressDetails;
    private EditText etPincode;
    RecyclerView phvPaddyColor;
    private RadioGroup rdoGrpProcess;
    private RadioButton rdoBtnParboiling, rdoBtnSteamCuring;
    private LinearLayout llPaddyImages;
    public static PlaceHolderView phvGrains;
    private Button btnSubmit;
    private Spinner spnr_country, spnrPaddyAge;
    private LinearLayout llProcess;
    private LinearLayout llMsg;

    private EditText etFName, etContctPersn, etPhnNo, etState, etDistrict, etTaluk, etTown, etGstNo;
    private TextView tvCountry, tvPaddysAge;
    private TextView tvMsg;
    private EditText etAvgRainFall, etMaxWindSpeed, etGrainVarities;
    private String str_grains1, str_row_grains1;
    private String str_fname, str_contct_person, str_phn_no;
    private String str_state, str_district, str_taluk, str_town, str_gst_no;
    private String str_avg_rainfall, str_max_wind_speed, str_grain_varts;
    private String str_soil_brearing_cap, str_avg_density;
    private String str_country, str_pincode, str_age_paddy, str_procs_id, str_procs_img_id;

    private String str1_process_id, str1_img_id;
    private String str1_first_name, str1_contact_person, str1_mobileno, str1_country_id, str1_pincode, str1_state_id;
    private String str1_district_id, str1_taluk_id, str1_village_id, str1_gst_no, str1_soil_bearing, str1_wind_speed;
    private String str1_avg_rainfall, str1_paddy_age, str1_paddy_density;

    ProgressDialog progressdialog;
    public static EditText strGrainsVariety;


    private int strCountryId, strPaddyId;
    private String strCountryName, strPaddyAge;
    int hidingItemIndex = 0;
    private String strProcessId;

    JsonArray datas = null;
    public static ArrayList<String> arrayListSelctedImg;
    private PaddyImagesAdapter adapter;
    ArrayList<Images> imgList;
    ImageId img_id;
    private int imgId;
    private int chk_pos;
    private String grainsVarityName;
    private ArrayList<String> grainsVartyArr;
    private ArrayList<String> sampleArr = new ArrayList<>();
    private ArrayList<Integer> grainsVartyIdArr = new ArrayList<>();
    int countRow = 0;
    TinyDB tinydb;
    ArrayList<String> db_arr_grains = new ArrayList<>();
    public boolean isClickable = true;

    public DashBoardFragmentGetaQuote() {
        //required public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.get_a_quote_frag, container, false);

        progressdialog = new ProgressDialog(getActivity());
        progressdialog.setMessage("Please Wait....");

        apiInterface = APIClient.getClient().create(APIInterface.class);
        postalInterface = POSTALClient.getClient().create(POSTALInterface.class);
        session = new SessionManager(getActivity());
        //getEnquiryFormStatus(session.getUsrId());//maintain form status
        arrayListSelctedImg = new ArrayList<>();
        clGetaQuoteContainer = view.findViewById(R.id.clGetaQuoteContainer);
        tvSoilBearingCapacity = view.findViewById(R.id.tvSoilBearingCapacity);
        tvSoilBearingCapacity.setText(Html.fromHtml("Soil Bearing Capacity ( t/m<sup><small>2</small></sup> )"));

        etSoilBearingCapacity = view.findViewById(R.id.etSoilBearingCapacity);
        etSoilBearingCapacity.setHint(Html.fromHtml("Enter Soil Bearing Capacity in t/m<sup><small>2</small></sup>"));

        etAvgDensity = view.findViewById(R.id.etAvgDensity);
        etAvgDensity.setHint(Html.fromHtml("Enter Average Density of Paddy in kg/m<sup><small>2</small></sup>"));
        tvAvgDensity = view.findViewById(R.id.tvAvgDensity);
        tvAvgDensity.setText(Html.fromHtml("Average Density of Paddy ( kg/m<sup><small>3</small></sup> )"));

        etFName = view.findViewById(R.id.etFName);
        etContctPersn = view.findViewById(R.id.etContctPersn);
        etPhnNo = view.findViewById(R.id.etPhnNo);
        etState = view.findViewById(R.id.etState);
        etDistrict = view.findViewById(R.id.etDistrict);
        etTaluk = view.findViewById(R.id.etTaluk);
        etTown = view.findViewById(R.id.etTown);
        etGstNo = view.findViewById(R.id.etGstNo);
        etAvgRainFall = view.findViewById(R.id.etAvgRainFall);
        etMaxWindSpeed = view.findViewById(R.id.etMaxWindSpeed);
        etGrainVarities = view.findViewById(R.id.etGrainVarities);
        llProcess = view.findViewById(R.id.llProcess);

        tvMsg = view.findViewById(R.id.tvMsg);
        llMsg = view.findViewById(R.id.llMsg);

        tinydb = new TinyDB(getActivity());

        phvGrains = view.findViewById(R.id.phvGrains);

        llAddGrainVarities = view.findViewById(R.id.llAddGrainVarities);
        //-----------------------------------------------------------------------------Add Grains-------------------------------

        llAddGrainVarities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phvGrains.getChildCount() < 10) {
                    phvGrains.addView(new RemoveGrnVarty_Models(getActivity(), phvGrains));
                } else {
                    Toast.makeText(getActivity(), "Limit Exceeded", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //-------------------spinners--------------------------------------------------------------------------------------
        spnr_country = view.findViewById(R.id.spnrCountry);
        spnr_country.setVisibility(View.VISIBLE);
        tvCountry = view.findViewById(R.id.tvCountry);
        tvCountry.setVisibility(View.GONE);
        spnrPaddyAge = view.findViewById(R.id.spnrPaddyAge);
        spnrPaddyAge.setVisibility(View.VISIBLE);
        tvPaddysAge = view.findViewById(R.id.tvPaddysAge);
        tvPaddysAge.setVisibility(View.GONE);
        //---------------address details that will be visible for indian people only----------------------------------------
        llAddressDetails = view.findViewById(R.id.llAddressDetails);
        etPincode = view.findViewById(R.id.etPincode);
        etPincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etPincode.getText().toString().isEmpty()) {

                    llAddressDetails.setVisibility(View.VISIBLE);

                } else {
                    llAddressDetails.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPincode.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(android.view.View v, boolean hasFocus) {
                if (!hasFocus) {
                    getAddress(etPincode.getText().toString().trim());
                }
            }
        });


        //----------------------------------------------------------------------------------------------------------------------
        //----------------------------------color of paddy---------------------------------------------------------------------
        //show 3 images in a row
        phvPaddyColor = view.findViewById(R.id.phvPaddyColor);
        //---------------------------------------------------------------------------------------------------------------------
        onRadioButtonClicked(view);
        //----------------------------------------call new fragment------------------------------------------------------------
        btnSubmit = view.findViewById(R.id.btnSubmit);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grainsVartyArr = new ArrayList<>();
                str_grains1 = etGrainVarities.getText().toString();

                phvPaddyColor.findViewHolderForAdapterPosition(session.getCHeckPosition());
                Log.e("------paddycolorPos----", String.valueOf(session.getCHeckPosition()));


                if (str_grains1 == null || str_grains1.isEmpty() || str_grains1.equals("null")) {
                    //do nothing
                } else {

                    grainsVartyArr.add(0, str_grains1);
                    Log.e("---grainsVartyArr---", String.valueOf(grainsVartyArr));
                }


                View child;
                for (int i = 0; i < phvGrains.getChildCount(); i++) {
                    child = phvGrains.getChildAt(i);
                    //In case you need to access ViewHolder:
                    phvGrains.getChildViewHolder(child);

                    EditText edt = phvGrains.getChildViewHolder(child).itemView.findViewById(R.id.etGrainVarities);
                    grainsVartyArr.add(edt.getText().toString());

                    Log.d("veveve", String.valueOf(grainsVartyArr.get(i)));
                }


                datas = new JsonArray();


                for (int i = 0; i < grainsVartyArr.size(); i++) {

                    if (!grainsVartyArr.get(i).equals("")) {
                        JsonObject object = new JsonObject();
                        object.addProperty("verity", grainsVartyArr.get(i));
                        datas.add(object);
                        db_arr_grains.add(grainsVartyArr.get(i));
                    }

                    Log.e("----items----", grainsVartyArr.get(i));

                }

                tinydb.putListString("db_arr_grains", db_arr_grains);


                Log.d("datas123", String.valueOf(datas));

                str_fname = etFName.getText().toString().trim();
                str_contct_person = etContctPersn.getText().toString().trim();
                str_phn_no = etPhnNo.getText().toString().trim();
                str_state = etState.getText().toString().trim();
                str_district = etDistrict.getText().toString().trim();
                str_taluk = etTaluk.getText().toString().trim();
                str_town = etTown.getText().toString().trim();
                str_gst_no = etGstNo.getText().toString().trim();
                str_avg_rainfall = etAvgRainFall.getText().toString().trim();
                str_max_wind_speed = etMaxWindSpeed.getText().toString().trim();
                str_grain_varts = etGrainVarities.getText().toString();
                str_soil_brearing_cap = etSoilBearingCapacity.getText().toString().trim();
                str_avg_density = etAvgDensity.getText().toString().trim();
                str_country = String.valueOf(strCountryId).trim();
                str_age_paddy = String.valueOf(strPaddyId).trim();
                str_pincode = etPincode.getText().toString().trim();
                str_procs_id = strProcessId;


                if (validateFormData(str_procs_id, str_fname, str_contct_person, str_phn_no, strCountryId,
                        str_pincode, str_gst_no)) {

                    if (str_state == null || str_state.isEmpty() || str_state.equals("null")) {
                        str_state = "NA";
                    }
                    if (str_district == null || str_district.isEmpty() || str_district.equals("null")) {
                        str_district = "NA";
                    }
                    if (str_taluk == null || str_taluk.isEmpty() || str_taluk.equals("null")) {
                        str_taluk = "NA";
                    }
                    if (str_town == null || str_town.isEmpty() || str_town.equals("null")) {
                        str_town = "NA";
                    }
                    if (str_gst_no == null || str_gst_no.isEmpty() || str_gst_no.equals("null")) {
                        str_gst_no = "NA";
                    }
                    if (str_soil_brearing_cap == null || str_soil_brearing_cap.isEmpty() || str_soil_brearing_cap.equals("null")) {
                        str_soil_brearing_cap = "NA";
                    }
                    if (str_max_wind_speed == null || str_max_wind_speed.isEmpty() || str_max_wind_speed.equals("null")) {
                        str_max_wind_speed = "NA";
                    }
                    if (str_avg_rainfall == null || str_avg_rainfall.isEmpty() || str_avg_rainfall.equals("null")) {
                        str_avg_rainfall = "NA";
                    }
                    if (str_age_paddy == null || str_age_paddy.isEmpty() || str_age_paddy.equals("null") || str_age_paddy.equals("0")) {
                        str_age_paddy = "NA";
                    }
                    if (str_avg_density == null || str_avg_density.isEmpty() || str_avg_density.equals("null")) {
                        str_avg_density = "NA";
                    }
                  /*  if (datas == null || datas.equals("null") || datas.size() == 0) {
                       // Object dataObj = "null";
                        datas = null;
                    }*/
                    str_procs_img_id = img_id.getImagId().toString();
                    if (str_procs_img_id == null || str_procs_img_id.isEmpty() || str_procs_img_id.equals("0")) {
                        str_procs_img_id = "NA";
                    }

                    if(datas.size() == 0)
                    {
                        callEnqFormAPI(session.getUsrId(), str_procs_id, str_procs_img_id, str_fname, str_contct_person, str_phn_no,
                                str_country, str_pincode, str_state, str_district, str_taluk, str_town, str_gst_no,
                                str_soil_brearing_cap, str_max_wind_speed, str_avg_rainfall, str_age_paddy,
                                str_avg_density, "");
                    }
                    else
                    {
                        callEnqFormAPI(session.getUsrId(), str_procs_id, str_procs_img_id, str_fname, str_contct_person, str_phn_no,
                                str_country, str_pincode, str_state, str_district, str_taluk, str_town, str_gst_no,
                                str_soil_brearing_cap, str_max_wind_speed, str_avg_rainfall, str_age_paddy,
                                str_avg_density, datas);
                    }

                } else {
                    Toast.makeText(getActivity(), "Invalid Data", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;

    }

    public void callEnqFormAPI(String usrId, String procsId, String procsImgId, String fName, String contctPerson, String phn,
                               String country, String pincode, final String state, String district, String taluk, String village,
                               String gstNo, String soilCap, String windSpeed, String rainFall, String agePaddy, String avgDensity,
                               Object grainDetails) {

        try {
            progressdialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        final PostEnquiryDataModel pedm = new PostEnquiryDataModel(usrId, procsId, procsImgId, fName, contctPerson, phn, country, pincode,
                state, district, taluk, village, gstNo, soilCap, windSpeed, rainFall, agePaddy, avgDensity, grainDetails);
        Call<PostEnquiryDataModel> pedmCall = apiInterface.submitEnquiryData(pedm);
        pedmCall.enqueue(new Callback<PostEnquiryDataModel>() {
            @Override
            public void onResponse(Call<PostEnquiryDataModel> call, Response<PostEnquiryDataModel> response) {
                PostEnquiryDataModel pedmResources = response.body();
                if (pedmResources.status.equals("success")) {

                    Toast.makeText(getActivity(), pedmResources.message, Toast.LENGTH_SHORT).show();
                    llMsg.setVisibility(View.VISIBLE);
                    tvMsg.setText(pedmResources.message);
                    List<PostEnquiryDataModel.EnquiryDatum> enquiryDatum = pedmResources.response;
                    for (PostEnquiryDataModel.EnquiryDatum enqDatumList : enquiryDatum) {

                        session.saveEnquiryResponse(enqDatumList.enquiry_id, enqDatumList.user_id, enqDatumList.process_id,
                                enqDatumList.process_image_id, enqDatumList.first_name, enqDatumList.contact_person,
                                enqDatumList.mobileno, enqDatumList.country_id, enqDatumList.pincode, enqDatumList.state_id,
                                enqDatumList.district_id, enqDatumList.taluk_id, enqDatumList.village_id, enqDatumList.gst_no,
                                enqDatumList.soil_bearing, enqDatumList.wind_speed, enqDatumList.avg_rainfall,
                                enqDatumList.paddy_age, enqDatumList.paddy_density);
                        getEnquiryFormStatus(enqDatumList.user_id);

                          List<PostEnquiryDataModel.EnquiryDatum.VarietyDatum> varietyDatum = enqDatumList.varity_id;
                            ArrayList<String> arrVariety = new ArrayList<>();
                            if(enqDatumList.varity_id.equals("null"))
                            {
                                arrVariety.add("null");
                            }
                            else {
                                for (PostEnquiryDataModel.EnquiryDatum.VarietyDatum vartyDatum : varietyDatum) {
                                    arrVariety.add(vartyDatum.varity_id);
                                    Log.e("----variety id----", vartyDatum.varity_id);
                                }
                            }
                            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                            SharedPreferences.Editor editor = sharedPrefs.edit();
                            Gson gson = new Gson();

                            String json = gson.toJson(arrVariety);
                            editor.putString("varity_id", json);
                            editor.commit();
                    }


                } else {
                    llMsg.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), pedmResources.message, Toast.LENGTH_SHORT).show();
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<PostEnquiryDataModel> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        //--------------------------------------------------------Select Process-----------------------------------------------
        rdoGrpProcess = view.findViewById(R.id.rdoGrpProcess);
        rdoBtnSteamCuring = view.findViewById(R.id.rdoBtnSteamCuring);
        rdoBtnParboiling = view.findViewById(R.id.rdoBtnParboiling);
        llPaddyImages = view.findViewById(R.id.llPaddyImages);
        //----------------------------------------------------on process selection show color of paddy images----------------------------------------------------------------
        rdoGrpProcess.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Check which radio button was clicked
                switch (checkedId) {
                    case R.id.rdoBtnParboiling:
                        strProcessId = "1";
                        getImages(strProcessId);
                        makeImageVisible();
                        break;
                    case R.id.rdoBtnSteamCuring:
                        strProcessId = "2";
                        getImages(strProcessId);
                        makeImageVisible();
                        break;

                }
            }
        });
        getCountryList();
        getPaddysAge();
    }

    public void makeImageVisible() {
        llPaddyImages.setVisibility(View.VISIBLE);
    }

    public void getEnquiryFormStatus(String usr_id) {

        EnqFormStatusModel enqFStausModel = new EnqFormStatusModel(usr_id);
        final Call<EnqFormStatusModel> enqFormStatusModelCall = apiInterface.getEnqFormStatus(enqFStausModel);
        enqFormStatusModelCall.enqueue(new Callback<EnqFormStatusModel>() {
            @Override
            public void onResponse(Call<EnqFormStatusModel> call, Response<EnqFormStatusModel> response) {
                EnqFormStatusModel enqFormStatusResources = response.body();
                List<EnqFormStatusModel.EnqFormRespDatum> formStatus = enqFormStatusResources.response;
                for (EnqFormStatusModel.EnqFormRespDatum formStatusList : formStatus) {
                    if (formStatusList.status.equals("1")) {
                        makeFormDisable();
                        llMsg.setVisibility(View.VISIBLE);
                    } else if (formStatusList.status.equals("2")) {
                        releaseForm();
                        llMsg.setVisibility(View.GONE);
                        // session.clearEnquiryForm();
                    } else {
                        //do nothing
                        //session.clearEnquiryForm();
                        llMsg.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<EnqFormStatusModel> call, Throwable t) {

            }
        });
    }

    public void makeFormDisable() {
        str1_process_id = session.getEnqPrcsId();
        str1_first_name = session.getEnqFName();
        str1_contact_person = session.getEnqContact();
        str1_mobileno = session.getEnqMobile();
        str1_country_id = session.getEnqCountry();
        str1_pincode = session.getEnqPincode();
        str1_state_id = session.getEnqState();

        str1_district_id = session.getEnqDistrict();
        str1_taluk_id = session.getEnqTaluk();
        str1_village_id = session.getEnqVillageId();
        str1_gst_no = session.getGstNo();
        str1_soil_bearing = session.getEnqSoil();
        str1_wind_speed = session.getEnqWindSpeed();

        str1_avg_rainfall = session.getEnqAvgRainFall();
        str1_paddy_age = session.getEnqPaddysAge();
        str1_paddy_density = session.getPaddyDensity();
        str1_img_id = session.getEnqImgId();

      /*  SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = sharedPrefs.getString("varity_id", "");

        Type type = (Type) new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> inpList = new Gson().fromJson(json, (java.lang.reflect.Type) type);
        Log.e("----inpList----",String.valueOf(inpList.size()));*/
       /* if(inpList.isEmpty()) {
            etGrainVarities.setText("");
        }else {
            etGrainVarities.setText(inpList.get(0));
            for (int i = 1; i < inpList.size(); i++) {
                phvGrains.addView(new RemoveGrnVarty_Models(getActivity(), phvGrains, inpList.get(i)));
            }
        }*/
        if (str1_process_id.equals("1")) {
            rdoBtnParboiling.setChecked(true);
            rdoBtnSteamCuring.setChecked(false);
        } else {
            rdoBtnSteamCuring.setChecked(true);
            rdoBtnParboiling.setChecked(false);
        }
        etFName.setText(str1_first_name);
        etContctPersn.setText(str1_contact_person);
        etPhnNo.setText(str1_mobileno);
        spnr_country.setVisibility(View.GONE);
        tvCountry.setVisibility(View.VISIBLE);
        tvCountry.setText(str1_country_id);
        etPincode.setText(str1_pincode);
        etState.setText(str1_state_id);
        etDistrict.setText(str1_district_id);
        etTaluk.setText(str1_taluk_id);
        etTown.setText(str1_village_id);
        etGstNo.setText(str1_gst_no);
        etSoilBearingCapacity.setText(str1_soil_bearing);
        etMaxWindSpeed.setText(str1_wind_speed);
        etAvgRainFall.setText(str1_avg_rainfall);
        spnrPaddyAge.setVisibility(View.GONE);
        tvPaddysAge.setVisibility(View.VISIBLE);
        tvPaddysAge.setText(str1_paddy_age);
        etAvgDensity.setText(str1_paddy_density);
        rdoBtnParboiling.setEnabled(false);
        rdoBtnSteamCuring.setEnabled(false);

        btnSubmit.setText("Submitted");
        btnSubmit.setEnabled(false);
        btnSubmit.setBackgroundResource(R.drawable.disable_btn);
        GradientDrawable drawable = (GradientDrawable) btnSubmit.getBackground();
        drawable.setColor(Color.parseColor("#88F9CE18"));

        etFName.setEnabled(false);
        etContctPersn.setEnabled(false);
        etPhnNo.setEnabled(false);
        spnr_country.setEnabled(false);
        etPincode.setEnabled(false);
        etState.setEnabled(false);
        etDistrict.setEnabled(false);
        etTaluk.setEnabled(false);
        etTown.setEnabled(false);
        etGstNo.setEnabled(false);
        etSoilBearingCapacity.setEnabled(false);
        etMaxWindSpeed.setEnabled(false);
        etAvgRainFall.setEnabled(false);
        spnrPaddyAge.setEnabled(false);
        etAvgDensity.setEnabled(false);

    }

    public void releaseForm() {
        etFName.setText("");
        etContctPersn.setText("");
        etPhnNo.setText("");
        spnr_country.setEnabled(true);
        spnr_country.setVisibility(View.VISIBLE);
        tvCountry.setVisibility(View.GONE);
        etPincode.setText("");
        etState.setText("");
        etDistrict.setText("");
        etTaluk.setText("");
        etTown.setText("");
        etGstNo.setText("");
        etSoilBearingCapacity.setText("");
        etMaxWindSpeed.setText("");
        etAvgRainFall.setText("");
        spnrPaddyAge.setEnabled(true);
        spnrPaddyAge.setVisibility(View.VISIBLE);
        tvPaddysAge.setVisibility(View.GONE);
        etAvgDensity.setText("");
        rdoBtnParboiling.setEnabled(true);
        rdoBtnSteamCuring.setEnabled(true);

        etFName.setEnabled(true);
        etContctPersn.setEnabled(true);
        etPhnNo.setEnabled(true);
        etPincode.setEnabled(true);
        etState.setEnabled(false);
        etDistrict.setEnabled(false);
        etTaluk.setEnabled(false);
        etTown.setEnabled(true);
        etGstNo.setEnabled(true);
        etSoilBearingCapacity.setEnabled(true);
        etMaxWindSpeed.setEnabled(true);
        etAvgRainFall.setEnabled(true);
        etAvgDensity.setEnabled(true);
        llAddGrainVarities.setClickable(true);

        btnSubmit.setText("Submit");
        btnSubmit.setEnabled(true);
        btnSubmit.setBackgroundResource(R.drawable.disable_btn);
        GradientDrawable drawable = (GradientDrawable) btnSubmit.getBackground();
        drawable.setColor(Color.parseColor("#F9CE18"));


        Fragment mFragment = new ModuleFragment();//background color should set on parent layout of new fragment
        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.clGetaQuoteContainer, mFragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    public void getImages(String processId) {
        if (Utils.CheckInternetConnection(getActivity())) {
            ImageListModel imageListModel = new ImageListModel(processId);
            Call<ImageListModel> imageListModelCall = apiInterface.getImages(imageListModel);
            imageListModelCall.enqueue(new Callback<ImageListModel>() {
                @Override
                public void onResponse(Call<ImageListModel> call, Response<ImageListModel> response) {
                    ImageListModel imageResources = response.body();
                    if (imageResources.status.equals("success")) {

                        llPaddyImages.setVisibility(View.VISIBLE);
                        List<ImageListModel.ImageListDatum> imageListDatum = imageResources.response;
                        imgList = new ArrayList<Images>();

                        img_id = new ImageId(0);
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
                        phvPaddyColor.setLayoutManager(mLayoutManager);
                        //phvPaddyColor.addItemDecoration(new DividerItemDecoration(getActivity(),0));//remove divider
                        adapter = new PaddyImagesAdapter(getActivity(), imgList, img_id);
                        phvPaddyColor.setAdapter(adapter);

                        for (ImageListModel.ImageListDatum imageCount : imageListDatum) {

                            imgList.add(new Images(imageCount.image_id, imageCount.name, imageCount.image));
                        }
                        adapter.setEmployees(imgList);

                    } else {
                        llPaddyImages.setVisibility(View.GONE);
                        img_id.setImageId(0);

                    }

                }

                @Override
                public void onFailure(Call<ImageListModel> call, Throwable t) {

                }
            });
        } else {
            Toast.makeText(getActivity(), "Please Check Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }

    public void getCountryList() {
        if (Utils.CheckInternetConnection(getActivity())) {
            Call<CountryListModel> countryListModelCall = apiInterface.getCountries();
            countryListModelCall.enqueue(new Callback<CountryListModel>() {
                @Override
                public void onResponse(Call<CountryListModel> call, Response<CountryListModel> response) {
                    CountryListModel countryListResources = response.body();
                    if (countryListResources.status.equals("success")) {
                        List<CountryListModel.CountryListDatum> datumList = countryListResources.response;
                        final ArrayList<String> conNameArr = new ArrayList<>();
                        final ArrayList<Integer> conIdArr = new ArrayList<>();
                        conNameArr.add("Select Country");
                        conIdArr.add(0);
                        for (CountryListModel.CountryListDatum countryData : datumList) {
                            conNameArr.add(countryData.country_name);
                            conIdArr.add(countryData.country_id);
                        }
                        spnr_country.setAdapter(new SpinnerAdapter(getActivity(), conNameArr, conIdArr, hidingItemIndex));
                        spnr_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {

                                strCountryName = conNameArr.get(position); //get selected cust name
                                strCountryId = conIdArr.get(position); //get selected cust type id

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                strCountryId = 0;
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "No data Available", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<CountryListModel> call, Throwable t) {
                    call.cancel();
                }
            });
        } else {
            Toast.makeText(getActivity(), "Please Check Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }

    public void getPaddysAge() {
        if (Utils.CheckInternetConnection(getActivity())) {

            Call<PaddysAgeListModel> paddysAgeListModelCall = apiInterface.getPaddyAgeList();
            paddysAgeListModelCall.enqueue(new Callback<PaddysAgeListModel>() {
                @Override
                public void onResponse(Call<PaddysAgeListModel> call, Response<PaddysAgeListModel> response) {
                    PaddysAgeListModel paddyAgeResources = response.body();
                    if (paddyAgeResources.status.equals("success")) {

                        List<PaddysAgeListModel.PaddysAgeDatum> datumList = paddyAgeResources.response;
                        final ArrayList<String> paddyNameArr = new ArrayList<>();
                        final ArrayList<Integer> paddyIdArr = new ArrayList<>();
                        paddyNameArr.add("Select Age of Paddy");
                        paddyIdArr.add(0);
                        for (PaddysAgeListModel.PaddysAgeDatum countryData : datumList) {
                            paddyNameArr.add(countryData.paddy_age);
                            paddyIdArr.add(countryData.paddyage_id);
                        }
                        spnrPaddyAge.setAdapter(new SpinnerAdapter(getActivity(), paddyNameArr, paddyIdArr, hidingItemIndex));
                        spnrPaddyAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {

                                strPaddyAge = paddyNameArr.get(position); //get selected cust name
                                strPaddyId = paddyIdArr.get(position); //get selected cust type id

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                strPaddyId = 0;
                            }
                        });

                    } else {
                        Toast.makeText(getActivity(), "No data Available", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PaddysAgeListModel> call, Throwable t) {

                }
            });

        } else {
            Toast.makeText(getActivity(), "Please Check Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }

    public void getAddress(String ip_pincode) {
        final Call<PincodeModel> pincodeModelCall = postalInterface.getAddress(ip_pincode);
        pincodeModelCall.enqueue(new Callback<PincodeModel>() {
            @Override
            public void onResponse(Call<PincodeModel> call, Response<PincodeModel> response) {
                PincodeModel pincodeResources = response.body();
                if (pincodeResources.Status.equals("Success")) {

                    List<PincodeModel.PincodeDatum> pincodeDatum = pincodeResources.PostOffice;

                    etState.setText(pincodeDatum.get(0).State);
                    etDistrict.setText(pincodeDatum.get(0).District);
                    etTaluk.setText(pincodeDatum.get(0).Taluk);

                    //Toast.makeText(getActivity(), "Pincode response getting", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getActivity(), pincodeResources.Message, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<PincodeModel> call, Throwable t) {
                call.cancel();

            }
        });
    }

    private boolean validateFormData(String process_id, String firstname, String contct_person, String mobile,
                                     int country, String pincode, String gst) {

        // String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (process_id == null || process_id.trim().length() == 0) {
            llProcess.requestFocus();
            rdoBtnParboiling.setError("Please select process");
            Toast.makeText(getActivity(), "Please select process", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (process_id == "") {
            llProcess.requestFocus();
            rdoBtnParboiling.setError("Please select process");
            Toast.makeText(getActivity(), "Please select process", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (firstname == null || firstname.trim().length() == 0) {
            etFName.requestFocus();
            etFName.setError("First Name is Required");
            return false;

        }
        if (contct_person == null || contct_person.trim().length() == 0) {
            etContctPersn.requestFocus();
            etContctPersn.setError("Last Name is Required");
            return false;

        }
        if (mobile == null || mobile.trim().length() == 0) {
            etPhnNo.requestFocus();
            etPhnNo.setError("Please enter valid Phone Number");
            return false;

        }
        if (mobile.matches("[0-9]")) {
            etPhnNo.requestFocus();
            etPhnNo.setError("Please Enter Phone Number");
            return false;
        }

        if (country == 0) {
            TextView errorText = (TextView) spnr_country.getSelectedView();
            errorText.requestFocus();
            errorText.setError("Please Select Country");
            Toast.makeText(getActivity(), "Please select country", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (pincode == null || pincode.trim().length() == 0) {
            etPincode.requestFocus();
            etPincode.setError("Pincode is Required");
            return false;
        }

        if (gst == null || gst.trim().length() == 0) {
            etGstNo.requestFocus();
            etGstNo.setError("Please Enter GST Number");
            return false;
        }

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        setRetainInstance(true);
        getEnquiryFormStatus(session.getUsrId());
    }
}
