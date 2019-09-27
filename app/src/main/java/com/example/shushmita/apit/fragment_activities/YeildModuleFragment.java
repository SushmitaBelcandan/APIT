package com.example.shushmita.apit.fragment_activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.shushmita.apit.R;

public class YeildModuleFragment extends Fragment {


    private Button btnSubmitYeildModule;
    private LinearLayout llYeildModuleContainer;
    public YeildModuleFragment()
    {
        //required constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yeild_module_fragment,container,false);

        btnSubmitYeildModule = view.findViewById(R.id.btnSubmitYeildModule);
        llYeildModuleContainer = view.findViewById(R.id.llYeildModuleContainer);
        btnSubmitYeildModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment mFragment = new YeildModuleGraphFragment();//background color should set on parent layout of new fragment
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.llYeildModuleContainer, mFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;

    }
}
