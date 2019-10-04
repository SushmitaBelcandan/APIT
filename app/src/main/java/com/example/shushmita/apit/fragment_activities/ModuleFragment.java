package com.example.shushmita.apit.fragment_activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.shushmita.apit.R;
import com.example.shushmita.apit.models.ExpandListModels;
import com.example.shushmita.apit.models.FeedStageDetailsModels;
import com.example.shushmita.apit.models.PaddyColorsModels;
import com.example.shushmita.apit.models.StageDetailsModels;
import com.example.shushmita.apit.models.StageModels;
import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.mindorks.placeholderview.PlaceHolderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModuleFragment extends Fragment {

    Toolbar toolbar;
    TextView tvToolbarTitle;
    ExpandablePlaceHolderView exphvModels;

    public ModuleFragment() {
        //required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.modules_fragment, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        tvToolbarTitle = toolbar.findViewById(R.id.tvToolbarTitle);
        //back to previous page
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        //----------------model list-----------------------------------------
        exphvModels = view.findViewById(R.id.exphvModels);
       /* for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                exphvModels.addView(new StageDetailsModels(getActivity()));
                Log.e("------sub items------", String.valueOf(j));
            }
            exphvModels.addView(new StageModels(getActivity()));
            Log.e("------items------", String.valueOf(i));

        }*/
        exphvModels.addView(new StageModels(getActivity()))
                .addView(new ExpandListModels(getActivity()))
                .addView(new StageModels(getActivity()))
                .addView(new StageDetailsModels(getActivity()))
                .addView(new StageDetailsModels(getActivity()))
                .addView(new StageDetailsModels(getActivity()))
                .addView(new StageDetailsModels(getActivity()));
        //-------------------------------------------------------------------
        return view;
    }
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa");

        List<String> football = new ArrayList<String>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");
        football.add("Netherlands");
        football.add("Italy");

        List<String> basketball = new ArrayList<String>();
        basketball.add("United States");
        basketball.add("Spain");
        basketball.add("Argentina");
        basketball.add("France");
        basketball.add("Russia");

        expandableListDetail.put("1st Stage", cricket);
        expandableListDetail.put("2nd Stage", football);
        expandableListDetail.put("3rd Stage", basketball);
        return expandableListDetail;
    }

}
