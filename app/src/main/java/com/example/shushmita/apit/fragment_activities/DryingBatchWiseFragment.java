package com.example.shushmita.apit.fragment_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.shushmita.apit.R;
import com.example.shushmita.apit.activities.AfterProcess_Act;
import com.example.shushmita.apit.adapters.Images;
import com.example.shushmita.apit.adapters.PaddyImagesAdapter2;
import com.example.shushmita.apit.adapters.SpinnerAdapter;
import com.example.shushmita.apit.adapters.Utils;
import com.example.shushmita.apit.reference.SessionManager;
import com.example.shushmita.apit.retrofit_models.APIClient;
import com.example.shushmita.apit.retrofit_models.APIInterface;
import com.example.shushmita.apit.retrofit_models.EProcessImagesModel;
import com.example.shushmita.apit.retrofit_models.EProcessSubmitFormModel;
import com.example.shushmita.apit.retrofit_models.PaddysAgeListModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class DryingBatchWiseFragment extends Fragment {

    APIInterface apiInterface;
    SessionManager session;
    Toolbar toolbarDryBatchWise;

    private LinearLayout llDmCapacity, llDryerCapacity;
    private ImageView ivDryerMethodName, ivDryerMethodName1;
    private TextView dryer_method_title;
    private EditText etDMCapacity, etDyerCapacity;
    private EditText etDMNumDryers, etPaddyVariety, etDMPincode, etDMProssLoc, etPaddyMoisture;
    private Button btnSubmit;
    private Spinner spnrDMPaddyAge;

    private String str_usr_id;
    private String str_mixed_capcty, str_batch_capcty, str_num_dryers, str_paddy_variety, str_pincode, str_paddy_moisture, str_prosc_loc;
    private String strPaddyAge;
    private int strPaddyId;
    int hidingItemIndex = 0;
    private String id, title1, title2, title3, title4;


    public DryingBatchWiseFragment() {
        //required constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.drying_batch_wise, container, false);
        toolbarDryBatchWise = view.findViewById(R.id.toolbarDryBatchWise);
        toolbarDryBatchWise.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        //-----------------------------intent's value-----------------------------------------------
        id = getArguments().getString("id");
        title1 = getArguments().getString("title1");
        //-------------------------------------------------------xml data---------------------------

        apiInterface = APIClient.getClient().create(APIInterface.class);
        session = new SessionManager(getActivity());

        llDmCapacity = view.findViewById(R.id.llDmCapacity);
        llDryerCapacity = view.findViewById(R.id.llDryerCapacity);

        ivDryerMethodName = view.findViewById(R.id.ivDryerMethodName);
        ivDryerMethodName1 = view.findViewById(R.id.ivDryerMethodName1);

        dryer_method_title = view.findViewById(R.id.dryer_method_title);

        etDMCapacity = view.findViewById(R.id.etDMCapacity);
        etDyerCapacity = view.findViewById(R.id.etDyerCapacity);
        etDMNumDryers = view.findViewById(R.id.etDMNumDryers);
        etPaddyVariety = view.findViewById(R.id.etPaddyVariety);
        etDMPincode = view.findViewById(R.id.etDMPincode);
        etDMProssLoc = view.findViewById(R.id.etDMProssLoc);
        etPaddyMoisture = view.findViewById(R.id.etPaddyMoisture);

        spnrDMPaddyAge = view.findViewById(R.id.spnrDMPaddyAge);

        getPaddysAge();
        getForms();
        btnSubmit  =view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_usr_id = session.getUsrId();
                str_mixed_capcty = etDyerCapacity.getText().toString().trim();
                str_batch_capcty = etDMCapacity.getText().toString().trim();

                str_num_dryers = etDMNumDryers.getText().toString().trim();
                str_paddy_variety = etPaddyVariety.getText().toString().trim();
                str_pincode = etDMPincode.getText().toString().trim();
                str_prosc_loc = etDMProssLoc.getText().toString().trim();
                str_paddy_moisture = etPaddyMoisture.getText().toString().trim();

                if (id.equals("p3d1")) {
                    sendData(str_usr_id, "3", "0", "1", "", "",
                            str_batch_capcty, str_num_dryers, str_paddy_variety, strPaddyAge, str_pincode, str_prosc_loc,
                            str_paddy_moisture, "", "", "",
                            "", "", "", "",
                            "", "", "", "", "",
                            "");
                }
                else {

                    sendData(str_usr_id, "3", "0", "2", "", "",
                            "", str_num_dryers, str_paddy_variety, strPaddyAge, str_pincode, str_prosc_loc,
                            str_paddy_moisture, "", "", "",
                            "", str_mixed_capcty, "", "",
                            "", "", "", "", "",
                            "");
                }

              /*  Intent intentAfterProcess = new Intent(getActivity(), AfterProcess_Act.class);
                startActivity(intentAfterProcess);*/
            }
        });

        return view;
    }

    public void displayImages(String processId, final String modelId, String DryerMethodId) {
        if (Utils.CheckInternetConnection(getActivity())) {

            EProcessImagesModel eProcessImgsModel = new EProcessImagesModel(processId, modelId, DryerMethodId);
            Call<EProcessImagesModel> epImgsModelCall = apiInterface.getImages(eProcessImgsModel);
            epImgsModelCall.enqueue(new Callback<EProcessImagesModel>() {
                @Override
                public void onResponse(Call<EProcessImagesModel> call, Response<EProcessImagesModel> response) {
                    EProcessImagesModel eProcsImgesResources = response.body();
                    if (eProcsImgesResources.status.equals("success")) {

                        List<EProcessImagesModel.ImagesEProcessDatum1> datumList = eProcsImgesResources.data1;

                        Glide.with(getActivity()).load(datumList.get(0).image).into(ivDryerMethodName);
                        Glide.with(getActivity()).load(datumList.get(1).image).into(ivDryerMethodName1);

                    } else {
                        Glide.with(getActivity()).load(R.drawable.rice_mill).into(ivDryerMethodName);

                    }
                }

                @Override
                public void onFailure(Call<EProcessImagesModel> call, Throwable t) {
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
                        spnrDMPaddyAge.setAdapter(new SpinnerAdapter(getActivity(), paddyNameArr, paddyIdArr, hidingItemIndex));
                        spnrDMPaddyAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    call.cancel();
                }
            });

        } else {
            Toast.makeText(getActivity(), "Please Check Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendData(String usrId, String procsId, String modelId, String dryerId,
                         String noHydn, String hydrnTnkCapcty, String batchDryerCapcty,
                         String noDryers, String vrtyPaddy, String agePaddy, String pincode,
                         String procesngLocations, String paddyMoisture, String noPreStmngTnk,
                         String preStmngCapcty, String noCookers, String cookerCapcty,
                         String mixedDryerCapcty, String noFinalStmngTnk, String finalStmngCapcty,
                         String noPostStmngTnk, String postStmngTnkCapcty, String noStmngTnk,
                         String stmngTnkCapcty, String agingTanks, String agingTnkCapcty) {

        if (procsId == null || procsId.isEmpty() || procsId.equals("null")) {
            procsId = "NA";
        }
        if (modelId == null || modelId.isEmpty() || modelId.equals("null")) {
            modelId = "NA";
        }
        if (dryerId == null || dryerId.isEmpty() || dryerId.equals("null")) {
            dryerId = "NA";
        }
        if (noHydn == null || noHydn.isEmpty() || noHydn.equals("null")) {
            noHydn = "NA";
        }
        if (hydrnTnkCapcty == null || hydrnTnkCapcty.isEmpty() || hydrnTnkCapcty.equals("null")) {
            hydrnTnkCapcty = "NA";
        }
        if (batchDryerCapcty == null || batchDryerCapcty.isEmpty() || batchDryerCapcty.equals("null")) {
            batchDryerCapcty = "NA";
        }
        if (noDryers == null || noDryers.isEmpty() || noDryers.equals("null")) {
            noDryers = "NA";
        }
        if (vrtyPaddy == null || vrtyPaddy.isEmpty() || vrtyPaddy.equals("null")) {
            vrtyPaddy = "NA";
        }
        if (agePaddy == null || agePaddy.isEmpty() || agePaddy.equals("null")) {
            agePaddy = "NA";
        }
        if (pincode == null || pincode.isEmpty() || pincode.equals("null")) {
            pincode = "NA";
        }
        if (procesngLocations == null || procesngLocations.isEmpty() || procesngLocations.equals("null")) {
            procesngLocations = "NA";
        }
        if (paddyMoisture == null || paddyMoisture.isEmpty() || paddyMoisture.equals("null")) {
            paddyMoisture = "NA";
        }
        if (noPreStmngTnk == null || noPreStmngTnk.isEmpty() || noPreStmngTnk.equals("null")) {
            noPreStmngTnk = "NA";
        }
        if (preStmngCapcty == null || preStmngCapcty.isEmpty() || preStmngCapcty.equals("null")) {
            preStmngCapcty = "NA";
        }
        if (noCookers == null || noCookers.isEmpty() || noCookers.equals("null")) {
            noCookers = "NA";
        }
        if (cookerCapcty == null || cookerCapcty.isEmpty() || cookerCapcty.equals("null")) {
            cookerCapcty = "NA";
        }
        if (mixedDryerCapcty == null || mixedDryerCapcty.isEmpty() || mixedDryerCapcty.equals("null")) {
            mixedDryerCapcty = "NA";
        }
        if (noFinalStmngTnk == null || noFinalStmngTnk.isEmpty() || noFinalStmngTnk.equals("null")) {
            noFinalStmngTnk = "NA";
        }
        if (finalStmngCapcty == null || finalStmngCapcty.isEmpty() || finalStmngCapcty.equals("null")) {
            finalStmngCapcty = "NA";
        }
        if (noPostStmngTnk == null || noPostStmngTnk.isEmpty() || noPostStmngTnk.equals("null")) {
            noPostStmngTnk = "NA";
        }
        if (postStmngTnkCapcty == null || postStmngTnkCapcty.isEmpty() || postStmngTnkCapcty.equals("null")) {
            postStmngTnkCapcty = "NA";
        }
        if (noStmngTnk == null || noStmngTnk.isEmpty() || noStmngTnk.equals("null")) {
            noStmngTnk = "NA";
        }
        if (stmngTnkCapcty == null || stmngTnkCapcty.isEmpty() || stmngTnkCapcty.equals("null")) {
            stmngTnkCapcty = "NA";
        }
        if (agingTanks == null || agingTanks.isEmpty() || agingTanks.equals("null")) {
            agingTanks = "NA";
        }
        if (agingTnkCapcty == null || agingTnkCapcty.isEmpty() || agingTnkCapcty.equals("null")) {
            agingTnkCapcty = "NA";
        }

        postForms(usrId, procsId, modelId, dryerId, noHydn, hydrnTnkCapcty, batchDryerCapcty,
                noDryers, vrtyPaddy, agePaddy, pincode, procesngLocations, paddyMoisture, noPreStmngTnk,
                preStmngCapcty, noCookers, cookerCapcty, mixedDryerCapcty, noFinalStmngTnk, finalStmngCapcty,
                noPostStmngTnk, postStmngTnkCapcty, noStmngTnk, stmngTnkCapcty, agingTanks, agingTnkCapcty);


    }

    public void postForms(String usrId, String procsId, String modelId, String dryerId,
                          String noHydn, String hydrnTnkCapcty, String batchDryerCapcty,
                          String noDryers, String vrtyPaddy, String agePaddy, String pincode,
                          String procesngLocations, String paddyMoisture, String noPreStmngTnk,
                          String preStmngCapcty, String noCookers, String cookerCapcty,
                          String mixedDryerCapcty, String noFinalStmngTnk, String finalStmngCapcty,
                          String noPostStmngTnk, String postStmngTnkCapcty, String noStmngTnk,
                          String stmngTnkCapcty, String agingTanks, String agingTnkCapcty) {
        EProcessSubmitFormModel epSubmitFormModel = new EProcessSubmitFormModel(usrId, procsId, modelId, dryerId,
                noHydn, hydrnTnkCapcty, batchDryerCapcty, noDryers, vrtyPaddy, agePaddy, pincode, procesngLocations,
                paddyMoisture, noPreStmngTnk, preStmngCapcty, noCookers, cookerCapcty, mixedDryerCapcty, noFinalStmngTnk,
                finalStmngCapcty, noPostStmngTnk, postStmngTnkCapcty, noStmngTnk, stmngTnkCapcty, agingTanks, agingTnkCapcty);

        Call<EProcessSubmitFormModel> epSubmitFormCall = apiInterface.postForm(epSubmitFormModel);
        epSubmitFormCall.enqueue(new Callback<EProcessSubmitFormModel>() {
            @Override
            public void onResponse(Call<EProcessSubmitFormModel> call, Response<EProcessSubmitFormModel> response) {
                EProcessSubmitFormModel formResources = response.body();
                if (formResources.status.equals("success")) {
                    Toast.makeText(getActivity(), "Thank you for Your Request.....", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EProcessSubmitFormModel> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void getForms() {
        if (id.equals("p3d1")) {

            displayImages("1", "1", "1");
            dryer_method_title.setText(title1);
            ivDryerMethodName.setVisibility(View.VISIBLE);
            ivDryerMethodName1.setVisibility(GONE);
            llDmCapacity.setVisibility(View.VISIBLE);
            llDryerCapacity.setVisibility(GONE);

        }
        else{

        }
            displayImages("1", "1", "2");
            dryer_method_title.setText(title1);
            ivDryerMethodName.setVisibility(GONE);
            ivDryerMethodName1.setVisibility(View.VISIBLE);
            llDmCapacity.setVisibility(View.GONE);
            llDryerCapacity.setVisibility(View.VISIBLE);
        }

    }
