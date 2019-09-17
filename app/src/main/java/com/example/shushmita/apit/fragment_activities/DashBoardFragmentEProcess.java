package com.example.shushmita.apit.fragment_activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shushmita.apit.R;

public class DashBoardFragmentEProcess extends Fragment {

    public DashBoardFragmentEProcess()
    {
        //required public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.e_process_frag,container,false);
        return view;

    }
}
