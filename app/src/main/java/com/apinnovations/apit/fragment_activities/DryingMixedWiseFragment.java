package com.apinnovations.apit.fragment_activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apinnovations.apit.R;


public class DryingMixedWiseFragment extends Fragment {

    Toolbar toolbarDryMixedWise;

    public DryingMixedWiseFragment() {
        //required constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.drying_mixed_wise, container, false);
        toolbarDryMixedWise = view.findViewById(R.id.toolbarDryMixedWise);
        toolbarDryMixedWise.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}
