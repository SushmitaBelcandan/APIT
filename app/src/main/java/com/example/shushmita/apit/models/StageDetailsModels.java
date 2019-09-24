package com.example.shushmita.apit.models;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shushmita.apit.R;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.ChildPosition;
import com.mindorks.placeholderview.annotations.expand.Collapse;
import com.mindorks.placeholderview.annotations.expand.Expand;
import com.mindorks.placeholderview.annotations.expand.Parent;
import com.mindorks.placeholderview.annotations.expand.ParentPosition;
import com.mindorks.placeholderview.annotations.expand.SingleTop;
import com.mindorks.placeholderview.annotations.expand.Toggle;

@Layout(R.layout.stage_details_models)
public class StageDetailsModels {

    @View(R.id.llStageItems)
    public LinearLayout llStageItems;

    @View(R.id.ivToggle)
    public ImageView ivToggle;

    @View(R.id.tvStageNo)
    public TextView tvStageNo;

    public Context mContext;

    public StageDetailsModels(Context contxt) {
        this.mContext = contxt;
    }

    @Resolve
    public void onResolved() {
        tvStageNo.setText("1st Stage");
        Log.e("------stage------", String.valueOf(tvStageNo));
        ivToggle.setImageDrawable(mContext.getResources().getDrawable(R.drawable.arrow_right_white));
        llStageItems.setBackgroundColor(Color.parseColor("#0c53a0"));
        tvStageNo.setTextColor(Color.parseColor("#FFFFFF"));
        ivToggle.setColorFilter(ContextCompat.getColor(mContext,
                R.color.white));
    }

    @Expand
    public void onExpand() {
        ivToggle.setImageDrawable(mContext.getResources().getDrawable(R.drawable.arrow_down_black));
        llStageItems.setBackgroundColor(Color.parseColor("#F9CE18"));
        tvStageNo.setTextColor(Color.parseColor("#000000"));
        ivToggle.setColorFilter(ContextCompat.getColor(mContext,
                R.color.black));

    }

    @Collapse
    public void onCollapse() {
        ivToggle.setImageDrawable(mContext.getResources().getDrawable(R.drawable.arrow_right_white));
        llStageItems.setBackgroundColor(Color.parseColor("#0c53a0"));
        tvStageNo.setTextColor(Color.parseColor("#FFFFFF"));
        ivToggle.setColorFilter(ContextCompat.getColor(mContext,
                R.color.white));
    }


    @Click(R.id.llStageItems)
    public void onClick() {

        ivToggle.setImageDrawable(mContext.getResources().getDrawable(R.drawable.arrow_down_black));
        llStageItems.setBackgroundColor(Color.parseColor("#F9CE18"));
        tvStageNo.setTextColor(Color.parseColor("#000000"));
        ivToggle.setColorFilter(ContextCompat.getColor(mContext, R.color.black));

    }

}
