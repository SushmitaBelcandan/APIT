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
    public Context mContext;
    public String strVariety;
    public ArrayList<String> grainsList = new ArrayList<>();

    public RemoveGrnVarty_Models(Context contxt) {
        this.mContext = contxt;

    }
    @Resolve
    public void onResolved() {
        session  = new SessionManager(mContext);

        etGrainVarities.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(android.view.View v, boolean hasFocus) {
                if (!hasFocus) {

                    strVariety = etGrainVarities.getText().toString();
                    if(!strVariety.equals("null") && !strVariety.equals(null) && !strVariety.isEmpty()) {
                        grainsList.add(strVariety);
                        ArrayList<Object> object = new ArrayList<Object>();
                        object.add(strVariety);
                        Intent intent = new Intent("custom-message");
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("verity",(Serializable)object);
                        intent.putExtra("BUNDLE",bundle);
                        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                    }
                    else
                    {
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
