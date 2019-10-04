package com.example.shushmita.apit.models;

import android.content.Context;
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

    public RemoveGrnVarty_Models(Context contxt) {
        this.mContext = contxt;

    }
    @Resolve
    public void onResolved() {
        session  = new SessionManager(mContext);
        strVariety = etGrainVarities.getText().toString();
        if(!strVariety.equals("null") && !strVariety.equals(null) && !strVariety.isEmpty())
        {
            //strGrainsVariety.setText(strVariety);
             session.setVarietyGrains(strVariety);

        }
        else
        {
            //strGrainsVariety.setText("");
            session.setVarietyGrains("");

        }
    }

    @Click(R.id.llRemoveGrains)
    public void removeRows() {
        //removing row
        ViewGroup.LayoutParams params = llGrainContainer.getLayoutParams();
        params.height = 0;
        llGrainContainer.setLayoutParams(params);
    }

}
