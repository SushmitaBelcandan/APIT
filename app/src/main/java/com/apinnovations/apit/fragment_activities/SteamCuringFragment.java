package com.apinnovations.apit.fragment_activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.apinnovations.apit.activities.AfterProcess_Act;
import com.apinnovations.apit.adapters.ImageId;
import com.apinnovations.apit.adapters.Images;
import com.apinnovations.apit.adapters.PaddyImagesAdapter2;
import com.apinnovations.apit.adapters.SpinnerAdapter;
import com.apinnovations.apit.adapters.Utils;
import com.apinnovations.apit.reference.SessionManager;
import com.apinnovations.apit.retrofit_models.APIClient;
import com.apinnovations.apit.retrofit_models.APIInterface;
import com.apinnovations.apit.retrofit_models.EProcessImagesModel;
import com.apinnovations.apit.retrofit_models.EProcessSubmitFormModel;
import com.apinnovations.apit.retrofit_models.PaddysAgeListModel;
import com.apinnovations.apit.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class SteamCuringFragment extends Fragment {

    SessionManager session;
    APIInterface apiInterface;
    Toolbar toolbarParboiling;
    private TextView tvProcessModelTitle1, tvProcessModelTitle2, tvHeading1, tvHeading2;
    private ImageView ivHydrTank, ivDryerMethodName;
    private TextView tvNumStmngModel1;
    private Button btnSubmit;
    private LinearLayout llTitleContainer;
    private LinearLayout llModel1BatchWise, llDmCapacity, llDryerCapacity, llModel2BatchWise, llModel3BatchWise, llModel4BatchWise, llModel5BatchWise;
    private EditText etNumStmngModel1, etStmngCapctyModel1, etDMCapacity, etDMNumDryers, etPaddyVariety, etDMPincode, etDMProssLoc, etPaddyMoisture;
    private EditText etDyerCapacity, etNumStmngModel2, etSteamngCapctyModel2, etAgeingTanksModel2, etAgeingCapctyModel2;
    private Spinner spnrDMPaddyAge;
    RecyclerView phvPaddyColor;
    private LinearLayout llPaddyImages;

    private String str_usr_id;
    private String str_num_stmng_1, str_stng_capcty_1;
    private String str_mixed_capcty, str_batch_capcty, str_num_dryers, str_paddy_variety, str_pincode, str_paddy_moisture, str_prosc_loc;
    private String str_num_stmng_2, str_capcty_stmng_2, str_num_ageing_2, str_ageing_capcty_2;
    private String id, title1, title2, title3, title4;
    private String process_id, model_id, dryer_id;

    private String strPaddyAge;
    private int strPaddyId;
    int hidingItemIndex = 0;
    private int imgId, imageId;
    private PaddyImagesAdapter2 adapter;
    ArrayList<Images> imgList;
    ImageId img_id;
    ProgressDialog progressdialog;


    public SteamCuringFragment() {
        //required constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.steam_curing_models_1, container, false);
        toolbarParboiling = view.findViewById(R.id.toolbarParboiling);
        toolbarParboiling.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        //-----------------------------intent's value-----------------------------------------
        id = getArguments().getString("id");
        title1 = getArguments().getString("title1");
        title2 = getArguments().getString("title2");
        title3 = getArguments().getString("title3");
        title4 = getArguments().getString("title4");

        progressdialog = new ProgressDialog(getActivity());
        progressdialog.setMessage("Please Wait....");

        //------------------------------------------------------------------------------------
        session = new SessionManager(getActivity());
        apiInterface = APIClient.getClient().create(APIInterface.class);

        llTitleContainer = view.findViewById(R.id.llTitleContainer);
        tvHeading1 = view.findViewById(R.id.tvHeading1);
        tvHeading2 = view.findViewById(R.id.tvHeading2);
        tvProcessModelTitle1 = view.findViewById(R.id.tvProcessModelTitle1);
        ivHydrTank = view.findViewById(R.id.ivHydrTank);
        ivHydrTank.setClipToOutline(true);//fit with parent container

        //-----------------------------------------------------------parboiling/model-1/batchwise------------------------------
        llModel1BatchWise = view.findViewById(R.id.llModel1BatchWise);
        tvNumStmngModel1 = view.findViewById(R.id.tvNumStmngModel1);
        etNumStmngModel1 = view.findViewById(R.id.etNumStmngModel1);
        etStmngCapctyModel1 = view.findViewById(R.id.etStmngCapctyModel1);

        etDMCapacity = view.findViewById(R.id.etDMCapacity);
        etDMNumDryers = view.findViewById(R.id.etDMNumDryers);
        etPaddyVariety = view.findViewById(R.id.etPaddyVariety);
        spnrDMPaddyAge = view.findViewById(R.id.spnrDMPaddyAge);
        llDmCapacity = view.findViewById(R.id.llDmCapacity);
        etDMPincode = view.findViewById(R.id.etDMPincode);
        etDMProssLoc = view.findViewById(R.id.etDMProssLoc);
        etPaddyMoisture = view.findViewById(R.id.etPaddyMoisture);
        phvPaddyColor = view.findViewById(R.id.phvPaddyColor);
        llPaddyImages = view.findViewById(R.id.llPaddyImages);

        //-----------------------------------------------------------Parboiling/model1/mixed-wise---------------------
        tvProcessModelTitle2 = view.findViewById(R.id.tvProcessModelTitle2);
        ivDryerMethodName = view.findViewById(R.id.ivDryerMethodName);
        ivDryerMethodName.setClipToOutline(true);//fit with parent container
        llDryerCapacity = view.findViewById(R.id.llDryerCapacity);
        etDyerCapacity = view.findViewById(R.id.etDyerCapacity);
        //---------------------------------------------------------parboiling/model2/batchwise------------------------
        llModel2BatchWise = view.findViewById(R.id.llModel2BatchWise);
        etNumStmngModel2 = view.findViewById(R.id.etNumStmngModel2);
        etSteamngCapctyModel2 = view.findViewById(R.id.etSteamngCapctyModel2);
        etAgeingTanksModel2 = view.findViewById(R.id.etAgeingTanksModel2);
        etAgeingCapctyModel2 = view.findViewById(R.id.etAgeingCapctyModel2);


        getPaddysAge();
        getForms();

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.e("------imgid00000------",String.valueOf(img_id.getImagId()));


                str_usr_id = session.getUsrId();
                str_mixed_capcty = etDyerCapacity.getText().toString().trim();
                str_batch_capcty = etDMCapacity.getText().toString().trim();

                str_num_dryers = etDMNumDryers.getText().toString().trim();
                str_paddy_variety = etPaddyVariety.getText().toString().trim();
                str_pincode = etDMPincode.getText().toString().trim();
                str_prosc_loc = etDMProssLoc.getText().toString().trim();
                str_paddy_moisture = etPaddyMoisture.getText().toString().trim();

                str_num_stmng_1 = etNumStmngModel1.getText().toString().trim();
                str_stng_capcty_1 = etStmngCapctyModel1.getText().toString().trim();

                str_num_stmng_2 = etNumStmngModel2.getText().toString().trim();
                str_capcty_stmng_2 = etSteamngCapctyModel2.getText().toString().trim();
                str_num_ageing_2 = etAgeingTanksModel2.getText().toString().trim();
                str_ageing_capcty_2 = etAgeingCapctyModel2.getText().toString().trim();






                if (id.equals("p2m1d1")) {
                    sendData(str_usr_id, "2", "6", "1", "", "",
                            str_batch_capcty, str_num_dryers, str_paddy_variety, strPaddyAge, str_pincode, str_prosc_loc,
                            str_paddy_moisture, "", "", "",
                            "", "", "", "",
                            "", "", str_num_stmng_1, str_stng_capcty_1, "",
                            "");
                } else if (id.equals("p2m1d2")) {

                    sendData(str_usr_id, "2", "6", "2", "", "",
                            "", str_num_dryers, str_paddy_variety, strPaddyAge, str_pincode, str_prosc_loc,
                            str_paddy_moisture, "", "", "",
                            "", str_mixed_capcty, "", "",
                            "", "", str_num_stmng_1, str_stng_capcty_1,"" ,
                            "");
                } else if (id.equals("p2m2d1")) {
                    sendData(str_usr_id, "2", "7", "1",
                            "", "",
                            str_batch_capcty, str_num_dryers, str_paddy_variety, strPaddyAge, str_pincode, str_prosc_loc,
                            str_paddy_moisture, "", "", "",
                            "", "", "", "",
                            "", "", str_num_stmng_2, str_capcty_stmng_2, str_num_ageing_2,
                            str_ageing_capcty_2);

                } else{
                    sendData(str_usr_id, "2", "7", "2",
                            "", "",
                            "", str_num_dryers, str_paddy_variety, strPaddyAge, str_pincode, str_prosc_loc,
                            str_paddy_moisture, "", "", "",
                            "", str_mixed_capcty, "", "",
                            "", "", str_num_stmng_2, str_capcty_stmng_2, str_num_ageing_2,
                            str_ageing_capcty_2);

                }
                Intent intentAfterProcess = new Intent(getActivity(), AfterProcess_Act.class);
                startActivity(intentAfterProcess);
            }
        });
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver3, new IntentFilter("custom-message"));
        return view;
    }

    //getting image id from adapter
    public BroadcastReceiver mMessageReceiver3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            imgId = intent.getIntExtra("process_image_id", 0);
        }
    };

    public void displayImages(String processId, final String modelId, String DryerMethodId) {
        if (Utils.CheckInternetConnection(getActivity())) {

            EProcessImagesModel eProcessImgsModel = new EProcessImagesModel(processId, modelId, DryerMethodId);
            Call<EProcessImagesModel> epImgsModelCall = apiInterface.getImages(eProcessImgsModel);
            epImgsModelCall.enqueue(new Callback<EProcessImagesModel>() {
                @Override
                public void onResponse(Call<EProcessImagesModel> call, Response<EProcessImagesModel> response) {
                    EProcessImagesModel eProcsImgesResources = response.body();
                    if (eProcsImgesResources.status.equals("success")) {

                        process_id = eProcsImgesResources.process_id;
                        model_id = eProcsImgesResources.model_id;
                        dryer_id = eProcsImgesResources.dryer_id;

                        List<EProcessImagesModel.ImagesEProcessDatum1> datumList = eProcsImgesResources.data1;

                        Glide.with(getActivity()).load(datumList.get(0).image).into(ivHydrTank);
                        Glide.with(getActivity()).load(datumList.get(1).image).into(ivDryerMethodName);

                        llPaddyImages.setVisibility(View.VISIBLE);
                        List<EProcessImagesModel.ImagesEProcessDatum2> imageListDatum = eProcsImgesResources.data2;
                        imgList = new ArrayList<Images>();

                        img_id = new ImageId(0);

                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
                        phvPaddyColor.setLayoutManager(mLayoutManager);
                        //phvPaddyColor.addItemDecoration(new DividerItemDecoration(getActivity(),0));//remove divider
                        adapter = new PaddyImagesAdapter2(getActivity(), imgList, img_id);
                        phvPaddyColor.setAdapter(adapter);

                        for (EProcessImagesModel.ImagesEProcessDatum2 imageCount : imageListDatum) {

                            imageId = Integer.parseInt(imageCount.process_image_id);
                            imgList.add(new Images(imageId, imageCount.image_color, imageCount.process_image_name));
                        }
                        adapter.setEmployees(imgList);

                    } else {
                        llPaddyImages.setVisibility(GONE);
                        Glide.with(getActivity()).load(R.drawable.rice_mill).into(ivHydrTank);
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
        try {
            progressdialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


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
                    Toast.makeText(getActivity(), "Your Request has Received.....", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_SHORT).show();
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure(Call<EProcessSubmitFormModel> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void getForms() {
        if (id.equals("p2m1d1")) {
            displayImages("2", "6", "1");
            llTitleContainer.setOrientation(LinearLayout.VERTICAL);
            tvProcessModelTitle1.setPadding(5, 5, 0, 5);
            tvProcessModelTitle2.setPadding(0, 5, 5, 5);

            llModel1BatchWise.setVisibility(View.VISIBLE);
            llModel2BatchWise.setVisibility(GONE);

            llDmCapacity.setVisibility(View.VISIBLE);
            llDryerCapacity.setVisibility(GONE);

            tvProcessModelTitle1.setText(title1);
            tvProcessModelTitle2.setText(title2);
            tvHeading1.setText(title3);
            tvHeading2.setText(title4);
        } else if (id.equals("p2m1d2")) {
            displayImages("2", "6", "2");
            llTitleContainer.setOrientation(LinearLayout.VERTICAL);
            tvProcessModelTitle1.setPadding(5, 5, 0, 5);
            tvProcessModelTitle2.setPadding(0, 5, 5, 5);


            llModel1BatchWise.setVisibility(View.VISIBLE);
            llModel2BatchWise.setVisibility(GONE);

            llDmCapacity.setVisibility(View.GONE);
            llDryerCapacity.setVisibility(View.VISIBLE);

            tvProcessModelTitle1.setText(title1);
            tvProcessModelTitle2.setText(title2);

            tvHeading1.setText(title3);
            tvHeading2.setText(title4);
        } else if (id.equals("p2m2d1")) {
            displayImages("2", "7", "1");
            llTitleContainer.setOrientation(LinearLayout.VERTICAL);
            tvProcessModelTitle1.setPadding(5, 5, 0, 5);
            tvProcessModelTitle2.setPadding(0, 5, 5, 5);

            llModel1BatchWise.setVisibility(View.GONE);
            llModel2BatchWise.setVisibility(View.VISIBLE);

            llDmCapacity.setVisibility(View.VISIBLE);
            llDryerCapacity.setVisibility(GONE);

            tvProcessModelTitle1.setText(title1);
            tvProcessModelTitle2.setText(title2);
            tvHeading1.setText(title3);
            tvHeading2.setText(title4);
        } else {
            displayImages("2", "7", "2");
            llTitleContainer.setOrientation(LinearLayout.VERTICAL);
            tvProcessModelTitle1.setPadding(5, 5, 0, 5);
            tvProcessModelTitle2.setPadding(0, 5, 5, 5);


            llModel1BatchWise.setVisibility(View.GONE);
            llModel2BatchWise.setVisibility(View.VISIBLE);

            llDmCapacity.setVisibility(View.GONE);
            llDryerCapacity.setVisibility(View.VISIBLE);

            tvProcessModelTitle1.setText(title1);
            tvProcessModelTitle2.setText(title2);
            tvHeading1.setText(title3);
            tvHeading2.setText(title4);
        }
    }

}
