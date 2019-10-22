package com.apinnovations.apit.models;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apinnovations.apit.R;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.Collapse;
import com.mindorks.placeholderview.annotations.expand.Expand;
import com.mindorks.placeholderview.annotations.expand.Parent;
import com.mindorks.placeholderview.annotations.expand.SingleTop;

@Parent
@SingleTop
@Layout(R.layout.stage_models)
public class StageModels {

    @View(R.id.llStageModelContainer)
    public LinearLayout llStageModelContainer;

    @View(R.id.ivToggle)
    public ImageView ivToggle;

    @View(R.id.tvmodelName)
    public TextView tvmodelName;

    public Context mContext;

    public StageModels(Context contxt) {
        this.mContext = contxt;
    }

    @Resolve
    public void onResolved() {
        tvmodelName.setText("Conventional Model");
        ivToggle.setImageDrawable(mContext.getResources().getDrawable(R.drawable.arrow_right_white));
        llStageModelContainer.setBackgroundColor(Color.parseColor("#0c53a0"));
        tvmodelName.setTextColor(Color.parseColor("#FFFFFF"));
        ivToggle.setColorFilter(ContextCompat.getColor(mContext,
                R.color.white));
    }

    @Expand
    public void onExpand() {
        ivToggle.setImageDrawable(mContext.getResources().getDrawable(R.drawable.arrow_down_black));
        llStageModelContainer.setBackgroundColor(Color.parseColor("#F9CE18"));
        tvmodelName.setTextColor(Color.parseColor("#000000"));
        ivToggle.setColorFilter(ContextCompat.getColor(mContext,
                R.color.black));

    }

    @Collapse
    public void onCollapse() {
        ivToggle.setImageDrawable(mContext.getResources().getDrawable(R.drawable.arrow_right_white));
        llStageModelContainer.setBackgroundColor(Color.parseColor("#0c53a0"));
        tvmodelName.setTextColor(Color.parseColor("#FFFFFF"));
        ivToggle.setColorFilter(ContextCompat.getColor(mContext,
                R.color.white));
    }
}
