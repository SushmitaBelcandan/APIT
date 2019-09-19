package com.example.shushmita.apit.models;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.shushmita.apit.R;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import static com.example.shushmita.apit.fragment_activities.DashBoardFragmentGetaQuote.phvGrains;

@NonReusable
@Layout(R.layout.remove_grain_varities)
public class AddGrnVarty_Models {

    @View(R.id.llGrainContainer)
    public LinearLayout llGrainContainer;

    @View(R.id.llAddGrains)
    public LinearLayout llAddGrains;

    public Context mContext;

    public AddGrnVarty_Models(Context contxt) {
        this.mContext = contxt;
    }

    @Resolve
    public void onResolved() {

    }

    @Click(R.id.llAddGrains)
    public void removeRows() {
    }
}
