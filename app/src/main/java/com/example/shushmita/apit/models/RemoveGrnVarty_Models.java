package com.example.shushmita.apit.models;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.shushmita.apit.R;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@NonReusable
@Layout(R.layout.remove_grain_varities)
public class RemoveGrnVarty_Models {

    @View(R.id.llGrainContainer)
    public LinearLayout llGrainContainer;

    @View(R.id.llRemoveGrains)
    public LinearLayout llRemoveGrains;


    public Context mContext;

    public RemoveGrnVarty_Models(Context contxt) {
        this.mContext = contxt;
    }

    @Resolve
    public void onResolved() {

    }

    @Click(R.id.llRemoveGrains)
    public void removeRows() {
        //removing row
        ViewGroup.LayoutParams params = llGrainContainer.getLayoutParams();
        params.height = 0;
        llGrainContainer.setLayoutParams(params);
    }
}
