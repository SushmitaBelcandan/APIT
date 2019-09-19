package com.example.shushmita.apit.fragment_activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shushmita.apit.R;
import com.example.shushmita.apit.models.AddGrnVarty_Models;
import com.example.shushmita.apit.models.RemoveGrnVarty_Models;
import com.mindorks.placeholderview.PlaceHolderView;

public class DashBoardFragmentGetaQuote extends Fragment {

    private TextView tvSoilBearingCapacity,tvAvgDensity;
    private EditText etSoilBearingCapacity,etAvgDensity;
    private LinearLayout llAddGrainVarities,llAddressDetails;
    private EditText etPincode;
    LayoutInflater inflater;
    //private LinearLayout llGrainDetailContainer;

    EditText etGrainVarity;
    int IdNum = 1;

    public  static PlaceHolderView phvGrains;

    public DashBoardFragmentGetaQuote() {
        //required public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.get_a_quote_frag, container, false);
        tvSoilBearingCapacity = view.findViewById(R.id.tvSoilBearingCapacity);
        tvSoilBearingCapacity.setText(Html.fromHtml("Soil Bearing Capacity ( t/m<sup><small>2</small></sup> )"));

        etSoilBearingCapacity = view.findViewById(R.id.etSoilBearingCapacity);
        etSoilBearingCapacity.setHint(Html.fromHtml("Enter Soil Bearing Capacity in t/m<sup><small>2</small></sup>"));

        etAvgDensity = view.findViewById(R.id.etAvgDensity);
        etAvgDensity.setHint(Html.fromHtml("Enter Average Density of Paddy in kg/m<sup><small>2</small></sup>"));
        tvAvgDensity = view.findViewById(R.id.tvAvgDensity);
        tvAvgDensity.setText(Html.fromHtml("Average Density of Paddy ( kg/m<sup><small>3</small></sup> )"));

        phvGrains = view.findViewById(R.id.phvGrains);
        // llGrainDetailContainer = view.findViewById(R.id.llGrainDetailContainer);
        llAddGrainVarities = view.findViewById(R.id.llAddGrainVarities);
        llAddGrainVarities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            phvGrains.addView(new RemoveGrnVarty_Models(view.getContext()));
            }
        });

        //address details that will be visible for indian people only
        llAddressDetails = view.findViewById(R.id.llAddressDetails);
        etPincode = view.findViewById(R.id.etPincode);
        if(!etPincode.getText().toString().isEmpty())
        {
            llAddressDetails.setVisibility(View.VISIBLE);
        }
        return view;

    }
}
