package com.apinnovations.apit.models;

import android.content.Context;
import android.widget.LinearLayout;

import com.apinnovations.apit.R;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;


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
