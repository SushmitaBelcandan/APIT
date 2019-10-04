package com.example.shushmita.apit.fragment_activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.shushmita.apit.adapters.Images;
import com.example.shushmita.apit.adapters.PaddyImagesAdapter;
import com.example.shushmita.apit.adapters.SpinnerAdapter;

import android.widget.TextView;
import android.widget.Toast;

import com.example.shushmita.apit.R;
import com.example.shushmita.apit.adapters.Utils;
import com.example.shushmita.apit.models.PaddyColorsModels;
import com.example.shushmita.apit.models.RemoveGrnVarty_Models;
import com.example.shushmita.apit.reference.SessionManager;
import com.example.shushmita.apit.retrofit_models.APIClient;
import com.example.shushmita.apit.retrofit_models.APIInterface;
import com.example.shushmita.apit.retrofit_models.CountryListModel;
import com.example.shushmita.apit.retrofit_models.ImageListModel;
import com.example.shushmita.apit.retrofit_models.PaddysAgeListModel;
import com.example.shushmita.apit.retrofit_models.PostEnquiryDataModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mindorks.placeholderview.PlaceHolderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardFragmentGetaQuote extends Fragment{

    SessionManager session;
    APIInterface apiInterface;
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

    private EditText etFName, etContctPersn, etPhnNo, etState, etDistrict, etTaluk, etTown, etGstNo;
    private EditText etAvgRainFall, etMaxWindSpeed, etGrainVarities;
    private String str_fname, str_contct_person, str_phn_no;
    private String str_state, str_district, str_taluk, str_town, str_gst_no;
    private String str_avg_rainfall, str_max_wind_speed, str_grain_varts;
    private String str_soil_brearing_cap, str_avg_density;
    private String str_country, str_pincode, str_age_paddy, str_procs_id, str_procs_img_id;
    public static EditText strGrainsVariety;


    private int strCountryId, strPaddyId;
    private String strCountryName, strPaddyAge;
    int hidingItemIndex = 0;
    private String strProcessId;

    final JsonArray datas = new JsonArray();
    public static ArrayList<String> arrayListSelctedImg;
    private PaddyImagesAdapter adapter;
    ArrayList<Images> imagesArr;
    ArrayList<Images> imagesNameArr;
    ArrayList<Images> imagesUrlArr;
    public DashBoardFragmentGetaQuote() {
        //required public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.get_a_quote_frag, container, false);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        session = new SessionManager(getContext());

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

        phvGrains = view.findViewById(R.id.phvGrains);

        llAddGrainVarities = view.findViewById(R.id.llAddGrainVarities);
        //-----------------------------------------------------------------------------Add Grains-------------------------------

        llAddGrainVarities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phvGrains.addView(new RemoveGrnVarty_Models(view.getContext()));
                //add object into array
                JsonObject object = new JsonObject();
                object.addProperty("varity", "5467879");
                datas.add(object);
                Log.e("-------datas length---", String.valueOf(datas.size()));
                Log.e("---data-1--", String.valueOf(session.getVarietyGrains()));
            }
        });

        //-------------------spinners--------------------------------------------------------------------------------------
        spnr_country = view.findViewById(R.id.spnrCountry);
        spnrPaddyAge = view.findViewById(R.id.spnrPaddyAge);
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
                str_fname = etFName.getText().toString();
                str_contct_person = etContctPersn.getText().toString();
                str_phn_no = etPhnNo.getText().toString();
                str_state = etState.getText().toString();
                str_district = etDistrict.getText().toString();
                str_taluk = etTaluk.getText().toString();
                str_town = etTown.getText().toString();
                str_gst_no = etGstNo.getText().toString();
                str_avg_rainfall = etAvgRainFall.getText().toString();
                str_max_wind_speed = etMaxWindSpeed.getText().toString();
                //str_grain_varts = etGrainVarities.getText().toString();
                str_soil_brearing_cap = etSoilBearingCapacity.getText().toString();
                str_avg_density = etAvgDensity.getText().toString();
                str_country = strCountryName;
                str_age_paddy = strPaddyAge;
                str_pincode = etPincode.getText().toString();
                str_procs_id = strProcessId;
                str_procs_img_id = "3";
                initApi(v);
                Fragment mFragment = new ModuleFragment();//background color should set on parent layout of new fragment
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.clGetaQuoteContainer, mFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;

    }
    //------------------------ini api method-----------------------------------------
    public void initApi(View view) {
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


        callEnqFormAPI(session.getUsrId(),  str_procs_id, str_procs_img_id ,str_fname, str_contct_person, str_phn_no,
                str_country, str_pincode,  str_state, str_district,
                str_taluk, str_town, str_gst_no, str_soil_brearing_cap, str_max_wind_speed, str_avg_rainfall, str_age_paddy,
                str_avg_density,  datas);
    }

    public void callEnqFormAPI(String usrId, String procsId, String procsImgId, String fName, String contctPerson, String phn,
                               String country, String pincode, final String state, String district, String taluk, String village,
                               String gstNo, String soilCap, String windSpeed, String rainFall, String agePaddy, String avgDensity,
                               JsonArray grainDetails) {
        PostEnquiryDataModel pedm = new PostEnquiryDataModel(usrId, procsId, procsImgId, fName, contctPerson, phn, country, pincode,
                state, district, taluk, village, gstNo, soilCap, windSpeed, rainFall, agePaddy, avgDensity, grainDetails);
        Call<PostEnquiryDataModel> pedmCall = apiInterface.submitEnquiryData(pedm);
        pedmCall.enqueue(new Callback<PostEnquiryDataModel>() {
            @Override
            public void onResponse(Call<PostEnquiryDataModel> call, Response<PostEnquiryDataModel> response) {
                PostEnquiryDataModel pedmResources = response.body();
                if (pedmResources.status.equals("success")) {
                    Toast.makeText(getContext(), pedmResources.message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), pedmResources.message, Toast.LENGTH_SHORT).show();
                }
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

    public void getImages(String processId) {
       /* phvPaddyColor.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(10)
                .setLayoutManager(new GridLayoutManager(getActivity(), 3));*/
        if (Utils.CheckInternetConnection(getContext())) {
            ImageListModel imageListModel = new ImageListModel(processId);
            Call<ImageListModel> imageListModelCall = apiInterface.getImages(imageListModel);
            imageListModelCall.enqueue(new Callback<ImageListModel>() {
                @Override
                public void onResponse(Call<ImageListModel> call, Response<ImageListModel> response) {
                    ImageListModel imageResources = response.body();
                    if (imageResources.status.equals("success")) {

                        llPaddyImages.setVisibility(View.VISIBLE);
                        List<ImageListModel.ImageListDatum> imageListDatum = imageResources.response;
                        imagesArr = new ArrayList<>();
                        imagesNameArr = new ArrayList<>();
                        imagesUrlArr = new ArrayList<>();

                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
                        phvPaddyColor.setLayoutManager(mLayoutManager);
                        phvPaddyColor.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                        adapter = new PaddyImagesAdapter(getContext(), imagesArr,imagesNameArr,imagesUrlArr);
                        phvPaddyColor.setAdapter(adapter);

                        for (ImageListModel.ImageListDatum imageCount : imageListDatum) {

                            Images imgs = new Images();
                            Images imgsName = new Images();
                            Images imgsUrl = new Images();

                            imgs.setImageId(imageCount.image_id);
                            imagesArr.add(imgs);

                            imgsName.setImgName(imageCount.name);
                            imagesNameArr.add(imgsName);

                            imgsUrl.setImgUrl(imageCount.image);
                            imagesUrlArr.add(imgsUrl);
                           /* phvPaddyColor.addView(new PaddyColorsModels(getContext(), imageCount.image_id,
                                    imageCount.name, imageCount.image));*/
                        }
                        adapter.setEmployees(imagesArr,imagesNameArr,imagesUrlArr);
                       /* btnSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (adapter.getSelected() != null) {
                                    Toast.makeText(getContext(),adapter.getSelected().getImageName(),Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(),"No Selection",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });*/

                    } else {
                        llPaddyImages.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailure(Call<ImageListModel> call, Throwable t) {

                }
            });
        } else {
            Toast.makeText(getContext(), "Please Check Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }

    public void getCountryList() {
        if (Utils.CheckInternetConnection(getContext())) {
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
                        spnr_country.setAdapter(new SpinnerAdapter(getContext(), conNameArr, conIdArr, hidingItemIndex));
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
                        Toast.makeText(getContext(), "No data Available", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<CountryListModel> call, Throwable t) {
                    call.cancel();
                }
            });
        } else {
            Toast.makeText(getContext(), "Please Check Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }

    public void getPaddysAge() {
        if (Utils.CheckInternetConnection(getContext())) {

            Call<PaddysAgeListModel> paddysAgeListModelCall = apiInterface.getPaddyAgeList();
            paddysAgeListModelCall.enqueue(new Callback<PaddysAgeListModel>() {
                @Override
                public void onResponse(Call<PaddysAgeListModel> call, Response<PaddysAgeListModel> response) {
                    PaddysAgeListModel paddyAgeResources = response.body();
                    if (paddyAgeResources.status.equals("success")) {

                        List<PaddysAgeListModel.PaddysAgeDatum> datumList = paddyAgeResources.response;
                        final ArrayList<String> paddyNameArr = new ArrayList<>();
                        final ArrayList<Integer> paddyIdArr = new ArrayList<>();
                        paddyNameArr.add("Select Country");
                        paddyIdArr.add(0);
                        for (PaddysAgeListModel.PaddysAgeDatum countryData : datumList) {
                            paddyNameArr.add(countryData.paddy_age);
                            paddyIdArr.add(countryData.paddyage_id);
                        }
                        spnrPaddyAge.setAdapter(new SpinnerAdapter(getContext(), paddyNameArr, paddyIdArr, hidingItemIndex));
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
                        Toast.makeText(getContext(), "No data Available", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PaddysAgeListModel> call, Throwable t) {

                }
            });

        } else {
            Toast.makeText(getContext(), "Please Check Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }


}
