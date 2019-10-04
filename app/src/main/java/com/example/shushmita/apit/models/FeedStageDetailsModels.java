package com.example.shushmita.apit.models;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shushmita.apit.R;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@NonReusable
@Layout(R.layout.feed_stage_items)
public class FeedStageDetailsModels {

    @View(R.id.ivImg)
    public ImageView ivImg;

    @View(R.id.tvDesc)
    public TextView tvDesc;

    @View(R.id.phvListItems)
    public PlaceHolderView phvListItems;

    public Context mContext;
    public FeedStageDetailsModels(Context contxt)
    {
        this.mContext = contxt;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Resolve
    public void onResolved()
    {
        phvListItems.addView(new FeedStageItems(mContext));
        phvListItems.addView(new FeedStageItems(mContext));

        ivImg.setClipToOutline(true); //rounded image corner
    }

}
