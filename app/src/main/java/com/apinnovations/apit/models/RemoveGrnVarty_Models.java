package com.apinnovations.apit.models;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.apinnovations.apit.reference.GrainsParcelable;
import com.apinnovations.apit.reference.SessionManager;
import com.apinnovations.apit.R;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

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
    PlaceHolderView mphvGrains;
    public String values = "text";

    public RemoveGrnVarty_Models(Context contxt,PlaceHolderView phvGrains) {
        this.mContext = contxt;
        this.mphvGrains = phvGrains;

    }
    public RemoveGrnVarty_Models(Context contxt,PlaceHolderView phvGrains, String val) {
        this.mContext = contxt;
        this.mphvGrains = phvGrains;
        this.values = val;

    }

    @Resolve
    public void onResolved() {
        session = new SessionManager(mContext);

        etGrainVarities.setText("");

        if(!values.equals("") && !values.equals(null) && !values.equals("null"))
        {
            etGrainVarities.setText(values);
        }
        else
        {
            etGrainVarities.setText("");
        }
    }

    @Click(R.id.llRemoveGrains)
    public void removeRows() {

        mphvGrains.removeView(mphvGrains.getChildLayoutPosition(llGrainContainer));
    }

}
