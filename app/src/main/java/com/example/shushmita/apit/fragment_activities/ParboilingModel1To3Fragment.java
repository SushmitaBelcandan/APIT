package com.example.shushmita.apit.fragment_activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.shushmita.apit.R;
import com.example.shushmita.apit.activities.AfterProcess_Act;

public class ParboilingModel1To3Fragment extends Fragment {

    Toolbar toolbarParboiling;
    private TextView tvProcessModelTitle;
    private ImageView ivHydrTank, ivDryerMethodName;
    private TextView tvNumHydrn;
    private Button btnSubmit;

    public ParboilingModel1To3Fragment() {
        //required constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parboiling_model_1, container, false);
        toolbarParboiling = view.findViewById(R.id.toolbarParboiling);
        toolbarParboiling.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        tvProcessModelTitle = view.findViewById(R.id.tvProcessModelTitle);
        ivHydrTank = view.findViewById(R.id.ivHydrTank);
        ivHydrTank.setClipToOutline(true);//fit with parent container

        ivDryerMethodName = view.findViewById(R.id.ivDryerMethodName);
        ivDryerMethodName.setClipToOutline(true);//fit with parent container

        tvNumHydrn = view.findViewById(R.id.tvNumHydrn);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAfterProcess = new Intent(getActivity(),AfterProcess_Act.class);
                startActivity(intentAfterProcess);
             }
        });

        return view;
    }



}
