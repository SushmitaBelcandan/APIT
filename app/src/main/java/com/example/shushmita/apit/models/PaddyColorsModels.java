package com.example.shushmita.apit.models;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shushmita.apit.R;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@NonReusable
@Layout(R.layout.paddy_colors_model)
public class PaddyColorsModels {

    @View(R.id.ivGrainImg)
    public ImageView ivGrainImg;

    @View(R.id.cbGrain)
    public CheckBox cbGrain;

    @View(R.id.tvPaddyClrName)
    public TextView tvPaddyClrName;

    public Context mContext;
    public PaddyColorsModels(Context contxt)
    {
        this.mContext = contxt;
    }

    @Resolve
    public void onResolved()
    {

    }
}
