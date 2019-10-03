package com.example.shushmita.apit.fragment_activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.shushmita.apit.adapters.SpinnerAdapter;

import android.widget.TextView;
import android.widget.Toast;

import com.example.shushmita.apit.R;
import com.example.shushmita.apit.adapters.Utils;
import com.example.shushmita.apit.models.AddGrnVarty_Models;
import com.example.shushmita.apit.models.PaddyColorsModels;
import com.example.shushmita.apit.models.RemoveGrnVarty_Models;
import com.example.shushmita.apit.retrofit_models.APIClient;
import com.example.shushmita.apit.retrofit_models.APIInterface;
import com.example.shushmita.apit.retrofit_models.CountryListModel;
import com.example.shushmita.apit.retrofit_models.ImageListModel;
import com.example.shushmita.apit.retrofit_models.PaddysAgeListModel;
import com.mindorks.placeholderview.PlaceHolderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardFragmentGetaQuote extends Fragment {

    APIInterface apiInterface;
    ConstraintLayout clGetaQuoteContainer;
    private TextView tvSoilBearingCapacity, tvAvgDensity;
    private EditText etSoilBearingCapacity, etAvgDensity;
    private LinearLayout llAddGrainVarities, llAddressDetails;
    private EditText etPincode;
    PlaceHolderView phvPaddyColor;
    private RadioGroup rdoGrpProcess;
    private RadioButton rdoBtnParboiling, rdoBtnSteamCuring;
    private LinearLayout llPaddyImages;
    public static PlaceHolderView phvGrains;
    private Button btnSubmit;
    private Spinner spnr_country, spnrPaddyAge;

    private int strCountryId, strPaddyId;
    private String strCountryName, strPaddyAge;
    int hidingItemIndex = 0;
    static ArrayList<Integer> selectedImage = new ArrayList<>();
    private String strProcessId;

    public DashBoardFragmentGetaQuote() {
        //required public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.get_a_quote_frag, container, false);

        apiInterface = APIClient.getClient().create(APIInterface.class);

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
        llAddGrainVarities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phvGrains.addView(new RemoveGrnVarty_Models(view.getContext()));
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
                Fragment mFragment = new ModuleFragment();//background color should set on parent layout of new fragment
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.clGetaQuoteContainer, mFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;

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

    public void getImages(String processId)
    {
        phvPaddyColor.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(10)
                .setLayoutManager(new GridLayoutManager(getActivity(), 3));
        if(Utils.CheckInternetConnection(getContext()))
        {
            ImageListModel imageListModel = new ImageListModel(processId);
            Call<ImageListModel> imageListModelCall = apiInterface.getImages(imageListModel);
            imageListModelCall.enqueue(new Callback<ImageListModel>() {
                @Override
                public void onResponse(Call<ImageListModel> call, Response<ImageListModel> response) {
                    ImageListModel imageResources = response.body();
                    if(imageResources.status.equals("success"))
                    {
                        llPaddyImages.setVisibility(View.VISIBLE);
                        List<ImageListModel.ImageListDatum> imageListDatum = imageResources.response;
                        for(ImageListModel.ImageListDatum imageCount : imageListDatum)
                        {
                            phvPaddyColor.addView(new PaddyColorsModels(getContext(),imageCount.image_id,
                                    imageCount.name,imageCount.image,selectedImage));
                        }

                    }
                    else
                    {
                        llPaddyImages.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailure(Call<ImageListModel> call, Throwable t) {

                }
            });
        }
        else
        {
            Toast.makeText(getContext(),"Please Check Internet Connection!",Toast.LENGTH_SHORT).show();
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
                    if(paddyAgeResources.status.equals("success"))
                    {

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
