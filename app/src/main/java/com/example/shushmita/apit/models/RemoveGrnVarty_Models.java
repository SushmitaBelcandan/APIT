package com.example.shushmita.apit.models;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.shushmita.apit.R;
import com.example.shushmita.apit.reference.GrainsParcelable;
import com.example.shushmita.apit.reference.SessionManager;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.io.Serializable;
import java.util.ArrayList;

@NonReusable
@Layout(R.layout.remove_grain_varities)
public class RemoveGrnVarty_Models {

    @View(R.id.llGrainContainer)
    public LinearLayout llGrainContainer;

    @View(R.id.llRemoveGrains)
    public LinearLayout llRemoveGrains;

    @View(R.id.etGrainVarities)
    public EditText etGrainVarities;

    SessionManager session;
    GrainsParcelable grainsParcelable;
    public Context mContext;
    public String strVariety;
    public ArrayList<GrainsParcelable> grainsList = new ArrayList<GrainsParcelable>();

    public ArrayList<String> grains_val = new ArrayList<>();
    public ArrayList<Integer> grains_val_id = new ArrayList<>();
    int count = 1;

    public RemoveGrnVarty_Models(Context contxt,ArrayList grains_val, int grains_id) {
        this.mContext = contxt;
        this.grains_val = grains_val;
        this.count = grains_id;
        count = count+1;
    }

    @Resolve
    public void onResolved() {
        session = new SessionManager(mContext);

        etGrainVarities.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(android.view.View v, boolean hasFocus) {
                if (!hasFocus) {

                    strVariety = etGrainVarities.getText().toString();
                    if (!strVariety.equals("null") && !strVariety.equals(null) && !strVariety.isEmpty()) {

                        grains_val.add(strVariety);
                        //grains_val_id.add(count);
                    } else {
                        //nothing
                    }
                }
            }
        });

    }

    @Click(R.id.llRemoveGrains)
    public void removeRows() {
        //removing row
        ViewGroup.LayoutParams params = llGrainContainer.getLayoutParams();
        params.height = 0;
        llGrainContainer.setLayoutParams(params);
    }

}
