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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shushmita.apit.R;
import com.example.shushmita.apit.models.AddGrnVarty_Models;
import com.example.shushmita.apit.models.PaddyColorsModels;
import com.example.shushmita.apit.models.RemoveGrnVarty_Models;
import com.mindorks.placeholderview.PlaceHolderView;

public class DashBoardFragmentGetaQuote extends Fragment {

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

    public DashBoardFragmentGetaQuote() {
        //required public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.get_a_quote_frag, container, false);
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
        phvPaddyColor.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(10)
                .setLayoutManager(new GridLayoutManager(getActivity(), 3));
        phvPaddyColor.addView(new PaddyColorsModels(getActivity()));
        phvPaddyColor.addView(new PaddyColorsModels(getActivity()));
        phvPaddyColor.addView(new PaddyColorsModels(getActivity()));
        phvPaddyColor.addView(new PaddyColorsModels(getActivity()));
        phvPaddyColor.addView(new PaddyColorsModels(getActivity()));
        phvPaddyColor.addView(new PaddyColorsModels(getActivity()));

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
                        makeImageVisible();
                        break;
                    case R.id.rdoBtnSteamCuring:
                        makeImageVisible();
                        break;

                }
            }
        });
    }

    public void makeImageVisible() {
        llPaddyImages.setVisibility(View.VISIBLE);
    }
}
